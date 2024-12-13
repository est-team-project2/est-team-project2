// Global.css
import { createGlobalStyle } from 'styled-components';

export const GlobalStyle = createGlobalStyle`
  * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: 'Noto Sans KR', sans-serif;
  }

  body {
    background-color: #f5f5f5;
  }

  a {
    text-decoration: none;
    color: inherit;
  }

  button {
    border: none;
    cursor: pointer;
  }
`;

// App.js
import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { GlobalStyle } from './styles/Global';
import MainPage from './pages/MainPage';
import LoginPage from './pages/LoginPage';

function App() {
  return (
    <Router>
      <GlobalStyle />
      <Routes>
        <Route path="/" element={<MainPage />} />
        <Route path="/login" element={<LoginPage />} />
      </Routes>
    </Router>
  );
}

export default App;

// components/Header.js
import React from 'react';
import styled from 'styled-components';
import { Link } from 'react-router-dom';

const HeaderContainer = styled.header`
  background-color: #FFD98E;
  padding: 1rem 2rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const Logo = styled.h1`
  font-size: 1.5rem;
  color: #4A4A4A;
`;

const Nav = styled.nav`
  display: flex;
  gap: 1rem;
`;

const StyledButton = styled.button`
  padding: 0.5rem 1rem;
  border-radius: 20px;
  background-color: white;
  color: #4A4A4A;
  font-weight: bold;

  &:hover {
    background-color: #f0f0f0;
  }
`;

function Header() {
  return (
    <HeaderContainer>
      <Logo>뭉게디아</Logo>
      <Nav>
        <Link to="/login">
          <StyledButton>로그인</StyledButton>
        </Link>
        <Link to="/signup">
          <StyledButton>회원가입</StyledButton>
        </Link>
      </Nav>
    </HeaderContainer>
  );
}

export default Header;

// pages/MainPage.js
import React from 'react';
import styled from 'styled-components';
import Header from '../components/Header';

const MainContainer = styled.main`
  max-width: 1200px;
  margin: 0 auto;
  padding: 2rem;
`;

const Banner = styled.div`
  width: 100%;
  height: 300px;
  background-color: #E6F3FF;
  border-radius: 10px;
  margin-bottom: 2rem;
  position: relative;
  overflow: hidden;
`;

const DogGrid = styled.div`
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
  gap: 2rem;
`;

const DogCard = styled.div`
  background-color: white;
  border-radius: 10px;
  padding: 1rem;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);

  img {
    width: 100%;
    height: 200px;
    object-fit: cover;
    border-radius: 8px;
  }

  h3 {
    margin: 1rem 0;
    color: #4A4A4A;
  }
`;

function MainPage() {
  const dogBreeds = [
    { name: 'Corgi', updated: 'Updated today' },
    { name: 'German Shepherd', updated: 'Updated today' },
    { name: 'Maltipoo', updated: 'Updated today' },
    // ... 추가 견종들
  ];

  return (
    <>
      <Header />
      <MainContainer>
        <Banner>
          {/* 배너 이미지와 콘텐츠 */}
        </Banner>
        <DogGrid>
          {dogBreeds.map((dog, index) => (
            <DogCard key={index}>
              <img src={`/images/${dog.name.toLowerCase()}.jpg`} alt={dog.name} />
              <h3>{dog.name}</h3>
              <p>{dog.updated}</p>
            </DogCard>
          ))}
        </DogGrid>
      </MainContainer>
    </>
  );
}

export default MainPage;

// pages/LoginPage.js
import React from 'react';
import styled from 'styled-components';

const LoginContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f5f5f5;
`;

const LoginForm = styled.form`
  background-color: white;
  padding: 2rem;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
  width: 100%;
  max-width: 400px;
`;

const Title = styled.h2`
  text-align: center;
  margin-bottom: 2rem;
  color: #4A4A4A;
`;

const Input = styled.input`
  width: 100%;
  padding: 0.8rem;
  margin-bottom: 1rem;
  border: 1px solid #ddd;
  border-radius: 4px;
  
  &:focus {
    outline: none;
    border-color: #FFD98E;
  }
`;

const LoginButton = styled.button`
  width: 100%;
  padding: 0.8rem;
  background-color: #FFD98E;
  color: #4A4A4A;
  border-radius: 4px;
  font-weight: bold;
  
  &:hover {
    background-color: #FFD070;
  }
`;

function LoginPage() {
  const handleSubmit = (e) => {
    e.preventDefault();
    // 로그인 로직 구현
  };

  return (
    <LoginContainer>
      <LoginForm onSubmit={handleSubmit}>
        <Title>로그인</Title>
        <Input type="email" placeholder="이메일" required />
        <Input type="password" placeholder="비밀번호" required />
        <LoginButton type="submit">로그인</LoginButton>
      </LoginForm>
    </LoginContainer>
  );
}

export default LoginPage;
