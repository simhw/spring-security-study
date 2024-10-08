# spring-security-study

- spring security 예시

## 라이브러리

- Spring Security 6.3.1
- Spring Session 3.3.1
- Spring OAuth2 Client 3.3.1

## 기능

- Username/Password 인증
    - AuthenticationProvider 기본 구현체 중 하나인 DaoAuthenticationProvider를 통해 인증
    - DaoAuthenticationProvider는 UserDetailsService를 사용하여 데이터베이스에 저장된 사용자 정보를 로드
- Spring Session 사용
    - Redis를 세션 저장소로 자동 설정
    - HttpSession에 저장된 SecurityContext를 Redis에 저장
    - 중앙 집중식 애플리케이션 세션 관리
      ![img1.png](./src/main/resources/static/img1.png)
- OAuth2 인증
    - 권한 부여, 클라이언트 인증 등 OAuth2 프로세스 구현
    - DefaultOAuth2UserService를 확장하여 사용자 정보를 처리
- JWT 인증
    - JwtAuthenticationFilter를 추가하여 사용자 인증 수행
    - JwtUtil에서 토큰 생성 및 토큰 유효성 검사 실행
    - 빈으로 등록된 AuthenticationManager를 주입받아 REST API에서 로그인 수행
      ![img2.png](./src/main/resources/static/img2.png)

## 참고

https://docs.spring.io/spring-security/reference/index.htmlspring
