# Heaps

## Largest Triple Product Heap

Find 3 numbers whose product is the maximum, then output the value.

Examples:

* `[1, 2, 3] => 6`
* `[1, 2, 3, 4] => 24`

1. Elements can range from -1000 to 1000
2. The length of the source array can be from 3 to 10^4
3. Multiplication of any three numbers won't exceed the range of 32-bits

### Analysis

First, lets create more examples to understand the behavior of this
algorithm that we must create.

For example, if I have [-5, -5, 2] then it would be -5 * -5 * 2 = 25 * 2 = 50.

This makes us realize that one thing we wish to ensure the input array is sorted.
In this way, we have the negative numbers near the beginning of the array and
the positive numbers near the end of the array.

This means that we have two candidates:
The maximum candidate is the multiplication of three last numbers in the array.
The minimum candidate is the multiplication of three first numbers in the array.

Whatever is bigger, either maximum candidate or minimum candidate will be the answer.

Another thing is the worse case which is when you have three elements that are all 1000 or -1000.
So, `1000 * 1000 * 1000 = 1000000000` and `-1000 * 1000 * 1000 = -1000000000`
In Java, the int has a range from -2147483648 to 2147483647; therefore, we are inside the range.
