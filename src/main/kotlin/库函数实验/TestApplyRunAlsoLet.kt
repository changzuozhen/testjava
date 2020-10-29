package 库函数实验

data class Book(var name: String, var author: String, var price: Int) {
    fun adjust(value: Int) {
        price = price.plus(value)
    }
}

fun main() {
    testApply() //apply in:this out:this
    testRun() //run in:this out:lam
    testAlso() //also in:it out:this
    testLet() //let in:it out:lam
    testWith() //with in:this out:lam
}

fun testApply() {
    /*
    apply in:this out:this
     */
    println()
    println("testApply apply in:this out:this")
    Book("《海边的卡夫卡》", "村上春树", 59).apply {
        println(this)
        this.adjust(-5)
        println(this)
    }.apply {
        println(this)
    }
}

fun testRun() {
    /*
    run in:this out:lam
     */
    println()
    println("testRun in: this out: lam")
    Book("《海边的卡夫卡》", "村上春树", 59).run {
        println(this)
        this.adjust(-5)
        println(this)
    }.run {
        println(this)
    }
}

fun testAlso() {
    /*
    also in:it out:this
     */
    println()
    println("testAlso also in:it out:this")
    Book("《海边的卡夫卡》", "村上春树", 59).also {
        println(it)
        it.adjust(-5)
        println(it)
    }.also {
        println(it)
    }
}

fun testLet() {
    /*
    let in:it out:lam
     */
    println()
    println("testLet let in:it out:lam")
    Book("《海边的卡夫卡》", "村上春树", 59).let {
        println(it)
        it.adjust(-5)
        println(it)
    }.let {
        println(it)
    }
}

fun testWith() {
    /*
    with in:this out:lam
     */
    println()
    println("testWith with in:this out:lam")
    with(Book("《海边的卡夫卡》", "村上春树", 59)) {
        println(this)
        this.adjust(-5)
        println(this)
    }.let {
        println(it)
    }
}