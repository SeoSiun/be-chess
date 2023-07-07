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
- [x] Pawn에 색, 출력할 값을 받는 생성자 추가
- [x] Pawn에 출력할 값을 리턴하는 getRepresentation 함수 추가

- [x] Board에 8 * 8로 구성된 체스판을 초기화하는 함수 initialize를 추가
- [x] Board의 초기화 결과를 출력할 수 있도록, String을 리턴하는 print() 함수 구현
  - 검은색 Pawn은 대문자 'P', 흰색은 소문자 'p', 빈칸은 '.'으로 표시
- [x] Board에 흰색 Pawn과 검은색 Pawn을 저장하는 List를 추가
- [x] Board 중복 코드 제거 리팩토링
    - Pawn 목록의 representation들을 하나의 string으로 만들어주는 부분을 함수로 구현
  
- [x] main() 구현
  - start 입력시 Board init 후 print되어야 함
  - end 입력시 종료되어야 함


## 미션 4 - 기물 배치
- [x] "\n" 반복코드 제거 => System.getProperty("line.separator") 사용
  - new line 문자는 운영체제마다 달라질 수 있음!!
  - utils.StringUtils 클래스를 추가해 해당 기능을 유틸리티 메소드로 분리한다.
- [x] Pawn 클래스 명을 좀 더 일반적인 이름인 Piece로 rename
  - 색상, 이름(기물 종류: pawn, knight,...)을 가짐
  - private 생성자를 가져야 하며 인스턴스 생성 후에는 상태를 변경할 수 없어야함(값 객체)
  - [x] 색과 이름을 받아 Piece 객체를 생성하는 팩토리 메소드를 구현
- [x] 전체 기물 상태를 볼 수 있는 체스판 구현 및 테스트
- [x] 검은색 말과 흰색 말을 구분할 수 있는 메소드 추가
- [x] 리팩토링


## 미션 5 - 기물 위치 부여 및 점수계산
- [x] 기물의 색(Color), 기물의 종류(Type)에 따른 enum 구현
  - Color.None -> Color.NO_COLOR로 수정
  - Type.None -> Type.NO_PIECE로 수정
  - Color, Type을 Piece 클래스 내부로 옮기기
  - getRepresentation 대신 getWhiteRepresentation, getBlackRepresentation
- [x] 팩토리 메소드에서 enum 사용
  - 기물이 존재하지 않는 Piece도 생성할 수 있도록 Piece.createBlank() 메소드 구현
  - Piece.getType() 함수 구현
- [x] 팩토리 메소드 리팩토링
  - createWhite(initialType), createBlack(initialType) 구현을 통해 중복 제거
  
- [x] 체스판의 모든 칸을 Piece로 초기화 -> ArrayList<ArrayList<Piece>>
  - ArrayList<Rank> 구조로 변경하기 (cf) 체스판에서 row를 Rank라고 부름)
- [x] 기물 종류, 색에 해당하는 기물 개수를 반환하는 로직 구현 (호출 시에 개수를 계산하게)
- [x] 주어진 위치의 기물을 가져오는 메소드 구현 (체스판 좌표 구성)
  - 위치를 xy 좌표로 변환하는 작업의 중복을 제거하기 위해 Position class 구현
- [x] 임의의 기물을 체스판에 추가하는 기능
  - 빈 체스판을 초기화하는 함수 추가
  
- [x] 체스 프로그램 점수 계산 기능 추가
  - 한 번에 한 쪽의 점수만을 계산해야함
  - 현재까지 남아있는 기물에 따라 점수 계산
  - 같은 file에 폰이 여러 개 있는 경우 0.5점으로 계산
- [x] 기물의 점수가 높은 순으로 정렬하는 기능 추가
  - 검은색과 흰색 각각을 높은순서/낮은 순서로 정렬해보기
  - java collection의 정렬 기능을 활용해 점수가 높은 순서에서 낮은 순으로 정렬한다.

- [ ] 리팩토링
  - 인터페이스 추출
  - 요구사항을 만족하는 "최소한의 클래스, 메소드, 중복을 없애는 것"이 중요