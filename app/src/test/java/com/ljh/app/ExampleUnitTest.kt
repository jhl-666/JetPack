package com.ljh.app

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @ExperimentalStdlibApi
    @Test
    fun addition_isCorrect() {
       // assertEquals(4, 2 + 2)

        var aDeque = ArrayDeque<String>()
        aDeque.add("1")
        aDeque.add("2")
        aDeque.add("3")
        aDeque.add("4")
        aDeque.add("5")

        println(aDeque.removeFirst())
        println(aDeque.removeFirst())
        println(aDeque.removeFirst())
        println(aDeque.removeFirst())
    }


}
