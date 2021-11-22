package samples

import kotlinx.coroutines.*

fun main(){
    doOperation()
}

fun doOperation() {

    val scope = CoroutineScope(Dispatchers.Main)

    scope.launch(CoroutineName("my-custom-name")){

        println("coroutine context1 -> " + this.coroutineContext)

        val data: String = withContext(Dispatchers.IO){
            println("coroutine context2 -> " + this.coroutineContext)
            "something"
        }
        data.let{
           // do something
        }
    }
}
// Output

/*
coroutine context1 -> [CoroutineName(my-custom-name), CoroutineId(1),
"my-custom-name#1":StandaloneCoroutine{Active}@17c72154, Dispatchers.Main]
coroutine context2 -> [CoroutineName(my-custom-name), CoroutineId(1),
"my-custom-name#1":DispatchedCoroutine{Active}@78eaa50a, Dispatchers.IO]

As you can see, withContext() doesn't create a new Coroutine.
It inherits the Context of the Parent and just changes the Dispatcher.
So now the same parent Coroutine runs in a different Dispatcher,
the parent Coroutine is Blocked by withContext() until it finishes its job.
*/
