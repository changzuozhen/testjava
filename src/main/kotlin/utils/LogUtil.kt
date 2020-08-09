package utils

import java.text.SimpleDateFormat
import java.util.*

val dateFormat = SimpleDateFormat("HH:mm:ss:SSS")
val now = { dateFormat.format(Date(System.currentTimeMillis())) }
fun log(msg: Any?) =
    LogUtils.d("${now()} [${Thread.currentThread().name}] $msg", 2)
