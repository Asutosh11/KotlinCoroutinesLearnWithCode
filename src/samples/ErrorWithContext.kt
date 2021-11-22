package samples

import kotlinx.coroutines.*
import java.io.IOException

fun main(){
    doAwork()
}

fun doAwork() {

val exceptionHandler = CoroutineExceptionHandler { _, e -> println("exception handler: $e") }
val scope = CoroutineScope(Dispatchers.Main)

    scope.launch(exceptionHandler){

        val data: String = withContext(Dispatchers.IO){
            throw IOException("IO exception in withContext")
            "something"
        }
        data?.let{
            // do something
        }

    }
}
// Output

/*
Since withContext doesn't start a new Coroutine, and runs in the same context of parent,
we don't need to pass instance of CoroutineExceptionHandler separately to withContext,
if already passing it to its parent coroutine
*/

/*
exception handler: java.io.IOException: IO exception in withContext
*/
