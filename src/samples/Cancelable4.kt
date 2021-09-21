package samples

import kotlinx.coroutines.*

fun main() {

    runBlocking {

        // Start a Coroutine, which is cancelled on timeout
        // Returns the value if Coroutine successfully executed within timeout
        val jobToDoSomeTask : String? = withTimeoutOrNull(200) {

            for (i in 0..100) {
                print(i)
            }

            // withTimeoutOrNull returns a data of generic type, here I'm using it for String
            "returnSomeString"
        }

        /** If the code in Coroutine is still executing when timeout happens,
            the Coroutines returns null **/
        val value: String? = jobToDoSomeTask
    }
}
