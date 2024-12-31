### 📎 프로젝트팀: 내 이름은 손목터미널 2조 

## 멍피디아 **강아지 백과사전 멍피디아**

## 👀 서비스 소개
**설명:** 회원이 조회, 수정할 수 있는 강아지 백과와 반려견 관련 다양한 서비스를 제공하는 웹 서비스

## 📅 프로젝트 기간
**기간:** 2024년 12월 13일 ~ 2024년 12월 30일 (3주)

## 개발 일정

- 2024.12.11 ~ 2024.12.13 : 기능 명세, 플로우차트, ERD 작성
- 2024.12.16 ~ 2024.12.26 : 기능 개발
- 2024.12.27 ~ 2024.12.30 : 최종 통합 및 디버깅
 
## ⭐ 주요 기능

## 백과 서비스

- 회원들이 참여하여 강아지에 대한 백과를 조회하거나 수정할 수 있는 서비스
- 전문가나 관리자는 백과를 추가하거나, 회원들이 보낸 수정 요청을 검토하고 승인/거절 가능

## 게시판 서비스

- 반려견 관련 정보를 자유롭게 나누는 게시판 서비스

## 반려견 서비스

- 지도 api를 활용한 주변 서비스 조회 (반려 동물 용품, 애견 동반, 애견 호텔, 애견 샵, 동물 병원)
- 건강 계산기(예방 접종 일자 계산기, 나이 계산기)

## ⛏ 기술 스택
| 구분          | 내용 |
|---------------|---------|
| 기본 사용언어     | <img src="https://img.shields.io/badge/java-f89820?style=for-the-badge&logo=java&logoColor=white"> |
| Frontend 사용언어     | <img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=HTML5&logoColor=white"/> <img src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=CSS3&logoColor=white"/> <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=JavaScript&logoColor=white"/> |
|  Backend 프레임워크     | <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/springsecurity-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white"> |
| 라이브러리     | <img src="https://img.shields.io/badge/bootstrap-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white"> <img src="https://img.shields.io/badge/Kakao Map Api-F7DF1E?style=for-the-badge&logo=KaKao Map Api&logoColor=black"> <img src="https://img.shields.io/badge/thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white"> |
| 개발 도구     | <img src="https://img.shields.io/badge/intelliJ-084CCF?style=for-the-badge&logo=intellijidea&logoColor=white"> |
| 서버 환경     | <img src="https://img.shields.io/badge/Live Server-084CCF?style=for-the-badge&logo=server&logoColor=white"> |
| 데이터베이스   | <img src="https://img.shields.io/badge/mariaDB-003545?style=for-the-badge&logo=mariaDB&logoColor=white"> |
| 협업 도구     | <img src="https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=Git&logoColor=white"/> <img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=GitHub&logoColor=white"/> |

## ⚙ 시스템 아키텍처
![시스템 아키텍처]

## 📌 SW 유스케이스
![SW 유스케이스]

### 회원가입

<img width="500" alt="signin" src="https://github.com/user-attachments/assets/85f3cf3f-373d-48b4-8800-fe1cf8453328">

### 비밀번호 찾기

<img width="500" alt="findPW" src="https://github.com/user-attachments/assets/96dd236a-de06-4129-bb8c-0bd4e5169280">

## 화면 설계

### 초기 화면 설계 (메인 페이지)

