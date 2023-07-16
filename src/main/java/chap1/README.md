# 1장 자바 8, 9, 10, 11: 무슨 일이 일어나고 있는가?

## 1.1 역사의 흐름은 무엇인가?

자바 역사를 통틀어 자바 8에서 가장 큰 변화가 있었다. 9와 10에도 변화가 있었지만 자바8에 비해서 큰 변화가 나타나지 않았다.

다음의 코드를 보자

```java
Collections.sort(inventory, new Comprator<Apple>(){
	public int compare(Apple a1, Apple a2){
		return a1.getWeight().compareTo(a2.getWeight());
	}
});

//java 8
inventory.sort(comparing(Apple::getWeight)); 
```

이처럼 자바 8에는 자연어에 더 가까운 간단한 방식으로 코드를 구현할 수 있다.

도한 자바 8은 멀티코어 CPU 대중화 같은 하드웨어적인 변화도 영향을 미쳤다.

이전에는 자바에는 하나의 코어를 사용하고 여거개의 스레드를 사용하였다. 하지만 스레드를 사용하면 관리하기 어렵고 많은 문제가 발생할 수 있다.

이를 해결하기 위해서 이전의 버전에서 멀티코어를 사용하기 위해서 다양한 것들을 추가했다. 하지만 개발자가 활용하기 쉽지는 않았다. 자바 8부터는 병렬 실행을 위해서 단순한 방식으로 접근할 수 있도록 하였지만 몇가지 규칙을 지켜야 한다. 이 책에 그 규칙에 대하여 설명할 예정이다.

자바 8에서의 새로운 기술들이 무엇인지 보면 다음과 같다.

- 스트림 API
- 메서드에 코드를 전달하는 기법
- 인터페이스의 디폴트 메서드

## 1.2 왜 아직도 자바는 변화하는가?

1960년대 사람들은 완벽한 프로그래밍 언어를 찾고자 노력했다. 이로인해서 수많은 언어가 탄생되었고 진화하지 않은 기존 언어는 사장되었다.

또한 특정 분야에서 장점을 가진 언어는 다른 경쟁 언어를 도태시킨다.

### 1.2.1 프로그래밍 언어 생태계에서 자바의 위치

자바는 시작부터 객체지향 언어로 나타났다. 자바는 스레드와 락을 이용하여 동시성을 제공하였고 자바 가상 머신(JVM)으로 컴파일을 하기 때문에 인터넷 애플릿 프로그래의 주요 언어가 되었다.

하지만 빅데이터의 등장으로 인해서 데이터를 효과적으로 처리하기 위해서 병렬성이 필요하게 되었다. 자바는 이러한 환경에 적응하기 위해서 자바 8부터 멀티코어 병렬성이 강화되었다.

### 1.2.2 스트림 처리

스트림이란 한 번에 한 개씩 만들어진 연속적인 데이터 항목 모임이다.

이론적으로 입력과 출력 스트림은 한개씩 처리를 하게 된다. 따라서 다른 프로그램의 출력 스트림이 될 수 있다.

