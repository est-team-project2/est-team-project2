let map;
let markers = [];

const breedData = {
  'CORGI': {
    name: '웰시 코기',
    size: '소형',
    weight: '10-12kg',
    personality: '영리하고 활발하며 충성심이 강합니다. 사교적이고 친근한 성격을 가지고 있습니다.',
    characteristics: '짧은 다리와 긴 몸통이 특징적이며, 양치기 개로서의 본능을 가지고 있습니다. 운동량이 많이 필요하며 훈련 능력이 뛰어납니다.'
  },
  'GERMAN_SHEPHERD': {
    name: '저먼 셰퍼드',
    size: '대형',
    weight: '30-40kg',
    personality: '충성심이 강하고 지능이 높으며, 용감하고 자신감이 있습니다.',
    characteristics: '강인한 체격과 뛰어난 운동능력을 가지고 있으며, 경찰견이나 군용견으로 자주 활용됩니다.'
  },
  'MALTIPOO': {
    name: '말티푸',
    size: '소형',
    weight: '3-6kg',
    personality: '활발하고 애교가 많으며, 사교적이고 친근합니다.',
    characteristics: '저자극성 털을 가지고 있어 알레르기가 있는 사람에게 적합하며, 똑똑하고 훈련이 잘 됩니다.'
  },
  'DOBERMAN': {
    name: '도베르만',
    size: '대형',
    weight: '32-45kg',
    personality: '충성심이 강하고 용감하며, 가족을 잘 보호합니다.',
    characteristics: '근육질의 체격과 뛰어난 운동능력을 가지고 있으며, 경비견으로 우수한 능력을 보입니다.'
  },
  'BORDER_COLLIE': {
    name: '보더 콜리',
    size: '중형',
    weight: '14-20kg',
    personality: '지능이 매우 높고 활동적이며 훈련성이 뛰어납니다.',
    characteristics: '뛰어난 지능과 운동신경을 가졌으며, 양치기 개로서 탁월한 능력을 보입니다.'
  },
  'GOLDEN_RETRIEVER': {
    name: '골든 리트리버',
    size: '대형',
    weight: '25-34kg',
    personality: '온순하고 친근하며 인내심이 많습니다.',
    characteristics: '부드러운 금색 털과 온화한 성격으로 가족견으로 인기가 많습니다.'
  },
  'POODLE': {
    name: '푸들',
    size: '소형~대형',
    weight: '3-30kg',
    personality: '영리하고 우아하며 활발한 성격입니다.',
    characteristics: '털이 빠지지 않고 알레르기 반응이 적으며, 다양한 크기로 존재합니다.'
  },
  'CHIHUAHUA': {
    name: '치와와',
    size: '초소형',
    weight: '1.5-3kg',
    personality: '용감하고 충성심이 강하며 활발합니다.',
    characteristics: '세계에서 가장 작은 견종 중 하나이며, 긴 수명을 가지고 있습니다.'
  }
};

// 초기화 함수
function initializeSearch() {
  const searchInput = document.getElementById('searchInput');
  const mapContainer = document.getElementById('map');

  // 카카오맵 초기화
  map = new kakao.maps.Map(mapContainer, {
    center: new kakao.maps.LatLng(37.566826, 126.978656),
    level: 3
  });

  // 검색 이벤트 리스너
  searchInput.addEventListener('input', debounce(handleSearch, 300));
}

// 검색 처리 함수
function handleSearch() {
  const searchInput = document.getElementById('searchInput');
  const query = searchInput.value.trim();

  if (!query) {
    document.getElementById('searchResults').style.display = 'none';
    document.getElementById('map').style.display = 'none';
    return;
  }

  searchAll(query);
}

// 통합 검색 함수
function searchAll(query) {
  // 백과사전 검색
  const encyclopediaResults = Object.entries(breedData)
  .filter(([_, breed]) =>
      breed.name.includes(query) ||
      breed.characteristics.includes(query) ||
      breed.personality.includes(query)
  )
  .map(([code, breed]) => ({
    type: 'encyclopedia',
    code,
    name: breed.name,
    description: breed.characteristics
  }));

  // 카카오맵 검색
  const places = new kakao.maps.services.Places();
  const searchTypes = [
    {type: 'petshop', keyword: '애견용품'},
    {type: 'hospital', keyword: '동물병원'},
    {type: 'emergency', keyword: '24시동물병원'},
    {type: 'hotel', keyword: '애견동반호텔'}
  ];

  clearMarkers();

  // 모든 검색 결과 합치기
  Promise.all(searchTypes.map(({type, keyword}) => {
    return new Promise((resolve) => {
      places.keywordSearch(`${keyword} ${query}`, (result, status) => {
        if (status === kakao.maps.services.Status.OK) {
          resolve(result.map(place => ({...place, type})));
        } else {
          resolve([]);
        }
      });
    });
  })).then(results => {
    const allPlaces = results.flat();
    displayCombinedResults(encyclopediaResults, allPlaces);
  });
}

