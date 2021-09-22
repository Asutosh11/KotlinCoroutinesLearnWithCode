package samples
import kotlinx.coroutines.*

fun main() {

    runBlocking {
        CoroutineScope(Dispatchers.Main).launch {
            val result = downloadUserData()
            println("Result : $result")
        }
    }

}

// remove _ to execute the problem code, rather than the solution code
// because both solution and problem have same method name and parameters
private suspend fun downloadUserData_(): Int {
    var result = 0

    /** Here, we use CoroutineScope (Capital C version) which will start
    a new scope and launch coroutine in new scope Dispatchers.IO,
    Not In Parent Scope which is Dispatchers.Main

    Thus, this function would directly return without waiting
    for loop completion and will return 0 **/

    CoroutineScope(Dispatchers.IO).launch {
        for (i in 0 until 100) {
            kotlinx.coroutines.delay(10)
            result++
        }
    }
    return result
}

/**
Output : Result : 0

This is an example of Unstructured Concurrency where it is not guaranteed
that child coroutine would complete before returning.
Thus, parent coroutine would get the wrong value returned by child coroutine.

Even, when child coroutine has returned already,
child coroutine may be running (in Active state) in the background
which may lead to Memory Leaks in certain cases.
**/

/**
Solution:
---------

When we need to communicate between multiple coroutines,
we need to make sure Structured Concurrency (Recommended)

This can be done by re-using parent coroutine scope inside child coroutine.
This can be achieved by coroutineScope {} (Smaller c) version
inside child/callee coroutine.
**/


private suspend fun downloadUserData(): Int {
    var result = 0

    /**
    1. By using coroutineScope (Smaller c version),
    coroutineScope creates child CoroutineScope for every child Coroutine it starts
    which inherit parent's coroutineContext,
    but overrides the parent coroutineContext's Job.

    2. If the parent scope is cancelled, all child scopes also get cancelled.
    3. If any one of the child Coroutine fails, parent Coroutine also fails.
    4. If all of the child Coroutine are success, it returns to parent.
    **/

    coroutineScope {
        for (i in 0 until 100) {
            kotlinx.coroutines.delay(10)
            result++
        }
    }
    return result
}

// Output : Result : 100