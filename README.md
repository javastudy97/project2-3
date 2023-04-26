<p align="center">
  <img src="https://user-images.githubusercontent.com/116870617/231791531-9e7ee801-a462-4b7a-977d-1e56b195e28b.png">
</p>

# KICKOFF (킥오프)
## Open API와 챗봇을 활용한 축구 동호회 운영/관리 그룹웨어 
## [프로젝트 소개 PDF](https://drive.google.com/file/d/1hKRz8V7S5cDmN2NThJtAIUazAXCb3UvG/view)
## [프로젝트 DB](https://drive.google.com/file/d/1Sq_t-sHVyWNhHz1W7dYdbGJk2qUlsRTR/view)
## [프로젝트 DB ERD](https://drive.google.com/file/d/1JvddvhrdoV1i78wkrrgPDKJa1tq9k7LW/view?usp=share_link)

## 프로젝트 기간 - 2023.03.14 ~ 2023.04.26

## 프로젝트 소개
- Open API(우편번호, 날씨, 일정관리, 조직관리 등)를 활용한 동호회 운영/관리용 그룹웨어입니다.
- 일정, 결재, 회계, 공지사항, 게시판 등 조직관리에 필요한 전반적인 기능들을 구현하였습니다.
- 기능 단위별로 패키지를 나누어 merge시 git 충돌을 최소화 하였습니다. 
- 네이티브쿼리 사용시 발생할 수 있는 컬럼인식 오류를 방지하고, 가급적 JPA의 기본 쿼리메소드(JPQL)만으로 비즈니스 로직이 구현 가능하도록 테이블간 연관관계를 설정하였습니다.
- Komoran(한국어 형태소 분석기)을 활용한 시나리오형 챗봇을 구현하였습니다.
- Github Actions와 AWS EC2를 기반으로 CI/CD(지속통합/지속배포) 환경을 구축하였습니다.
- OpenWeather API, 영화진흥위원회 API, 공공테이터 포털을 이용해서 날씨, 영화, 버스 API 활용해서 날씨, 영화, 버스노선 정보 조회구현

## 개발 환경
- `Language` : Java 11, HTML5, CSS3, JavaScript
- `IDE` : IntelliJ IDEA, Visual Studio Code
- `Framework` : Springboot
- `Database` : MySQL
- `Template Engine` : Thymeleaf 
- `ORM` : JPA <br>

## 팀 구성 및 역할
#### 팀장 : 김필수 <br>
- 프로젝트 일정 관리 및 발표준비
- 소스 통합 및 형상관리
- 날씨(Openweathermap), 우편번호(다음 우편번호) API 연동
- 결재서류 승인/반려 처리 구현 <br>

#### 팀원 : 김현기 <br>
- 회계내역(수입/지출) CRUD 구현
- Naver API(workplace, work) 연동 <br>

#### 팀원 : 이정모 <br>
- 근태관리 구현
- 공식일정, 개인일정 CRUD 구현 
- 일정관리(fullcalender) API 연동 <br>

#### 팀원 : 장기운 <br>
- 결재관리 CRUD 구현
- 조직관리 CRUD 구현
- Komoran 기반 챗봇 구현 <br>

## 팀원 : 김홍록 <br>
- 공지사항, 커뮤니티 게시판 CRUD 구현
- 공통요소(fragment) 디자인
- Github Actions, AWS EC2 기반 CI/CD 구현 <br>

### 공지사항, 커뮤니티 게시판 CRUD 구현 화면<br>
#### 공지사항 조회<br>
![image](https://user-images.githubusercontent.com/116870683/234513684-b7e949bf-f353-4aec-b39e-e4cb9eaace05.png)
#### 공지사항 추가<br>
![image](https://user-images.githubusercontent.com/116870683/234514035-a3eadf6d-4b4f-4ddc-92e0-4324da5225ae.png)




