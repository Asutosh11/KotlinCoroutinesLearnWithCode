package samples

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

// withContext(...) executes Coroutines one by one, not concurrently

fun main(){

    runBlocking {
        var resultOne = ""
        var resultTwo = ""
        resultOne = withContext(Dispatchers.IO) { function1() }
        resultTwo = withContext(Dispatchers.IO) { function2() }
    }
}

suspend fun function1(): String
{
    delay(1000L)
    val message = "function1"
    println("withContext: " + message)
    return message
}

suspend fun function2(): String
{
    delay(100L)
    val message = "function2"
    println("withContext: "+ message)
    return message
}

/**
 * Since function1 is delayed for 1000ms and function2 is delayed 100ms,
 * if these functions were to run in parallel, function2 would output first and then function1
 *
 * But we are using withContext(...), which executes Coroutines one by one,
 * so function2 will execute only when function1 has finished execution
 *
 *   Output:
 *     withContext: function1
 *     withContext: function2
 *
 * */