자바 8에서는 [java.util.stream](http://java.util.stream) 패키지에 스트림 API가 추가되었다.

스트림 API에서는 입력 부분을 여러 CPU 코어를 쉽게 할당할 수 있어서 병렬성을 얻을 수 있다.

### 1.2.3 동작 파라미터화로 메서드에 코드 전달하기

동작의 파라미터화는 코드 일부나 혹은 메서드를 API로 전달하는 기능이다.

자바 8에서는 이러한 기능이 추가 되었다.

### 1.2.4 병렬성과 공유 가변 데이터

스트림 메서드로 전달하는 코드는 다른 코드와 동시에 실행하더라도 안전성이 보장되어야 한다.  예를 들어서 임계 구역에 대한 문제가 있다. 자바에서는 synchronized를 사용하여 공유된 데이털르 보호하는 규칙을 만들 수 있지만 시스템 성능에 악영향을 미친다. 하지만 자바 8의 스트림 API로 충분히 해결할 수 있다.

## 1.3 자바 함수

프로그래밍 언어에서 함수라는 용어는 메서드 특히 정적 메서드와 같은 의미로 사용되지만 자바에서는 수학적인 함수처럼 사용되며 부작용을 일으키지 않도록 하는 것이다.

자바 8에서는 함수를 새로운 값의 형식으로 추가했다. 그 이유는 병렬성을 위한 스트림과 연계되기 위해서이다.

이전의 버전에서는 int, double과 같은 기본 형식을 함수에 전달할 수 있었다. 이를 일급  시민이라고 불렸다. 구조체나 클래스는 자유롭게 전달할 수 없어 이급 시민이라고 불렸다. 하지만 자바 8에서는 자바스크립트와 같이 메서드, 클래스와 같은 이급 시민들을 일급 시민으로 만들어서 프로그래밍을 좀더 유용하게 활용하도록 한다.

### 1.3.1 메서드와 람다를 일급 시민으로

- 메서드 참조

주어진 파일이 숨겨진 여부를 판단하는 매서드를 구현해 보면

```java
File[] hiddenFiles = new File(".").listFiles(new FileFilter() {
	public boolean accept(File file) {
		return file.isHidden();
	}
});
```

하지만 이러한 방법은 각 행이 무슨 작업을 하는지 투명하지 않는다. isHidden이라는 함수가 있는데 굳이 복잡하게 감싸야 하는지 의문이 생길 수 있다.

자바 8에서는 이렇게 구현된다.

```java
File[] hiddenFiles = new File(",").listFile(File::isHidden);
```

이처럼 메서드 참조인 ::을 이용하여 listFile에 메서드를 전달할 수 있다. 이에 대한 자세한 이야기는 3장에 나온다.

- 람다 : 익명 함수

람다 함수는 `(int x) -> x+1` 과 같은 함수로 x라는 인수를 호출하면 x+1을 반환하는 함수이다. 람다 함수는 File::isHidden과 같이 편리한 클래스나 메서드가 없으면 람다 문법으로 좀더 간결하게 코드를 작성할 수 있다.

람다 문법 형식으로 구현된 프로그램은 함수형 프로그래밍, 즉 `함수를 일급값으로 넘겾는 프로그램을 구현` 한다라고 한다.

### 1.3.2 코드 넘겨주기 : 예제

Apple 클래스와 getColor 메서드가 있고 Apples 리스트를 포함하는 변수 inventory가 있다고 가정하자. 이때 모든 녹색 사과를 선택해서 리스트를 반환하는 프로그램을 구현해보자.

- 자바 8 이전

```java
public static List<Apple> filterGreenApples(List<Apple> inventory) {
  List<Apple> result = new ArrayList<>();
  for(Apple apple : inventory) {
    if(GREEN.equals(apple.getColor())) {
      result.add(apple);
    }
  }
  return result;
}
```

하지만 누군가 사과를 150g 이상으로 필터링을 하고 싶을 수 있다. 그래서 다음과 같이 코드를 구현 할 수 있다.

```java
...
for(Apple apple : inventory) {
    if(apple.getWeight()>150) {
      result.add(apple);
    }
  }
...
```

하지만 이렇게 코드를 작성하면 복사 붙여넣기를 하는 느낌을 주고 소프트웨어공학적인면에서 버그가 발생하면 복붙한 코드를 모두 고쳐야 한다.

- 자바 8 기준으로의 코드

```java
public static boolean isGreenApple(Apple apple) {
  return GREEN.equals(apple.getColor());
}

public static boolean isHeavyApple(Apple apple) {
  return apple.getWeight() > 150;
}

// 명확히 하기위해 적어놓음 
// 보통은 java.util.function에서 임포트함
public interface Predicate<T> {
  boolean test(T t);
}

// 메서드가 p라는 이름의 프레디케이트 파라미터로 전달됨
static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p {
  List<Apple> result = new ArrayList<>();
  for(Apple apple : inventory) {
    if(p.test(apple)) {
      result.add(apple);
    }
  }
  return result;
}

// 아래처럼 메서드를 호출할 수 있다.
filterApples(inventory, Apple:isGreenApple);
filterApples(inventory, Apple:isHeavyApple);
```

이때 **프레디케이드**라는 것을 보았을 것이다.  `수학에서는 인수로 값을 받아 true나 false를 반환하는 함수를 프레디케이트라고 한다.` 나중에 이야기 하지만 Function<Apple, Boolean>과 같이 작성할 수 있지만 Predicate를 사용하는 것이 더 표준적인 방식이다.

### 1.3.3 메서드 전달에서 람다로

메서드를 값으로 전달하는 것은 유용한 기능이지만 한 두번 사용할 메서드를 매번 정의하는 것은 귀찮은 일이다. 자바 8에서는 람다를 이용해서 이 문제를 간단히 해결 할 수 있다.

filterApples(inventory, (Apple a) -> GREEN.equals(a.getColor()));

filterApples(inventory, (Apple a) -> a.getWieght() > 150);

하지만 `람다가 몇 줄 이상으로 길어진다면 람다 보다는 코드가 수행하는 일을 잘 설명하는 이름을 가진 메서드를 정의하고 메서드 참조를 활용하는 것이 바랍직하다. 코드의 명확성이 우선시 되어야 하기 때문이다.`

## 1.4 스트림

거의 모든 자바 애플리케이션은 컬렉션을 만들고 활용한다. 하지만 컬렉션으로 모든 문제를 해결하는 것은 아니다.

고가의 트랜잭션(거래)만 필터링한 다음에 통화로 결과를 그룹화해야 한다고 가정하면 다음 코드처럼 많은 기본 코드를 구현

```java
Map<Curreny, List<Transaction>> transactionByCurrencies = new HashMap<>(); // 그룹화된 트랜잭션을 더할 Map 생성
for(Transaction transaction : transactions) {
  if(transaction.getPrice() > 1000) {
    Curreny curreny = transacation.getCurrency(); // 트랜잭션의 통화를 추출
    List<Transcation> transactionsForCurrency = transactionsByCurrencies.get(currency);
    if(transactionsForCurrency == null) {
      transactionsForCurrency = new ArrayList<>();
      transactionsByCurrencies.put(currenc, transcationsForCurrency);
    }
    transactionsForCurrency.add(transacation);
  }
}
```

위의 코드는 중첩된 제어 흐름 문장이 많아서 코드를 한 번에 이해하기 어렵다.

스트림 API를 이용하면 다음처럼 문제를 해결

```java
import static java.util.stream.Collectors.groupingBy;
Map<Currency, List<Transaction>> transactionsByCurrencies = transactions.stream()
.filter((Transcations t) -> t.getPrice() > 1000); // 고가의 트랜잭션 필터링
.collect(groupingBy(Transcation::geturrency)); // 통화로 그룹화
```

스트림 API를 사용하면 컬렉션 API와는 상당히 다른 방식으로 데이터를 처리할 수 있다.

- 외부 반복

for-each루프를 이용해서 각 요소를 반복하면서 작업을 수행

- 내부 반복

스트림 API와 같이 루프를 신경 쓸 필요가 없이, 라이브러리 내부에서 모든 데이터가 처리되는 것

또한 컬렉션을 이용했을 때 많은 요소를 가진 목록을 반복한다면 오랜 시간이 걸릴 수 있다. 이를 해결하기 위해서 멀티코어를 사용하여 병렬적으로 처리해야 한다.

### 1.4.1 멀티 스레딩은 어렵다.

이전 버전에는 스레드 API로는 멀티 스레딩 코드를 구현하기가 쉽지 않았다. 공유된 데이터를 처리하는데 문제가 발생할 수 있기 때문이다. 자바 8에서는  컬렉션을 처리하면서 발생하는 `모호함과 반복적인 코드 문제` 와 `멀티코어 활용 어려움` 이라는 두 가지 문제를 모두 해결했다.

## 1.5 디폴트 메서드와 자바 모듈

요즘은 외부에서 만들어진 컴포넌트를 이용해 시스템을 구축하는 경향이 있다. 이전의 버전은 평범한 자바 패키지 집합을 포함하는 JAR파일을 제공하는 것이 전부 였다. 게다가 이러한 패키지의 인터페이스를 바꿔야 하는 상황에서는 인터페이스를 구현하는 모든 클래스의 구현을 바꾸어야 하는 불편함이 있었다.  이러한 문제점을 자바 8과 9에서 해결하였다.

우선 자바 9에서는 모듈을 정의하는 문법을 제공하므로 이를 이용해 패키지 모음을 포함하는 모듈을 정의할 수 있다

또한 자바 8에서는 인터페이스를 쉽게 바꿀 수 있도록 디폴트 메서드를 지원한다.

- 디폴트 메서드는 특정 프로그램을 구현하는 데 도움을 주는 기능이 아니라 미래에 프로그램이 쉽게 변화할 수 있는 환경을 제공하는 기능이다.
