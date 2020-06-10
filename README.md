# :notebook_with_decorative_cover: SSafari <img src="https://img.shields.io/badge/public_ver-1.0-blue">

<br>

## :one: Overview

- SSAFY 교육생과 SSAFY에 관심 있는 사람들을 위한 커뮤니티 사이트
- SSAFY를 위한 활발한 생태계를 지향한다는 의미를 담아서 `SSafari` 라고 서비스 이름을 정했다.
- Project 기간 : 20.01.13 ~ 20.02.21

<br>

## :two: Service Introduction

- 본 프로젝트에서 구현한 것은 `지역`, `기수`, `취업 여부` 등에 상관없이 `SSAFY 교육생`이라면 누구나 참여할 수 있는 활발한 커뮤니티 웹 서비스입니다. 물론 SSAFY 교육생이 아니더라도 관심 있는 사람은 누구든지 들어와서 서비스를 이용할 수 있지만 몇 가지 기능들은 제한됩니다.

- `SSAFY 교육생`임을 인증받기 위해서 새로 회원가입한 사람은 본인의 `SSAFY 명찰`을 업로드하여 `관리자`의 승인을 받아야 합니다. 관리자는 관리자 대시보드를 통해 싸피 명찰의 진위여부를 확인하고 승인할 수 있습니다.

- 커뮤니티 서비스인만큼 `자유게시판`, `취업게시판`, `주변 맛집 리뷰 게시판`, `코드 리뷰 게시판`, `스터디 게시판` 등의 기본 게시판을 갖고 있습니다.  기본 게시판 이외에도 사용자는 본인이 형성하고자 하는 커뮤니티에 맞게 <u>게시판을 생성</u>할 수 있고, 이 또한 관리자의 승인 하에 만들 수 있습니다.

- 각 게시판은 지역별로 구분되고, 검색 기능을 포함하고 있어 손 쉽게 정보에 다가갈 수 있습니다.

<br>

## :three: Tech Stack & Architecture

![architecture](https://user-images.githubusercontent.com/52685250/84230908-f7f00d00-ab27-11ea-9268-b1e165ce89e5.PNG)

:round_pushpin: <b>Frontend</b> : `Vue.js`

:round_pushpin: <b>Backend</b> : `Spring Boot`

:round_pushpin: <b>Database</b> : `MySQL`

:round_pushpin: <b>Deploy</b> : `Nginx`, `AWS EC2`

:pushpin: <b>Using API</b> : `Facebook Login API`, `Kakao Map API`, `Github API`, `Imgur API`

:round_pushpin: <b>Development Enviornment</b> : Windows 10, Node.js 12 or higher, Vue CLI 4 or higher

:round_pushpin: <b>Using Editor</b> : Visual Studio Code

<br>

## :four: Quick Start

:heavy_check_mark: <b>Requirement</b>

- `Node.js` : 12 or higher
- `Vue CLI` : 4.0 or higher

:heavy_check_mark: <b>Installation</b>

```bash
$ cd ssafy-frontend
$ npm install
```

:heavy_check_mark: <b>Usage</b>

```bash
$ npm run serve
```

<br>

## :five: Homepage Configuration

- 메인화면

![main](https://user-images.githubusercontent.com/52685250/84230909-f9213a00-ab27-11ea-9dd4-c4fb60157bad.PNG)

- 게시판 리스트

![](https://user-images.githubusercontent.com/52685250/79038377-bf10e600-7c13-11ea-83a5-320b046060ae.jpg)

- github 계정을 연동한 경우 MY PAGE 에서 내 github 계정에 있는 repository 들을 볼 수 있습니다.

![](https://user-images.githubusercontent.com/52685250/79038376-bddfb900-7c13-11ea-97ae-2b12587d78b8.jpg)