package 协程

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import utils.LogUtils

/**
 * 默认的，协程运行在一个共享的线程池中。
 * 线程仍然存在于基于协程的程序中，但是一个线程可以运行大量的协程，所以这里不需要太多线程。
 */
fun testDelay() {
    LogUtils.d("start")

    //  启动一个协程
    var job: Job = GlobalScope.launch {
        /**
         * 我们使用了类似 Thread.sleep() 的 delay() 函数，但是它更优异：它 不会阻塞一个线程 ，但是会挂起协程自身。
         * 当这个协程处于等待状态时该线程会返回线程池中，当等待结束的时候，这个协程会在线程池中的空闲线程上恢复。
         */
        delay(1000)
        LogUtils.d("Hello")
    }

    /**
    主线程（通过 main() 函数运行的线程）必须等到我们的协程完成，否则程序会在 Hello 被打印之前终止。
     */
//    Thread.sleep(2000)
    /**
    这是因为我们不在任何协程中。我们可以在 runBlocking {} 包装中使用 delay，它启动了一个协程并等待直到它结束：
     */
    runBlocking { delay(2000) }
//    job.join()

    LogUtils.d("Stop")
}

/**
 * 异步：从协程中返回一个值
 * 另一个启动协程的方法是 async {}。
 * 它类似于 launch {}，但返回一个 Deferred<T> 实例，它拥有一个 await() 函数来返回协程执行的结果。
 * Deferred<T> 是一个非常基础的 future（还支持完全成熟的 JDK future，但是现在我们将局限于我们自己的 Deferred）。
 * 让我们再次创建一百万个协程，并保持它们的 Deferred 对象的引用。现在这里不再需要原子计数，我们可以仅仅返回从协程中添加的数字：
 */
suspend fun testAsync() {
    LogUtils.d("start")
    val deferred: List<Deferred<Int>> = (1..1_000_000).map { n ->
        GlobalScope.async {
            n
        }
    }
    val sum = deferred.map { it.await().toLong() }.sum()
    LogUtils.d("End:Sum:$sum")
}


// https://play.kotlinlang.org/hands-on/Introduction%20to%20Coroutines%20and%20Channels/01_Introduction
//http://github.com/kotlin-hands-on/intro-coroutines
fun testChannel() = runBlocking<Unit> {
    val channel = Channel<String>()
    launch {
        channel.send("A1")
        channel.send("A2")
        LogUtils.d("A done")
    }
    launch {
        channel.send("B1")
        LogUtils.d("B done")
    }
    launch {
        repeat(3) {
            val x = channel.receive()
            LogUtils.i("received:$x")
        }
    }
}


suspend fun main() {
//    testDelay()
//    testAsync()
    testChannel()
}
