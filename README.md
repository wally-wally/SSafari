# 웹/모바일 프로젝트 (웹 기술)

## Sub PJT 1 - 반응형 단일 페이지 애플리케이션 구현          (20.01.13 ~ 20.01.17)

### 1. 소개

- 단일 페이지 애플리케이션(Single Page Application)의 개념을 이해하고 반응형 웹(Responsive Web)의 특징을 파악하여 사용자 친화적이고 다양한 디바이스에 최적화 된 웹을 구현 - 스켈레톤 프로젝트 바탕의 프로토타입 제작
- 웹의 기본 기능과 반응형 디자인으로 자신만의 포트폴리오로 발전 시킬 수 있는 기술 블로그의 토대를 만들고 다양한 API를 적용한 사용자 기능과 관리자(백오피스) 기능을 강화하여 개발자로서 활용성을 높게 가져갈 수 있는 SW 개발 비서 완성
  - 커스터마이징 - 해리포터 블로그
- 목표
  - 기본 HTML 및 CSS에 대한 이해
  - JavaScript(ES6)에 대한 이해
  - 웹 프로젝트의 구성과 필요 기능 명세서 작성에 대한 이해
  - 외부 프레임워크와 Open API 활용
  - SAP(Single Page Application)의 구조 이해
  - 반응형 웹(Responsive Web) 제작

### 2. 환경

- Frontend : Vue.js / Vuetify
- Backend : Firebase

### 3. 개발 환경 구성 (Windows)

(1) NPM 설치

(2) Vue.js, Vue-cli, Firebase 패키지 설지

```
npm install
npm install -g yarn
npm install -g @vue/cli
npm install vue
npm install -g firebase-tools
```

### 4. Firebase 서버 배포

(1) firebase 프로젝트 생성

(2) firebase SDK 확인 및 태그 첨부

(3) Auth 및 Cloud Firestore 확인

```
firebase login
firebase init

'npm run build'
firebase deploy
```

### 5. 배포 URL

- https://sub-pjt-1-1week.firebaseapp.com/

### 6. 추가

  - 로그인/로그아웃



## Sub PJT 2 -백엔드 구성 및 기본 기능 구현(20.01.20 ~ 20.01.31)

<h1>Team A5</h1>
### 1. Overview

  본 프로젝트의 주제는 `IT강의를 수강하고자 하는 사람(수강생)과 IT강의를 하고자하는 사람(강의자)을 매칭하는 서비스`를 개발하는 것입니다.



**서비스 이름 : iTeacher(IT관련 강의, 커뮤니티를 제공하는 서비스)**



  <h6>이번 Sub PJT 2에서 구현한 기능 및 환경은 다음과 같습니다.</h6>

- Sub PJT 1에서 백엔드로서 `firebase`를 사용했지만, `MySQL` DB를 사용하는 `Spring`으로 전환.

- 웹 페이지 화면을 추후 서비스에 걸맞는 UI로 변경.

- 서비스를 이용하는 회원들을 관리하기 위한 기초적인 기능으로서 `회원가입`, `로그인` 을 갖음.

- 회원은 자신의 `프로필` 화면에서 자신이 작성한 글을 확인할 수 있음.

- 회원은 게시판에서 권한에 따라서 글을 작성하거나, 수정하거나, 삭제할 수 있음. `CRUD`

- 회원은 각 게시글에서 댓글을 작성할 수 있음.

- 메인 페이지에서 `팀 소개` 페이지로 이동할 수 있음.

- etc.




<h6> 아래와 같은 기능이 추가될 예정입니다.</h6>

- 회원가입시 중복 여부 확인

- 소셜 로그인

- etc.

  

### 2. Prerequisites

 본 코드를 이용하기 위해서 아래와 같은 환경을 구축했는지 먼저 확인해 주시기 바랍니다. 

- `Node.js` - [Download & Install Node.js](https://nodejs.org/en/download/)이 필요합니다.  또한`npm package` 또는 `yarn package`를 설치했는지 확인해 주세요.
- `MySQL` - [Download & Install MySQL](https://www.mysql.com/downloads/)이 필요합니다. 저희는 `MySQL`을 DB로 사용합니다.
- `Java & Spring` - [Download & Install JDK](https://www.oracle.com/technetwork/java/javase/downloads/index.html) 이 필요합니다. 백엔드 프레임워크로서 `Java` 기반의 `Spring`을 사용합니다.
- `Vue.js` - [Download & Install Vue](https://vuejs.org/v2/guide/installation.html) Component기반의 SPA를 구현하기 위해 저희는 Vue.js를 프론트엔드 개발 도구로 사용합니다.



### 3. Setup & build

**Cloning The Gitlab Repository**

저희가 구축한 서비스의 코드를 살펴보기 위해 추천드리는 방법은 `s02p12b101` repository를 살펴보는 것입니다.

```bash
$ git clone https://github.com/meanjs/mean.git meanjs
```



**require npm package**

**A5**가 만든 서비스를 제대로 살펴보시려면 아래와 같은 `npm package`들을 필요로 합니다.

```bash
$ npm install
$ npm install -g @vue/cli
$ npm install vue
$ npm install vuetify
$ npm install vueperslides --save
$ npm install aos
```

- `vueperslides`: `Carousel` component를 위한 package입니다.
- `aos`: 화면의 fade 효과를 주기 위한 package입니다. 



### 4. Run

 환경 구축이 끝났다면 아래와 같은 명령어로 application을 실행시켜 주세요.

```bash
$ npm run serve
```

 프론트엔드를 `Vue.js`로 사용하기 때문에 `8080`포트를 사용합니다. 정상적으로 application이 실행되면 `localhost:8080`으로 접속해 서비스를 살펴볼 수 있습니다.

아래와 같은 화면이 나오면 성공.

![main](./images/main.PNG)

