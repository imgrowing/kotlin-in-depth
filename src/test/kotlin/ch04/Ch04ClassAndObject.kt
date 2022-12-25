package ch04

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.lang.IllegalArgumentException

class Person(firstName: String, familyName: String) {
    val fullName = "$firstName $familyName"

    init {
        println("Created new Person instance: $fullName")
    }
}

class Person2(fullName: String) {
    val firstName: String
    val familyName: String

    init {
        val names = fullName.split(" ")
        if (names.size != 2) {
            throw IllegalArgumentException("Invalid name: $fullName")
        }
        firstName = names[0]
        familyName = names[1]
    }
}

class Person3(val firstName: String, val familyName: String) {
    val fullName = "$firstName $familyName"
}

class Person4 {
    val firstName: String
    val familyName: String

    constructor(firstName: String, familyName: String): this("$firstName $familyName")

    constructor(fullName: String) {
        val names = fullName.split(" ")
        if (names.size != 2) {
            throw IllegalArgumentException("Invalid name: $fullName")
        }
        firstName = names[0]
        familyName = names[1]
    }
}

class Ch04ClassAndObject: FunSpec({

    context("4.1.2 - 주생성자") {
        // 생성자는 클래스 인스턴스를 초기화해주고, 인스턴스 생성시 호출되는 특별한 함수다.
        val person = Person("John", "Doe")
        person.fullName shouldBe "John Doe"

        // init 블록 안에서 프로퍼티를 초기화 할 수 있다.
        val person2 = Person2("John Doe")
        person2.firstName shouldBe "John"
        person2.familyName shouldBe "Doe"

        // 생성자 파라미터의 값을 멤버 프로퍼티로 만들 수 있다.
        // 생성자 파라미터에 val, var 키워드를 붙이면 된다.
        // 해당 생성자 파라미터로 초기화되는 프로퍼티가 정의된다.
        val person3 = Person3("John", "Doe")
        person3.firstName shouldBe "John"
        person3.familyName shouldBe "Doe"
        person3.fullName shouldBe "John Doe"
    }

    context("4.1.2 - 부생성자") {
        // 부 생성자는 constructor 키워드를 사용해서 정의할 수 있으며, 반환타입은 Unit 이다.
        // 다른 생성자를 호출하여 위임할 수 있다.
        // 클래스에 주생성자가 있다면, 모든 부생성자는 주생성자에게 위임을 하거나, 다른 부생성자에게 위임해야 한다.
        val person41 = Person4("John", "Doe")
        person41.firstName shouldBe "John"
        person41.familyName shouldBe "Doe"

        val person42 = Person4("John Doe")
        person42.firstName shouldBe "John"
        person42.familyName shouldBe "Doe"
    }
})