![화면설계 ui](https://github.com/user-attachments/assets/0383632e-bf0f-4de5-ab83-811ce4fd26cd)

## 📌 서비스 흐름도
![서비스 흐름도]

## API 명세서

### 로그인 및 회원 정보 기능

| 주소               | 기능      | 비회원 | 회원 | 전문가 | 관리자 |
|------------------|---------|-----|----|-----|-----|
| `/`              | 홈       | O   | O  | O   | O   |
| `/signin`        | 로그인     | O   | O  | O   | O   |
| `/signup`        | 회원가입    | O   | O  | O   | O   |
| `/find-password` | 비밀번호 찾기 | O   | O  | O   | O   |

| 주소                    | 기능       | 비회원 | 회원 | 전문가 | 관리자 |
|-----------------------|----------|-----|----|-----|-----|
| `/my`                 | 회원정보(자신) | X   | O  | O   | O   |
| `/my/update-password` | 비밀번호 변경  | X   | O  | O   | O   |

### 관리자 기능

| 주소                               | 기능                | 비회원 | 회원 | 전문가 | 관리자 |
|----------------------------------|-------------------|-----|----|-----|-----|
| `/admin`                         | 관리자 페이지           | X   | X  | X   | O   |
| `/admin/manageMember`            | (관리자) 회원 목록       | X   | X  | X   | O   |
| `/admin/manageMember{member_id}` | (관리자) 회원 상세 및 관리  | X   | X  | X   | O   |
| `/admin/managePedia`             | (관리자) 백과 목록       | X   | X  | X   | O   |
| `/admin/managePedia/{pedia_id}`  | (관리자) 백과 상세 및 관리  | X   | X  | X   | O   |
| `/admin/managePost`              | (관리자) 게시글 목록      | X   | X  | X   | O   |
| `/admin/managePost/{post_id}`    | (관리자) 게시글 상세 및 관리 | X   | X  | X   | O   |

### 백과 기능

| 주소                                     | 기능           | 비회원 | 회원 | 전문가 | 관리자 |
|----------------------------------------|--------------|-----|----|-----|-----|
| `/pedia`                               | 견종백과 목록      | O   | O  | O   | O   |
| `/pedia/detail/{pedia_id}`             | 견종백과 상세      | O   | O  | O   | O   |
| `/pedia/edit-request/{pedia_id}`       | 견종백과 수정 요청   | X   | O  | O   | O   |
| `/pedia/history/{pedia_id}`            | 견종백과 버전 목록   | O   | O  | O   | O   |
| `/pedia/history/detail/{pedia_id}`     | 견종백과 버전 상세   | O   | O  | O   | O   |
| `/view-edit-request`                   | 백과 수정 요청 조회  | X   | X  | O   | O   |
| `/view-edit-request/detail/{pedia_id}` | 백과 수정 요청 상세  | X   | X  | O   | O   |
| `/registerOnlyBreed`                   | 견종백과 새 견종 등록 | X   | X  | X   | O   |

### 게시판 기능

| 주소                        | 기능         | 비회원 | 회원 | 전문가 | 관리자 |
|---------------------------|------------|-----|----|-----|-----|
| `/posts`                  | 게시글 목록(기본) | X   | O  | O   | O   |
| `/posts/{post_id}`        | 게시글 조회     | X   | O  | O   | O   |
| `/posts/new`              | 게시글 작성     | X   | O  | O   | O   |
| `/posts/{post_id}/update` | 게시글 수정     | X   | O  | O   | O   |

### 반려견 서비스 기능

| 주소                | 기능     | 비회원 | 회원 | 전문가 | 관리자 |
|-------------------|--------|-----|----|-----|-----|
| `/map`            | 지도 api | O   | O  | O   | O   |
| `/dog-calculator` | 건강 계산기 | O   | O  | O   | O   |

## 📌 ER 다이어그램
![ER 다이어그램]

![2팀 erd](https://github.com/user-attachments/assets/22faefc8-255f-47a6-9ebf-df3b73f106f3)

## 🖥 화면 구성
![화면 구성]

### 메인 페이지

<img width="500" alt="index-로그인-전" src="https://github.com/user-attachments/assets/b44cbb00-8edc-4482-9a83-eaf1a0631986" />

### 로그인 및 회원 정보 페이지

<img width="500" alt="my" src="https://github.com/user-attachments/assets/144def44-4791-4a2f-8af2-19082a082d6d" />
<img width="500" alt="signin" src="https://github.com/user-attachments/assets/140da092-bec6-4699-8a74-1a52d54bc74b" />
<img width="500" alt="changePass" src="https://github.com/user-attachments/assets/d0953430-a9cb-49a3-ba3e-169ec1180463" />

### 관리자 페이지

<img width="500" alt="manageMember" src="https://github.com/user-attachments/assets/b7e37b6f-d80b-4cd4-b147-ca2209f48211" />
<img width="500" alt="managePedia" src="https://github.com/user-attachments/assets/a0947e3a-10df-4d96-af49-6b4dffa12bb3" />
<img width="500" alt="managePost" src="https://github.com/user-attachments/assets/a81f0d9f-1f6c-49da-ae22-40320dde6023" />

### 백과 페이지

<img width="500" alt="pedia" src="https://github.com/user-attachments/assets/1133f47f-9ac7-4458-8246-f26b5ae99146" />
<img width="500" alt="pediaDetail" src="https://github.com/user-attachments/assets/ba1e424a-c73a-4e00-b2b3-5c8a20d3f672" />
<img width="500" alt="pediaHistory" src="https://github.com/user-attachments/assets/a62f212f-82c8-4cc0-b495-81c4991a2b08" />
<img width="500" alt="pediaHistoryDetail" src="https://github.com/user-attachments/assets/b4eb4ce5-e0b1-44e8-b74d-49389150674a" />
<img width="500" alt="editRequest" src="https://github.com/user-attachments/assets/2c4a80e4-342a-462b-bf49-a4cf7f8d7a66" />

### 게시판 페이지

<img width="500" alt="community" src="https://github.com/user-attachments/assets/fd266cc1-86e5-4080-9ad1-a4e09cb8390d" />
<img width="500" alt="postDetail" src="https://github.com/user-attachments/assets/a63a6951-9d6f-413a-9512-4c23d5760f36" />

## 반려견 서비스 페이지

<img width="500" alt="map" src="https://github.com/user-attachments/assets/e6352b79-dd5a-4756-8814-4f56cc230e45" />
<img width="500" alt="dog-calculator" src="https://github.com/user-attachments/assets/e6bf2426-08b2-46e4-b37a-0914cf34a9b2" />



## 👨‍👩‍👦‍👦 팀원 역할

<table>
  <tr>
    <td align="center"><img src="https://item.kakaocdn.net/do/fd49574de6581aa2a91d82ff6adb6c0115b3f4e3c2033bfd702a321ec6eda72c" width="100" height="100"/></td>
    <td align="center"><img src="https://mb.ntdtv.kr/assets/uploads/2019/01/Screen-Shot-2019-01-08-at-4.31.55-PM-e1546932545978.png" width="100" height="100"/></td>
    <td align="center"><img src="https://mblogthumb-phinf.pstatic.net/20160127_177/krazymouse_1453865104404DjQIi_PNG/%C4%AB%C4%AB%BF%C0%C7%C1%B7%BB%C1%EE_%B6%F3%C0%CC%BE%F0.png?type=w2" width="100" height="100"/></td>
    <td align="center"><img src="https://i.pinimg.com/236x/ed/bb/53/edbb53d4f6dd710431c1140551404af9.jpg" width="100" height="100"/></td>
    <td align="center"><img src="https://pbs.twimg.com/media/B-n6uPYUUAAZSUx.png" width="100" height="100"/></td>
  </tr>
  <tr>
    <td align="center"><strong>조경천</strong></td>
    <td align="center"><strong>이경돈</strong></td>
    <td align="center"><strong>양성희</strong></td>
    <td align="center"><strong>정석환</strong></td>
    <td align="center"><strong>오상훈</strong></td>
  </tr>

  <tr>
   <td align="center"><b>PM/Front/Back/DB</b>
    <br>로그인, 관리자 기능 개발
  </td>

   <td align="center"><b>Front/Back/DB</b>
    <br>백과, 게시판 기능 개발
  </td>
  
  <td align="center"><b>Front/Back/DB</b>
    <br>백과 기능 개발
  </td>

  <td align="center"><b>Front/Back/DB</b>
    <br>로그인, 백과 기능 개발 
  </td>
  
  <td align="center"><b>Front/Back</b>
   <br>프론트, 지도 및 계산기 기능 개발
  </td>
 </tr>
</table>

## 시연 영상

### 회원가입, 로그인

![회원가입 로그인](https://github.com/user-attachments/assets/81104947-baff-42c6-8ceb-8aa475c6f406)

### oauth2 로그인, 닉네임 변경

![oauth2로그인 닉네임변경](https://github.com/user-attachments/assets/a5afcd7c-362c-47a5-85e3-1ba2a7feeb87)

### 권한별 서비스

![권한별서비스](https://github.com/user-attachments/assets/aa67e4b9-01be-4ca4-9b26-511846a11d21)

### 관리자 페이지

![관리자페이지](https://github.com/user-attachments/assets/20913b34-8fd2-4de3-8df7-fd8096a04555)

