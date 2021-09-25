package samples

import kotlinx.coroutines.*

fun main(){

    runBlocking {

        val job = launch {
            
            try{
                for(i in 0..100){
                    print(i)

                    /**
                     * Gives away the thread (or thread pool) of the current coroutine
                     * dispatcher to other coroutine to run if possible.
                     *
                     * It temporarily deprioritizes the current long running CPU task.
                     *
                     * This suspending function is cancellable.
                     * If the Job of the current coroutine is canceled or completed
                     * when this suspending function is invoked or while this function
                     * is waiting for dispatch, it resumes with a CancellationException.
                     */
                    yield()
                }
            }
            catch(ex: CancellationException){
                // Need to use try/catch as cancelling a Coroutine throws a CancellationException
            }
        }

        delay(7)

        // coroutine will be cancelled now because yield() is used inside it
        job.cancel()
        job.join()
        println(" coroutine cancelled")
    }

}

/**
 *  Output:
 *     012345678910111213141516171819202122 coroutine cancelled
 **/