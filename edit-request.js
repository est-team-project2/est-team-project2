// API 연동을 위한 설정
const API_BASE_URL = 'http://your-api-domain/api';

// API 호출 함수들
async function fetchBreedData(breedId) {
    try {
        const response = await fetch(`${API_BASE_URL}/encyclopedia/${breedId}`);
        return await response.json();
    } catch (error) {
        console.error('Error fetching breed data:', error);
        throw error;
    }
}

async function submitEditRequest() {
    const editData = {
        encyclopediaId: currentBreedId,
        originalVersionId: currentVersionId,
        content: {
            breedName: document.getElementById('editBreedName').value,
            size: document.getElementById('editSize').value,
            weight: document.getElementById('editWeight').value,
            personality: document.getElementById('editPersonality').value,
            characteristics: document.getElementById('editCharacteristics').value
        },
        reason: document.getElementById('editReason').value
    };

    try {
        const response = await fetch(`${API_BASE_URL}/edit-requests`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${getUserToken()}`  // 사용자 인증 토큰
            },
            body: JSON.stringify(editData)
        });

        if (response.ok) {
            alert('수정 요청이 제출되었습니다. 관리자/전문가 검토 후 반영됩니다.');
            closeModal();
        } else {
            throw new Error('수정 요청 제출 실패');
        }
    } catch (error) {
        console.error('Error submitting edit request:', error);
        alert('수정 요청 제출 중 오류가 발생했습니다.');
    }
}

async function approveEdit(editRequestId) {
    try {
        const response = await fetch(`${API_BASE_URL}/edit-requests/${editRequestId}/approve`, {
            method: 'POST',
            headers: {
                'Authorization': `Bearer ${getUserToken()}`
            },
            body: JSON.stringify({
                comment: document.getElementById('reviewComment').value
            })
        });

        if (response.ok) {
            alert('수정 요청이 승인되었습니다.');
            closeModal();
        } else {
            throw new Error('승인 처리 실패');
        }
    } catch (error) {
        console.error('Error approving edit:', error);
        alert('승인 처리 중 오류가 발생했습니다.');
    }
}