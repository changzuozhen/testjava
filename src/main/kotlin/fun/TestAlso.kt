package `fun`

fun test() {
    val a = "aaa"
    val callback = object : TestCalback {
        override fun on(it: String) {
            println("on TestCalback $it")
        }

    }
    a.also { println("on Also "+it) }
        .also(callback)
}

private fun String.also(block: TestCalback) {
    block.on(this)
}

interface TestCalback {
    fun on(it: String)
}

fun main() {
    test()
}