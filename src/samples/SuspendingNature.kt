package samples

import kotlinx.coroutines.*

// Suspending nature of Coroutine
fun main(){

    // launch coroutine 1 on main thread
    runBlocking {

        println("One: "+Thread.currentThread().name)

        // launch coroutine 2 on IO thread
        // suspend coroutine 1 till coroutine 2 is complete
        val job: Deferred<String?> = async(Dispatchers.IO) {
            println("Two: "+Thread.currentThread().name)
            "return something from background thread"
        }

        // resume coroutine 1 on main thread
        println("Three: "+Thread.currentThread().name)
        val value = job.await()
        println(value)
    }
}

/**
 *   Output:
 *     One: main @coroutine#1
 *     Three: main @coroutine#1
 *     Two: DefaultDispatcher-worker-1 @coroutine#2
**/