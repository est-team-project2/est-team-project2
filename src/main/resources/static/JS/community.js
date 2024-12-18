// community.js

// 전역 변수 선언
let uploadedFiles = [];

// DOM이 로드되면 이벤트 리스너 설정
document.addEventListener('DOMContentLoaded', function() {
    initializeEventListeners();
});

// 이벤트 리스너 초기화
function initializeEventListeners() {
    const writeBtn = document.querySelector('.write-btn');
    const modal = document.getElementById('writeModal');
    const writeForm = document.getElementById('writeForm');
    
    writeBtn.addEventListener('click', openWriteModal);
    document.addEventListener('keydown', handleEscapeKey);
    modal.addEventListener('click', handleOutsideClick);
    writeForm.addEventListener('submit', submitPost);

    // 드래그 앤 드롭 이벤트 설정
    const dropZone = document.getElementById('fileUploadZone');
    dropZone.addEventListener('dragover', handleDragOver);
    dropZone.addEventListener('drop', handleFileDrop);
}

function handleDragOver(event) {
    event.preventDefault();
    event.stopPropagation();
    event.currentTarget.classList.add('dragover');
}

function handleFileDrop(event) {
    event.preventDefault();
    event.stopPropagation();
    event.currentTarget.classList.remove('dragover');
    
    const files = Array.from(event.dataTransfer.files);
    handleFileUpload(files);
}

function handleFileUpload(files) {
    files.forEach(file => {
        if (validateFile(file)) {
            createPreview(file);
            uploadedFiles.push(file);
        }
    });
}

// 파일 유효성 검사
function validateFile(file) {
    const validTypes = ['image/', 'video/'];
    const isValidType = validTypes.some(type => file.type.startsWith(type));
    
    if (!isValidType) {
        alert('이미지 또는 동영상 파일만 업로드할 수 있습니다.');
        return false;
    }
    return true;
}

// 미리보기 생성
function createPreview(file) {
    const reader = new FileReader();
    reader.onload = function(e) {
        const previewContainer = document.getElementById('filePreviewContainer');
        const previewItem = createPreviewItem(e.target.result, file);
        previewContainer.appendChild(previewItem);
    };
    reader.readAsDataURL(file);
}

function createPreviewItem(src, file) {
    const div = document.createElement('div');
    div.className = 'preview-item';

    const media = file.type.startsWith('image/') ? new Image() : document.createElement('video');
    media.src = src;
    if (file.type.startsWith('video/')) {
        media.controls = true;
    }

    const removeButton = createRemoveButton(div, file);
    const fileInfo = createFileInfo(file);

    div.appendChild(media);
    div.appendChild(removeButton);
    div.appendChild(fileInfo);

    return div;
}

function createRemoveButton(container, file) {
    const button = document.createElement('button');
    button.className = 'remove-file';
    button.innerHTML = '×';
    button.onclick = () => removeFile(container, file);
    return button;
}

function createFileInfo(file) {
    const div = document.createElement('div');
    div.className = 'file-info';
    const fileType = file.type.startsWith('image/') ? '이미지' : '동영상';
    div.textContent = `${fileType} - ${formatFileSize(file.size)}`;
    return div;
}

function removeFile(element, file) {
    uploadedFiles = uploadedFiles.filter(f => f !== file);
    element.remove();
}

// 미리보기 초기화
function clearPreviews() {
    document.getElementById('filePreviewContainer').innerHTML = '';
    uploadedFiles = [];
}

// 폼 제출 처리
async function submitPost(event) {
    event.preventDefault();
    
    try {
        const formData = new FormData();
        const title = document.getElementById('postTitle').value;
        const content = document.getElementById('postContent').value;

        formData.append('title', title);
        formData.append('content', content);
        
        // 파일 추가
        uploadedFiles.forEach((file, index) => {
            formData.append('files', file);
        });

        const response = await fetch('/api/posts', {
            method: 'POST',
            body: formData
        });

        if (!response.ok) {
            throw new Error('게시글 등록에 실패했습니다.');
        }

        const result = await response.json();
        alert('게시글이 등록되었습니다.');
        closeWriteModal();
        location.reload();
    } catch (error) {
        console.error('Error:', error);
        alert(error.message || '게시글 등록 중 오류가 발생했습니다.');
    }
}