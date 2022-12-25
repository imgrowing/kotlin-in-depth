package ch03

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Ch3Function : FunSpec({

    context("3.1.4 vararg: 가변인자") {

        fun stringSorted(vararg items: Int): String {
            items.sort() // 아래의 호출시, 함수 내부에서 items는 IntArray 이다.
            return items.contentToString()
        }

        stringSorted(6, 2, 10, 1) shouldBe "[1, 2, 6, 10]"

        // 스프레드(spread) 연산자인 *를 사용하면 가변인자 대신 배열을 넘길 수 있다.
        // 배열을 복사(얕은 복사)하여 가변인자로 전달한다.
        val numbers = intArrayOf(6, 2, 10, 1)
        stringSorted(*numbers) shouldBe "[1, 2, 6, 10]"
    }

    context("3.3.2 range, progression, operation") {
        test("range") {
            // .. 연산자, in
            val chars = 'c'..'e'
            ('c' in chars) shouldBe true
            ('d' in chars) shouldBe true
            ('e' in chars) shouldBe true
            ('b' in chars) shouldBe false
            ('b' !in chars) shouldBe true
            println(chars)

            // IntRange는 IntProgression을 상속하는데, IntProgression에는 Iterable을 구현하여 iterator()가 존재한다.
            // 따라서 IntRange는 for 문에서 iteration을 할 수 있다.
            val numbers: IntRange = 3..6
            numbers.forEach { println(it) }

            // ClosedRange는 Iterable 하지 않기 때문에 for 문에서 사용할 수 없다.
            val sRange: ClosedRange<String> = "aa".."bb"
            // for (n in sRange) { }
            // => Kotlin: For-loop range must have an 'iterator()' method

            // 문자열과 배열에는 원소나 문자의 인덱스 범위를 제공하는 indices(IntRange) 프로퍼티가 있다.
            val a = IntArray(10) { it }
            a.indices shouldBe 0..9
            val s = "abcde"
            s.indices shouldBe 0..4
        }
    }
})