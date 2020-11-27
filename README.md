# kw_team22_project_term

데이터베이스 : maridb
실습환경 : linux
사용언어 : Java Swing

관리자 아 이 디 : kwteam22
관리자 비밀번호 : 12345

프로젝트 내용 : 
요즘 사용률이 점차 늘고 있는 무인주문기를 만들었습니다.
원하는 상품을 고른 뒤 주문을 하면 계산완료가 됩니다.

평소에는 일반 손님으로 계산되지만 회원 가입을 하면 번호로 로그인이 가능합니다.
회원등급 : 일반손님 > 고객(핸드폰 번호) > 단골 손님(핸드폰번호 + 생년월일)
고객부터 7만원이상 구매시 자동으로 할인됩니다.

프로그램 종료는 프로그램 특성상 관리자 로그인 이후 가능합니다.
(프로젝트 설명 동영상에서 자세하게 설명드립니다.)

관리자 기능으로는 회원들이 산 금액과 회원들의 번호를 입력하면 그 회원의 정보와 여태까지 산 금액을 알 수 있습니다.

설치 방법 :
window 환경 혹은 linux 환경에서 파일이 제대로 인식이 되는 경우 main에서 실행시키시면 됩니다.
(linux 일 경우 데이터베이스가 다르면 수정하여서 작업 가능합니다)

파일이 인식이 안될 경우
JPA project 만들기 > hibernate library 추가(파일내에 존재) > maridb library 추가
이 순서로 진행하시면 됩니다.
(보통 window 환경일 때 이런 경우가 많았습니다)


