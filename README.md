# be-chess
소프티어 부트캠프 2기 체스 프로젝트

## 미션 1 - 폰 생성
- [x] PawnTest 클래스 생성
  - [x] create() 테스트 메소드 작성
    - [x] 흰색 폰 생성
    - [x] 검은색 폰 생성
- [x] Pawn 클래스 구현
- [x] 리팩토링
  - [x] 문자열 값 중복 제거를 위해 local variable 추가
  - [x] Pawn 생성 및 결과 확인 코드의 중복 제거를 위해 메소드 추가


## 미션 2 - 체스판 생성
 - [x] Board 클래스 구현
   - [x] ArrayList로 여러 개의 Pawn 관리
   - [x] Pawn 추가 기능
 - [x] PawnTest에 색이 없는 Pawn 생성 테스트 추다
 - [x] 색을 상수로
 - [x] BoardTest 추가
   - [x] Pawn를 체스 판에 추가
 - [x] 패키지 분리
 - [x] Pawn 외의 객체 추가시 에러 발생시키기
 - [x] 중복제거


## 미션 3 - 체스판 초기화
- [ ] Pawn에 색, 출력할 값을 받는 생성자 추가
- [ ] Pawn에 출력할 값을 리턴하는 getRepresentation 함수 추가

- [ ] Board에 8 * 8로 구성된 체스판을 초기화하는 함수 initialize를 추가
- [ ] Board에 초기화 결과를 화면에 출력하는 print() 함수 구현
  - 검은색 Pawn은 대문자 P, 흰색은 소문자 p로 표시
- [ ] Board에 흰색 Pawn과 검은색 Pawn을 저장하는 List를 추가
- [ ] Board 중복 코드 제거 리팩토링
- [ ] main() 구현
  - start 입력시 Board init 후 print되어야 함
