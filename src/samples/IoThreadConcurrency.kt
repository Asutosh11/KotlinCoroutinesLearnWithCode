package samples

import kotlinx.coroutines.*

fun main(){

    runBlocking {
        launch(Dispatchers.IO) { functionOne() }
        launch(Dispatchers.IO) { functionTwo() }
    }
}

suspend fun functionOne()
{
    delay(1000L)
    println("functionOne")
}

suspend fun functionTwo()
{
    println("functionTwo")
}

/**
 * Since functionOne is delayed for 1000ms
 * if these functions were to run in parallel/concurrence,
 * functionTwo would output first and then functionOne
 *
 *   Output:
 *     functionTwo
 *     functionOne
 *
 * */