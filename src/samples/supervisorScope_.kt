package samples

/*

Lets create some Coroutines that follows this kind of hierarchy:

Parent Coroutine
Child coroutine 1
Child coroutine 2
...
Child coroutine N

1. By using supervisorScope,
    supervisorScope creates child CoroutineScope for every child Coroutine it starts
    which inherit parent's coroutineContext,
    but overrides the parent coroutineContext's Job just like coroutineScope.

2. If the parent scope is cancelled, all child scopes also get cancelled.

But assume that "Coroutine i" fails. What do you want to happen with its parent?

If you want for its parent to also fail, use coroutineScope.
That's what structured concurrency is all about.

But if you don't want it to fail,
for example child was some kind of background task which can be started again,
then use supervisorScope.

*/
