package samples

import kotlinx.coroutines.*

fun main() {

    runBlocking {

        // Start a Coroutine, which is cancelled on timeout
        withTimeout(200) {

            try {
                for (i in 0..100) {
                    print(i)
                }
            } catch (ex: TimeoutCancellationException) {
                /** if the code inside the Coroutine is still executing when timeout happens,
                    it throws a TimeoutCancellationException **/
            }
        }

    }
}
