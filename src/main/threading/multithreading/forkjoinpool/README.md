# ForkJoinPool


## Alternative to ForkJoin: Parallel Stream in Java 8

In Java 8, there is an alternative to ForkJoinPool that would allow us, by the use of lambas to do the same:

Example:

<pre>
int heaviestPackageWeight = container.parallelStream()
                                     .filter(item -> item.getType() == ITEM_PACKAGE)
                                     .mapToInt(item -> item.getWeight())
                                     .max();
</pre>