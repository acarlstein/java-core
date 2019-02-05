# Multithreading

In Java, there are four common ways to do multithreading:

* Extending Thread class
* Implementing the Runnable interface and using a Thread instance
* Executing a Runnable task with ExecutorServices
* Implementing Callable interface and using ExecutorServices.

## Thread Pools

### Single Thread Pool

Only one thread executing one task is keep at a time.

### Cached Thread Pool

It keeps a certain number of thread alive and when needed, it creates new threads.

#### [!] Warning About Cached Thread Pool

If you are creating an application, such as a server, using the Cached Thread Pool, 
you may be opening your application to a DoS kind of attack.

Depending how you implement it, you can perfectly be allowing someone to force your application
to create a huge amount of threads; therefore, crippling your JVM.  

These are some advice for these cases:

* Use a Fixed Thread Pool or set a maximum number of threads using the ThreadPoolExecutor for information

* Find out the number of threads your CPU core can handle and perform a load testing.

* Investigate about the use of ThreadFactory.

### Fixed Thread Pool

It limits the maximum number of concurrent threads, while keeping waiting any additional task inside a queue.

### Fork Join Pool

By taking advantages of the multi-processors, it breaks the work into smaller pieces recursively inside a special thread pool.   


 