// 통합 검색 결과 표시
function displayCombinedResults(encyclopediaResults, placeResults) {
  const resultsDiv = document.getElementById('searchResults');
  const mapContainer = document.getElementById('map');

  resultsDiv.style.display = 'block';
  mapContainer.style.display = placeResults.length > 0 ? 'block' : 'none';

  let resultsHtml = '';

  // 백과사전 결과 표시
  if (encyclopediaResults.length > 0) {
    resultsHtml += '<div class="search-category"><h3>백과사전</h3>';
    resultsHtml += encyclopediaResults.map(result => `
                    <div class="search-result-item" onclick="openBreedDetails('${result.code}')">
                        <strong>${result.name}</strong>
                        <p>${result.description.substring(0, 100)}...</p>
                    </div>
                `).join('');
    resultsHtml += '</div>';
  }

  // 장소 결과 표시
  if (placeResults.length > 0) {
    const bounds = new kakao.maps.LatLngBounds();
    const typeLabels = {
      'petshop': '펫샵',
      'hospital': '동물병원',
      'emergency': '야간 응급 동물병원',
      'hotel': '애견 동반 호텔'
    };

    // 카테고리별 결과 그룹화 및 표시
    const groupedResults = groupPlacesByType(placeResults);
    resultsHtml += displayPlaceResults(groupedResults, typeLabels, bounds);

    if (markers.length > 0) {
      map.setBounds(bounds);
    }
  }

  // 검색 결과 없음 처리
  if (encyclopediaResults.length === 0 && placeResults.length === 0) {
    resultsHtml = '<div class="no-results">검색 결과가 없습니다.</div>';
  }

  resultsDiv.innerHTML = resultsHtml;
}

// 장소 결과 그룹화
function groupPlacesByType(places) {
  return places.reduce((acc, place) => {
    if (!acc[place.type]) {
      acc[place.type] = [];
    }
    acc[place.type].push(place);
    return acc;
  }, {});
}

// 장소 결과 HTML 생성
function displayPlaceResults(groupedResults, typeLabels, bounds) {
  let html = '';
  Object.entries(groupedResults).forEach(([type, places]) => {
    html += `<div class="search-category"><h3>${typeLabels[type]}</h3>`;
    places.forEach(place => {
      const position = new kakao.maps.LatLng(place.y, place.x);
      bounds.extend(position);

      const marker = createMarker(place, position);
      markers.push(marker);

      html += createPlaceResultHTML(place, markers.length - 1);
    });
    html += '</div>';
  });
  return html;
}

// 마커 생성
function createMarker(place, position) {
  const marker = new kakao.maps.Marker({
    map: map,
    position: position
  });

  const infowindow = new kakao.maps.InfoWindow({
    content: `
                    <div style="padding:5px;font-size:12px;">
                        <strong>${place.place_name}</strong><br>
                        ${place.phone || '전화번호 없음'}<br>
                        <small>${place.address_name}</small>
                    </div>
                `
  });

  kakao.maps.event.addListener(marker, 'mouseover',
      () => infowindow.open(map, marker));
  kakao.maps.event.addListener(marker, 'mouseout', () => infowindow.close());
  kakao.maps.event.addListener(marker, 'click', () => {
    window.open(
        `https://map.kakao.com/link/to/${place.place_name},${place.y},${place.x}`,
        '_blank');
  });

  marker.infowindow = infowindow;
  return marker;
}

// 장소 결과 HTML 생성
function createPlaceResultHTML(place, markerIndex) {
  return `
                <div class="search-result-item" 
                    onclick="panToMarker(${place.y}, ${place.x})"
                    onmouseover="showInfoWindow(${markerIndex})"
                    onmouseout="hideInfoWindow(${markerIndex})">
                    <strong>${place.place_name}</strong>
                    <p>${place.address_name}</p>
                    <small>${place.phone || '전화번호 없음'}</small>
                    <div class="search-result-actions">
                        <button onclick="window.open('https://map.kakao.com/link/to/${place.place_name},${place.y},${place.x}', '_blank'); event.stopPropagation();">
                            길찾기
                        </button>
                    </div>
                </div>
            `;
}

// 유틸리티 함수들
function showInfoWindow(index) {
  if (markers[index]?.infowindow) {
    markers[index].infowindow.open(map, markers[index]);
  }
}

function hideInfoWindow(index) {
  if (markers[index]?.infowindow) {
    markers[index].infowindow.close();
  }
}

function clearMarkers() {
  markers.forEach(marker => marker.setMap(null));
  markers = [];
}

function panToMarker(lat, lng) {
  map.panTo(new kakao.maps.LatLng(lat, lng));
}

function openBreedDetails(breedCode) {
  window.location.href = `/pedia/${breedCode}`;
}

// 견종 목록 렌더링
function renderBreeds() {
  const grid = document.getElementById('breedGrid');
  grid.innerHTML = '';

  Object.entries(breedData).slice(0, 8).forEach(([key, breed]) => {
    const card = document.createElement('div');
    card.className = 'breed-card';
    card.setAttribute('data-breed', key);
    card.onclick = () => openBreedDetails(key);

    card.innerHTML = `
                    <div class="breed-image"></div>
                    <div class="breed-name">${breed.name}</div>
                    <div class="updated">Updated today</div>
                `;

    grid.appendChild(card);
  });
}

// 페이지네이션 초기화
function initializePagination() {
  const paginationLinks = document.querySelectorAll('.pagination a');
  paginationLinks.forEach(link => {
    link.addEventListener('click', (e) => {
      e.preventDefault();
      if (!link.classList.contains('disabled')) {
        updatePage(link.textContent);
      }
    });
  });
}

// 페이지 업데이트
function updatePage(pageNumber) {
  const paginationLinks = document.querySelectorAll('.pagination a');
  paginationLinks.forEach(link => {
    link.classList.remove('active');
    if (link.textContent === pageNumber) {
      link.classList.add('active');
    }
  });
}

// 디바운스 유틸리티
function debounce(func, wait) {
  let timeout;
  return function executedFunction(...args) {
    const later = () => {
      clearTimeout(timeout);
      func(...args);
    };
    clearTimeout(timeout);
    timeout = setTimeout(later, wait);
  };
}

// 초기화
document.addEventListener('DOMContentLoaded', () => {
  renderBreeds();
  initializeSearch();
  initializePagination();
});