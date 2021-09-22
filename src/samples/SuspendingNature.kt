package samples

import kotlinx.coroutines.*

// Suspending nature of Coroutine
fun main(){

    // launch coroutine 1 on main thread
    val coroutine1 = runBlocking {

        println("One: "+Thread.currentThread().name)

        // launch coroutine2 on IO thread
        // suspend coroutine1 till coroutine2 is complete
        val coroutine2: Deferred<String?> = async(Dispatchers.IO) {
            println("Two: "+Thread.currentThread().name)
            "return something from background thread"
        }

        // resume coroutine1 on main thread
        println("Three: "+Thread.currentThread().name)
        val value = coroutine2.await()
        println(value)
    }
}

/**
 *   Output:
 *     One: main @coroutine#1
 *     Three: main @coroutine#1
 *     Two: DefaultDispatcher-worker-1 @coroutine#2
**/