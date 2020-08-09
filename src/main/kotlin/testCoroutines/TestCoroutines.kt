package testCoroutines

import java.text.SimpleDateFormat
import java.util.*
import kotlinx.coroutines.*
import kotlinx.coroutines.CoroutineStart
import utils.log
import java.util.concurrent.Executors
import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor

/**
 * https://mp.weixin.qq.com/s/JR0APULIDOoz9kLVBgM9zQ
 */
suspend fun main() {
    defaultTest()
//    atomicTest()
//    lazyTest()
//    undispatchedTest()
//    continuationInterceptorTest()
//    continuationInterceptorTest2()
//    testDispatcher()
//    testError()
}

/**
16:51:55:888 [main] 1
16:51:55:942 [main] 3
16:51:55:946 [DefaultDispatcher-worker-1] 2
16:51:55:950 [main] 4
 */
private suspend fun defaultTest() {
    log(1)
    val job = GlobalScope.launch {
        log(2)
    }
    log(3)
    job.join()
    log(4)
}


/**
18:15:54:237 [main] 1
18:15:54:287 [DefaultDispatcher-worker-2] 2
18:15:54:287 [main] 4
18:15:54:288 [main] 5
 */
private suspend fun atomicTest() {

    log(1)
    val job = GlobalScope.launch(start = CoroutineStart.ATOMIC) {
        log(2)
        delay(100)
        log(3)
    }
    job.cancel()
    log(4)
    job.start()
//    job.join()
    log(5)
}

/**
18:16:08:938 [main] 1
18:16:08:981 [main] 4
18:16:08:981 [main] 5
 */
private suspend fun lazyTest() {

    log(1)
    val job = GlobalScope.launch(start = CoroutineStart.LAZY) {
        log(2)
        delay(100)
        log(3)
    }
    job.cancel()
    log(4)
    job.start()
//    job.join()
    log(5)
}

/**
18:15:30:909 [main] 1
18:15:30:950 [main] 2
18:15:30:964 [main] 4
18:15:31:065 [DefaultDispatcher-worker-1] 3
18:15:31:066 [DefaultDispatcher-worker-1] 5
 */
private suspend fun undispatchedTest() {

    log(1)
    val job = GlobalScope.launch(start = CoroutineStart.UNDISPATCHED) {
        log(2)
        delay(100)
        log(3)
    }
//    job.cancel()
    log(4)
//    job.start()
    job.join()
    log(5)
}


/**
10:48:27:041 [main] 1
10:48:27:106 [main] <MyContinuation> Success(kotlin.Unit)
10:48:27:106 [main] 2
10:48:27:125 [main] 4
10:48:27:224 [kotlinx.coroutines.DefaultExecutor] <MyContinuation> Success(kotlin.Unit)
10:48:27:224 [kotlinx.coroutines.DefaultExecutor] 3
10:48:27:226 [kotlinx.coroutines.DefaultExecutor] 5
 */
private suspend fun continuationInterceptorTest() {

    log(1)
    val job = GlobalScope.launch(
        MyContinuationInterceptor(),
        start = CoroutineStart.DEFAULT
//        start = CoroutineStart.LAZY
//        start = CoroutineStart.ATOMIC
//        start = CoroutineStart.UNDISPATCHED
    ) {
        log(2)
        delay(100)
        log(3)
    }
//    job.cancel()
    log(4)
//    job.start()
    job.join()
    log(5)
}

/**
10:56:57:289 [main] <MyContinuation> Success(kotlin.Unit)
10:56:57:290 [main] 1
10:56:57:295 [main] <MyContinuation> Success(kotlin.Unit)
10:56:57:295 [main] 2
10:56:57:306 [main] 4
10:56:58:304 [kotlinx.coroutines.DefaultExecutor] <MyContinuation> Success(kotlin.Unit)
10:56:58:304 [kotlinx.coroutines.DefaultExecutor] 3
10:56:58:306 [kotlinx.coroutines.DefaultExecutor] <MyContinuation> Success(Hello)
10:56:58:306 [kotlinx.coroutines.DefaultExecutor] 5. Hello
10:56:58:306 [kotlinx.coroutines.DefaultExecutor] 6
 */
private suspend fun continuationInterceptorTest2() {
    log(0)
    GlobalScope.launch(MyContinuationInterceptor()) {
        log(1)
        val job = async {
            log(2)
            delay(100)
            log(3)
            "Hello"
        }
        log(4)
        val result = job.await()
        log("5. $result")
    }.join()
    log(6)
}

class MyContinuationInterceptor : ContinuationInterceptor {
    override val key = ContinuationInterceptor
    override fun <T> interceptContinuation(continuation: Continuation<T>) = MyContinuation(continuation)
}

class MyContinuation<T>(val continuation: Continuation<T>) : Continuation<T> {
    override val context = continuation.context
    override fun resumeWith(result: Result<T>) {
        log("<MyContinuation> $result")
        continuation.resumeWith(result)
    }
}

/**
13:18:17:060 [pool-1-thread-1] 1
13:18:17:069 [pool-1-thread-1] 4
13:18:17:069 [pool-1-thread-2] 2
13:18:18:082 [pool-1-thread-3] 3
13:18:18:084 [pool-1-thread-4] 5. Hello
13:18:18:084 [pool-1-thread-4] 6
 */
private suspend fun testDispatcher() {
    Executors.newFixedThreadPool(10)
        .asCoroutineDispatcher().use { dispatcher ->
            GlobalScope.launch(dispatcher) {
                log(1)
                val job = async {
                    log(2)
                    delay(1000)
                    log(3)
                    "Hello"
                }
                log(4)
                val result = job.await()
                log("5. $result")
            }.join()
            log(6)
        }
}


suspend fun testError() {
    var i = 0
    Executors.newFixedThreadPool(10)
        .asCoroutineDispatcher().use { dispatcher ->
            List(1000000) {
                GlobalScope.launch(dispatcher) {
                    i++
                }
            }.forEach {
                it.join()
            }
        }
    log(i)
}