function handleApprove(reqId, userEmail, reqBreed) {
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
  console.log(reqBreed)

  fetch('/RequestAccept', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      "code": reqId,
      "memberEmail": userEmail,
      "breed": reqBreed
    }),
  })
  .then(response => {
    if (!response.ok) {
      throw new Error('네트워크 응답 오류');
    }
    return response.text();
  })
  .then(data => {
    alert('수정 요청이 승인되었습니다.');
    // window.location.href = "/view-edit-request";
    location.reload();
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
      "memberEmail": userEmail,
    }),
  })
  .then(response => {
    if (!response.ok) {
      throw new Error('네트워크 응답 오류');
    }
    return response.text();
  })
  .then(data => {
    alert('수정 거절이 승인되었습니다.');
    location.reload();
  })
  .catch(error => {
    console.error('에러 발생:', error);
  });
}

function handleShowDetail(id) {

  fetch(`/viewEditRequestDetail?id=${encodeURIComponent(id)}`, {
    redirect: 'follow'
  })
  .then(response => {
    if (!response.ok) {
      throw new Error('네트워크 응답 오류');
    }
    return response.json();
  })
  .then(data => {

    console.log(data)
  })
  .catch(error => {
    console.error('에러 발생:', error);
  });
}