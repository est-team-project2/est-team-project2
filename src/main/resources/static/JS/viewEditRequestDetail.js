function handleApprove(reqId, userEmail) {
  // // 부모 요소에서 data-* 속성 읽기
  // const postItem = button.closest('.post-item'); // 버튼의 부모 요소 찾기
  // const code = postItem.getAttribute('data-board-id');
  // const memberEmail = postItem.getAttribute('data-member-email');
  //
  // console.log('Board ID:', boardId);
  // console.log('Member Email:', memberEmail);

  // Fetch API를 통해 수정 승인 요청 보내기

  console.log(reqId);
  console.log(userEmail);

  fetch('/RequestAccept', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      "code": reqId,
      "memberEmail": userEmail
    }),
  })
  .then(response => {
    if (!response.ok) {
      throw new Error('네트워크 응답 오류');
    }
    return response.json();
  })
  .then(data => {
    alert('수정 요청이 승인되었습니다.');
    window.location.reload(); // 필요시 페이지 리로드
  })
  .catch(error => {
    console.error('에러 발생:', error);
  });
}

function handleDecline(reqId, userEmail) {
  // // 부모 요소에서 data-* 속성 읽기
  // const postItem = button.closest('.post-item'); // 버튼의 부모 요소 찾기
  // const code = postItem.getAttribute('data-board-id');
  // const memberEmail = postItem.getAttribute('data-member-email');
  //
  // console.log('Board ID:', boardId);
  // console.log('Member Email:', memberEmail);

  // Fetch API를 통해 수정 승인 요청 보내기

  console.log(reqId);
  console.log(userEmail);

  fetch('/RequestDecline', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      "code": reqId,
      "memberEmail": userEmail
    }),
  })
  .then(response => {
    if (!response.ok) {
      throw new Error('네트워크 응답 오류');
    }
    return response.json();
  })
  .then(data => {
    alert('수정 요청이 승인되었습니다.');
    window.location.reload(); // 필요시 페이지 리로드
  })
  .catch(error => {
    console.error('에러 발생:', error);
  });
}

function handleShowDetail(info) {

  const requestCode = info.pediaEditRequestCode;

  fetch(`/checkAllContents?pediaEditRequestCode=${encodeURIComponent(
      requestCode)}`)
  .then(response => {
    if (!response.ok) {
      throw new Error('네트워크 응답 오류');
    }
    return response.json();
  })
  .then(data => {

    // 로케이션.리디렉션

    alert('수정 요청이 승인되었습니다.');
    window.location.reload(); // 필요시 페이지 리로드
  })
  .catch(error => {
    console.error('에러 발생:', error);
  });
}
