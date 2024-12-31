// static/js/encyclopedia.js

document.addEventListener('DOMContentLoaded', function() {
    initializeSearch();
    initializeFilters();
    loadBreeds();
});

// 검색 초기화
function initializeSearch() {
    const searchInput = document.getElementById('searchInput');
    let debounceTimer;

    searchInput.addEventListener('input', function() {
        clearTimeout(debounceTimer);
        debounceTimer = setTimeout(() => {
            const searchTerm = this.value.trim();
            if (searchTerm.length >= 2) {
                searchBreeds(searchTerm);
            } else if (searchTerm.length === 0) {
                loadBreeds();
            }
        }, 300);
    });
}

// 필터 초기화
function initializeFilters() {
    const sizeFilter = document.getElementById('sizeFilter');
    const sortOption = document.getElementById('sortOption');
    const categoryButtons = document.querySelectorAll('.category-btn');

    sizeFilter.addEventListener('change', applyFilters);
    sortOption.addEventListener('change', applyFilters);

    categoryButtons.forEach(button => {
        button.addEventListener('click', function() {
            categoryButtons.forEach(btn => btn.classList.remove('active'));
            this.classList.add('active');
            applyFilters();
        });
    });
}

// 견종 데이터 로드
async function loadBreeds() {
    showLoadingState();
    try {
        const response = await fetch('/api/breeds');
        if (!response.ok) throw new Error('Failed to load breeds');

        const data = await response.json();
        renderBreeds(data);
    } catch (error) {
        console.error('Error:', error);
        showErrorMessage('견종 데이터를 불러오는데 실패했습니다.');
    } finally {
        hideLoadingState();
    }
}

// 검색 실행
async function searchBreeds(term) {
    showLoadingState();
    try {
        const response = await fetch(`/api/breeds/search?q=${encodeURIComponent(term)}`);
        if (!response.ok) throw new Error('Search failed');

        const data = await response.json();
        renderBreeds(data);
    } catch (error) {
        console.error('Error:', error);
        showErrorMessage('검색 중 오류가 발생했습니다.');
    } finally {
        hideLoadingState();
    }
}

// 필터 적용
function applyFilters() {
    const size = document.getElementById('sizeFilter').value;
    const sort = document.getElementById('sortOption').value;
    const category = document.querySelector('.category-btn.active').dataset.category;

    const filteredData = filterBreeds(initialData, { size, category });
    const sortedData = sortBreeds(filteredData, sort);
    renderBreeds(sortedData);
}

// 견종 필터링
function filterBreeds(breeds, filters) {
    return breeds.filter(breed => {
        const sizeMatch = !filters.size || breed.size === filters.size;
        const categoryMatch = filters.category === 'all' || breed.category === filters.category;
        return sizeMatch && categoryMatch;
    });
}

// 견종 정렬
function sortBreeds(breeds, sortBy) {
    return [...breeds].sort((a, b) => {
        switch (sortBy) {
            case 'name':
                return a.name.localeCompare(b.name);
            case 'popular':
                return b.viewCount - a.viewCount;
            case 'updated':
                return new Date(b.updatedAt) - new Date(a.updatedAt);
            default:
                return 0;
        }
    });
}

// 견종 렌더링
function renderBreeds(breeds) {
    const grid = document.getElementById('breedsGrid');

    if (breeds.length === 0) {
        grid.innerHTML = `
            <div class="no-results">
                <img src="/images/empty-state.svg" alt="No results">
                <p>검색 결과가 없습니다.</p>
            </div>
        `;
        return;
    }

    grid.innerHTML = breeds.map(breed => `
        <div class="breed-card" data-id="${breed.id}">
            <div class="breed-image" style="background-image: url('${breed.imageUrl || '/images/placeholder-dog.png'}')"></div>
            <div class="breed-info">
                <h3 class="breed-name">${breed.name}</h3>
                <p class="breed-description">${breed.shortDescription || ''}</p>
                <div class="breed-tags">
                    <span class="tag">${breed.size}</span>
                    <span class="tag">${breed.category}</span>
                </div>
                <div class="breed-meta">
                    <span class="views">${formatNumber(breed.viewCount)} views</span>
                    <span class="updated">Updated ${formatDate(breed.updatedAt)}</span>
                </div>
            </div>
        </div>
    `).join('');

    // 카드 클릭 이벤트 추가
    document.querySelectorAll('.breed-card').forEach(card => {
        card.addEventListener('click', () => {
            const breedId = card.dataset.id;
            showBreedModal(breeds.find(b => b.id === breedId));
        });
    });
}

// 유틸리티 함수들
function showLoadingState() {
    document.getElementById('breedsGrid').classList.add('loading');
}

function hideLoadingState() {
    document.getElementById('breedsGrid').classList.remove('loading');
}

function formatNumber(num) {
    return new Intl.NumberFormat().format(num);
}