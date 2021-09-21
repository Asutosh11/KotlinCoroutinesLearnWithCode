package samples

import kotlinx.coroutines.*

fun main(){

    runBlocking {

        // by default when Coroutine runs, isActive is true
        val job = launch (Dispatchers.Default) {

            try{
                for(i in 0..100){
                    print(i)

                    // We are stopping the Coroutine like this, when isActive is detected as false
                    if(!isActive){
                        println(" coroutine cancelled")
                        return@launch
                    }
                }
            }
            catch(ex: CancellationException){
                // Need to use try/catch as cancelling a Coroutine throws a CancellationException
            }
        }

        // when this is encountered, isActive will be set to false
        job.cancel()
        job.join()
    }
}

/**
 *  Output:
 *     01234567891011121314151617181920212223242526272829303132333435363738394041
 *     4243444546474849505152535455565758596061626364 coroutine cancelled
**/