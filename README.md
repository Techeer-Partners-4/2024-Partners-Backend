# ✂️ URL Shortener 앱 ✂️

---

<br>

## 안내사항

- 본 레포지토리를 fork하여 과제를 수행하고, 완료시 PR을 보냅니다.
- 과제의 소스코드는 본인의 GitHub 레포지토리에 **Public**으로 올려주세요.
- 진행 간 문의사항은 이 레포지토리의 Issue로 등록해주세요.
- 구현 내용은 [README.md](http://readme.md/) 하단에 이어서 작성해 주세요. ㅇ

<br>

## 기본 요구사항

- 아래 제시된 라이브러리, 프레임워크를 활용하여 URL Shortener 앱을 구현합니다.
- 일관된 코딩 컨벤션을 유지해주세요.
- 기능 당 커밋은 필수입니다.

<br>

## 기술 스택별 요구사항

### 1. SpringBoot + Java

- 빌드 도구는 gradle를 사용해주세요.
- Spring data JPA를 사용해주세요.

### 2. Python + Django

- 데이터베이스는 db.sqlite3를 사용해주세요.

<br>

---

# 기능

### 1. Short Link 생성하기

- 원본 URL, 줄여진 URL, 해시값, 생성일자를 포함해야합니다.
- 원본 URL 해시값을 엔드포인트 뒤에 붙여 줄여진 URL을 생성합니다.
    - 줄여진 URL 형식 예시: [http://localhost:8000/short-links/{hash}](http://localhost:8000/short-links/)
- 원본 URL로 만든 hash 값이 데이터베이스에 존재하면 저장된 그 데이터를 반환합니다.

<br>

### 2. Short Link 리다이렉트하기

- 줄여진 URL으로 요청을 보냈을 때, 원본 URL로 Redirect 되어야 합니다.

<br> 

### 3. 전체 Links 조회하기

- 원본 URL, 줄여진 URL, 해시값과 같은 정보를 반환합니다.
- 최근 생성일자 순으로 정렬합니다.
- 삭제 처리된 링크의 정보는 조회하지 않도록 합니다.

<br>

### 4. Short Link 삭제하기

- Soft Delete로 구현해야합니다.
- 조회 기능에서 삭제 처리된 URL은 조회하지 않도록 합니다.
- Redirect 기능에서 삭제 처리된 URL은 접속되지 않도록 합니다.

<br> <br>

## [📃 Swagger 문서화]

- Swagger 문서화 코드를 작성합니다. (추가기능)

<br><br>

---

# 기여해주신 분

- [백한결](https://github.com/baekhangyeol) 👾
- [조희은](https://github.com/kubit2) 👾
