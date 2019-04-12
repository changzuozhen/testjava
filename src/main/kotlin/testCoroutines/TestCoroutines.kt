package testCoroutines

import java.text.SimpleDateFormat
import java.util.*
import kotlinx.coroutines.*
import kotlinx.coroutines.CoroutineStart

/**
 * https://mp.weixin.qq.com/s/JR0APULIDOoz9kLVBgM9zQ
 */
val dateFormat = SimpleDateFormat("HH:mm:ss:SSS")
val now = { dateFormat.format(Date(System.currentTimeMillis())) }
fun log(msg: Any?) = println("${now()} [${Thread.currentThread().name}] $msg")
suspend fun main() {
//    defaultTest()
//    atomicTest()
    lazyTest()
//    undispatchedTest()
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