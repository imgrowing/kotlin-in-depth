package my.ex.ch02

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldNotBeSameInstanceAs

class ArrayTest : FunSpec({

    context("배열의 내용을 비교하고 싶으면 contentEquals() 함수를 사용하라") {
        val intArray1 = intArrayOf(1, 2, 3)
        val intArray2 = intArrayOf(1, 2, 3)

        (intArray1 == intArray2) shouldBe false
        (intArray1.contentEquals(intArray2)) shouldBe true

        intArray1 shouldBe intArray2                  // == 비교
        intArray1 shouldNotBeSameInstanceAs intArray2 // === 비교
    }

    context("배열의 표준함수 몇 가지") {
        // isEmpty(): 배열이 비었는지 검사
        intArrayOf(1, 2).isEmpty() shouldBe false

        // 배열에 원소가 있는지 검사
        intArrayOf(1, 2).isNotEmpty() shouldBe true
    }
})