package samples

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// Concurrency and parallel are almost same thing,
// meaning more than one tasks happening together

fun main(){

    // create a Coroutine in main thread.
    // runBlocking blocks the main thread but two new Coroutines are created and run inside it here
    // because those 2 are started from this runBlocking
    runBlocking {

        val threadName = Thread.currentThread().name
        println("inside runBlocking: $threadName")

        // inherit the parent scope and create second Coroutine in main thread
        // instead of launch, async can also be used for same concurrent behaviour
        val concurrent1 = launch{
            delay(1000)
            val threadName = Thread.currentThread().name
            println("inside concurrent1: $threadName")
        }

        // inherit the parent scope and create third Coroutine in main thread
        // instead of launch, async can also be used for same concurrent behaviour
        val concurrent2 = launch{
            val threadName = Thread.currentThread().name
            println("inside concurrent2: $threadName")
        }

        // concurrent1 and concurrent2 are parallel to each other
    }

}

/**
  Output:
    inside runBlocking: main @coroutine#1
    inside concurrent2: main @coroutine#3
    inside concurrent1: main @coroutine#2

 If it were not Concurrent, and would have executed one serially,
 the Output would have been first runBlocking, then concurrent1 and then concurrent2
**/
