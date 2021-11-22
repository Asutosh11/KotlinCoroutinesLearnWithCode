package samples

import kotlinx.coroutines.*

suspend fun main(){
    CoroutineScope(Dispatchers.Main).launch{
        println("1")

        getData()
        println("2")

    }

    println("3")
}

suspend fun getData(){
    // output is same if delay is commented out or not
    // delay(1000)
    // because Coroutine takes some time to start
}

/**
 * Output:
 *  3
 *  1
 *  2
 */