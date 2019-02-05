# Threading

## Process

A process is an instance of a program which has access to resources such as memory and such.
Unless programmed, a process holds one thread only.

## Thread

Threads allows us to perform multiple tasks concurrently. 
A thread is a sequence of programmed instructions that may share resources from the process it belongs to.

Plus, threads address a problem that processes have which are sharing memory 
and speed in creation of an instance of the process.

### JVM Steps to Create and Start a Thread

* Memory is allocated in the thread stack to hold a frame for every thread method invocation
* Each frame will consist of a local variable array, return value, constant pool and an operand track
  * If supported, a native stack will be allocated
* Each thread willhold a program counter that will tekk the current instruction executed by the processor
* A native thread corresponding to the Java thread is created by the system
  * Inside the JVM internal data structure, some related descriptors to the thread will be added.
* The threads share the heap and method area.    
