package samples

import kotlinx.coroutines.*

fun main(){
    doSomeOperation()
}

fun doSomeOperation() {

    val scope = CoroutineScope(Dispatchers.Main)

    scope.launch(){

        var data = 0
        for(i in 0 until 1000){
            println(" " + i)
            data = i
        }
        println("last statement")
    }

}

// Inside a Coroutine, the code executes line by line.
// Even if some line takes some time to execute, the next line waits

// Output:
/*
...
995
996
997
998
999
last statement
*/
