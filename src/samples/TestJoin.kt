package samples

import kotlinx.coroutines.*

fun main() {

    // suspend means pause

    runBlocking {

        val job: Job = CoroutineScope(Dispatchers.IO).launch {
            println("start job")
            delay(5000)
            println("finish job")
        }
        // When .join() is encountered,
        // it suspends/pauses execution of next lines, until the above started job is complete.
        job.join()
        println("After join()")

        /**
         * Output:
         *   start job
         *   finish job
         *   After join()
         *
         * As you can see, the line after job.join() is called only when
         * the Coroutine's work is over, even if the Coroutine runs on a background thread.
         *
         * Same output will come, if its a Coroutine running on main thread
         */


    }
}
