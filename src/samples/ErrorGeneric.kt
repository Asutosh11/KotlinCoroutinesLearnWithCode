package samples

import kotlinx.coroutines.*
import java.io.IOException

fun main(){
    doSomeWork()
}

fun doSomeWork() {

    val exceptionHandler = CoroutineExceptionHandler { _, e -> println("exception handler: $e") }
    val scope = CoroutineScope(Dispatchers.Main)

    scope.launch(exceptionHandler){

        scope.launch(exceptionHandler){
            throw IOException("IO exception in child")
        }

    }
}
// Output

/*If there is Parent and Child Coroutine,
you need to pass the CoroutineExceptionHandler instance for Child also,
the exception will be handled in Child also

If you don't pass CoroutineExceptionHandler instance for Child,
Exception will not be handled in Child

*/

/*
exception handler: java.io.IOException: IO exception in child
*/
