package samples

import kotlinx.coroutines.*
import kotlin.coroutines.ContinuationInterceptor

// Details on all Dispatchers
fun main(){

    runBlocking {

        launch(Dispatchers.Main){
            /** A coroutine dispatcher that is confined to the Main thread
                operating with UI objects. **/
        }

        launch(Dispatchers.Default){
            /**
             * The default [CoroutineDispatcher] that is used by all standard builders like
             * [launch][async], etc if no dispatcher
             * nor any other [ContinuationInterceptor] is specified in their context.
             *
             * It is backed by a shared pool of threads on JVM.
             * By default, the maximal level of parallelism used by this dispatcher
             * is equal to the number of CPU cores, but is at least two.
             */
        }

        launch(Dispatchers.IO){
            /**
             * The [CoroutineDispatcher] that is designed for offloading blocking IO tasks to
             * a shared pool of threads.
             *
             * Additional threads in this pool are created and are shutdown on demand.
             * The number of threads used by tasks in this dispatcher is limited by the value of
             * "`kotlinx.coroutines.io.parallelism`" ([IO_PARALLELISM_PROPERTY_NAME]) system property.
             * It defaults to the limit of 64 threads or the number of cores (whichever is larger).
             *
             * ### Important note
             *
             * This dispatcher shares threads with the [Default][Dispatchers.Default] dispatcher, so using
             * `withContext(Dispatchers.IO) { ... }` when already running on the [Default][Dispatchers.Default]
             * dispatcher does not lead to an actual switching to another thread &mdash; typically execution
             * continues in the same thread.
             */
        }

        launch(Dispatchers.Unconfined){
            /**
             * A coroutine dispatcher that is not confined to any specific thread.
             * It executes the coroutine initially in the current thread
             * where its called until suspended and lets the coroutine resume
             * in whatever thread that is used by the corresponding suspending function
             */
        }

    }
}