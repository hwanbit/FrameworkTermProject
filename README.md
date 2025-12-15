# 📋 Framework Term Project - Web Login System

이 프로젝트는 Spring Boot 프레임워크를 활용하여 구현한 웹 로그인 및 회원가입 시스템입니다. Spring Security를 이용한 안전한 인증 처리와 Spring Data JPA를 통한 데이터베이스 연동을 핵심 기능으로 하고 있습니다.

---

## 🛠 기술 스택 (Tech Stack)

- **Language:** Java
- **Framework:** Spring Boot 3.5.3
- **Security:** Spring Security
- **Database Access:** Spring Data JPA
- **Template Engine:** Thymeleaf
- **Utility:** Lombok

---

## ✨ 주요 기능 (Features)

### 1. 회원가입 (Sign Up)
- **사용자 등록:** `MemberSignupDto`를 통해 아이디와 비밀번호를 입력받아 회원을 생성합니다.
- **중복 검사:** 이미 존재하는 아이디로 가입 시도 시 `IllegalStateException`을 발생시켜 중복 가입을 방지합니다.
- **비밀번호 암호화:** `PasswordEncoder`를 사용하여 비밀번호를 암호화한 후 DB에 저장합니다.
- **권한 부여:** 회원가입 시 기본적으로 `USER` 권한(Role)이 부여됩니다.

### 2. 로그인 (Sign In)
- **인증 처리:** Spring Security의 `UserDetailsService` 인터페이스를 구현한 `MemberService`를 통해 로그인 인증을 처리합니다.
- **사용자 조회:** `MemberRepository`를 통해 DB에서 사용자 정보를 조회하며, 존재하지 않을 경우 예외를 처리합니다.
- **세션 관리:** 로그인 성공 시 Spring Security가 자동으로 세션을 생성하고 관리합니다.

### 3. 바오패밀리 콘텐츠 (Contents)
- 바오 패밀리의 프로필과 사진, 관련 유튜브 채널 등의 정보를 확인할 수 있습니다.
- 인증된 사용자만 권한에 따라 단계별로 접근 가능한 보호된 콘텐츠 페이지를 제공합니다.

---

## 📂 프로젝트 구조 (Project Structure)

```
FrameworkTermProject/
│
├── src/
│   └── main/
│       ├── java/
│       │   └── kr/ac/kopo/sun/weblogin/
│       │       ├── dto/
│       │       │   └── MemberSignupDto.java          # 회원가입 데이터 전송 객체
│       │       │
│       │       ├── entity/
│       │       │   └── Member.java                   # 회원 엔티티 (JPA)
│       │       │
│       │       ├── repository/
│       │       │   └── MemberRepository.java         # 회원 데이터 접근 계층 (Spring Data JPA)
│       │       │
│       │       └── service/
│       │           └── MemberService.java            # 회원 비즈니스 로직 및 인증 처리
│       │
│       └── resources/
│           └── templates/
│               └── Signup.html                       # 회원가입 페이지 (Thymeleaf)
│
├── build.gradle                                      # Gradle 빌드 설정
└── README.md                                         # 프로젝트 문서
```

### 주요 파일 설명

- **`MemberSignupDto.java`**: 회원가입 시 클라이언트로부터 전달받는 데이터를 담는 DTO 클래스
- **`Member.java`**: 데이터베이스의 회원 테이블과 매핑되는 JPA 엔티티 클래스
- **`MemberRepository.java`**: Spring Data JPA를 사용한 회원 데이터 접근 인터페이스
- **`MemberService.java`**: 회원가입, 로그인 인증 등의 비즈니스 로직을 처리하는 서비스 계층
- **`Signup.html`**: Thymeleaf 템플릿 엔진을 사용한 회원가입 폼 페이지

---

## 🚀 시작하기 (Getting Started)

### 사전 요구사항
- Java JDK 17 이상
- Gradle 7.0 이상
- IDE (IntelliJ IDEA, Eclipse 등)
- 데이터베이스 (H2, MySQL, PostgreSQL 등)

### 실행 방법

1. **프로젝트를 클론(Clone) 합니다.**

```bash
git clone https://github.com/your-username/framework-term-project.git
cd FrameworkTermProject
```

2. **데이터베이스 설정을 확인합니다.**
   - `src/main/resources/application.properties` 파일에서 데이터베이스 연결 정보를 설정합니다.

3. **프로젝트를 빌드하고 실행합니다.**

```bash
./gradlew bootRun
```

또는 IDE에서 `WebLoginApplication.java` (메인 클래스)를 직접 실행합니다.

4. **웹 브라우저에서 접속합니다.**

```
http://localhost:8080
```

---

## 🔒 보안 설정 (Security Configuration)

- **비밀번호 암호화:** BCrypt 알고리즘을 사용하여 비밀번호를 안전하게 암호화합니다.
- **CSRF 보호:** Spring Security의 기본 CSRF 보호 기능이 활성화되어 있습니다.
- **권한 관리:** Role 기반 접근 제어(RBAC)를 통해 사용자 권한을 관리합니다.
