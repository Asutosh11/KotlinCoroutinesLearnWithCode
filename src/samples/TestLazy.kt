package samples

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {

    runBlocking {

        // using LAZY, will start a Coroutine only when value returned by it is used
        val myName = async (start = CoroutineStart.LAZY){
            "iron monkey"
        }

        // the above Coroutine will only start when variable myName is used somewhere

        // now the above Coroutine will start because myName is being used
        println(myName)
    }


}