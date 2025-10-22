# ToDoList

## 📌 유틸 기능

- [ ] 구분자로 문자열을 분리한다
    - given: ("pobi,woni", ',') → then: ["pobi", "woni"]
    - given: ("pobi,,woni", ',') → then: IllegalArgumentException (연속된 구분자/빈 이름)
    - given: (",pobi", ',') → then: IllegalArgumentException (시작이 구분자)
    - given: ("pobi,", ',') → then: IllegalArgumentException (끝이 구분자)

## 📌 입력

- [ ] 자동차 이름들을 입력받는다
    - given: "pobi,woni,jun" / then: ["pobi", "woni", "jun"]
    - given: "" / then: IllegalArgumentException (빈 문자열)
    - given: "pobi,,woni" / then: IllegalArgumentException (빈 이름)
    - given: "aaaaaaaaa,pobi" / then: IllegalArgumentException (5글자 초과)
    - given: "pobi;woni" / then: IllegalArgumentException (다른 구분자)
- [ ] 시도 횟수를 입력받는다
    - given: "5" / then: 5
    - given: "0" / then: IllegalArgumentException (범위 미만)
    - given: "1000001" / then: IllegalArgumentException (범위 초과)
    - given: "abc" / then: IllegalArgumentException (자연수 아님)

## 📌 출력

- [ ] 라운드별 실행 결과를 출력한다
    - given: {Car("pobi",3), Car("woni",2)} / then: "pobi : ---\nwoni : --" (입력 순서대로)
- [ ] 최종 우승자를 출력한다
    - given: {"pobi", "jun"} / then: "최종 우승자 : pobi, jun" (입력 순서대로)

## 📌 도메인 객체

### Car

- [ ] 자동차를 생성한다
    - given: "pobi" / then: Car(name="pobi", position=0)
    - given: "" / then: IllegalArgumentException (빈 이름)
    - given: "toolongname" / then: IllegalArgumentException (5글자 초과)
    - given: null / then: IllegalArgumentException (null)

- [ ] 무작위 값에 따라 전진하거나 멈춘다
    - given: randomValue=4 / then: position=1 (전진 - 경계값)
    - given: randomValue=9 / then: position=1 (전진 - 최댓값)
    - given: randomValue=3 / then: position=0 (멈춤 - 경계값)
    - given: randomValue=0 / then: position=0 (멈춤 - 최솟값)
    - given: randomValue=4 두 번 / then: position=2 (연속 전진)

### Referee

- [ ] 최대 위치를 찾는다
    - given: [Car(pos=3), Car(pos=5), Car(pos=2)] / then: 5
    - given: [Car(pos=5), Car(pos=5), Car(pos=5)] / then: 5 (모두 동일)
    - given: [Car(pos=0)] / then: 0 (한 대만)

- [ ] 우승자를 결정한다
    - given: [Car("pobi",5), Car("woni",3), Car("jun",2)] / then: ["pobi"] (단독)
    - given: [Car("pobi",5), Car("woni",5), Car("jun",3)] / then: ["pobi", "woni"] (공동)
    - given: [Car("pobi",5), Car("woni",5), Car("jun",5)] / then: ["pobi", "woni", "jun"] (전원)
    - given: [Car("jun",5), Car("pobi",5)] / then: ["jun", "pobi"] (입력 순서)

### Game (도메인 객체 조합)

- [ ] 자동차 목록을 초기화한다
    - given: ["pobi", "woni"] / then: List<Car> 2개 생성
    - given: ["pobi", "woni", "jun"] / then: List<Car> 3개 생성 (입력 순서 유지)
    - given: [] / then: IllegalArgumentException (빈 목록)

- [ ] 한 라운드를 실행한다
    - given: [Car("pobi",0), Car("woni",0)] / then: 각 자동차 움직이기
    - given: 실행 후 / then: 자동차 순서 유지
    - given: 실행 후 / then: 각 자동차의 position 변경 가능

- [ ] 전체 게임을 실행한다
    - given: (["pobi", "woni"], 시도=3) / then: 3라운드 실행
    - given: 각 라운드 종료 시 / then: 결과 출력 호출
    - given: 게임 종료 시 / then: 우승자 출력 호출
    - given: 시도=0 / then: IllegalArgumentException (0회 이하)
