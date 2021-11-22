package samples

import kotlinx.coroutines.*
import java.io.IOException

fun main(){
    doSomething()
}

fun doSomething() {

    val exceptionHandler = CoroutineExceptionHandler { _, e -> println("exception handler: $e") }
    val scope = CoroutineScope(Dispatchers.Main)

    scope.launch(exceptionHandler){

        supervisorScope{
            launch(Dispatchers.IO){
                println("1st supervisor runs fine")
            }
        }

        supervisorScope{
            launch(Dispatchers.IO){
                throw IOException("IO exception in 2nd supervisor")
            }
        }

        supervisorScope{
            launch(Dispatchers.IO){
                println("3rd supervisor runs fine")
            }
        }
    }
}
// Output

// In supervisor scope, the error gets propagated to parent

/*
1st supervisor runs fine
exception handler: java.io.IOException: IO exception in 2nd supervisor
3rd supervisor runs fine
*/
