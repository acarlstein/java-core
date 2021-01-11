# Matrix

TBD

## Rotate Matrix

TBD

## Find Unique Paths in a Grid with Obstacles
With a grid of size `M*N`, starting at `(1,1)`, try  to reach 
`(M, N)`. 

If you are at `(x, y)` then you can either go `(x, y + 1)` or `(x + 1, y)`.

If some obstacles are added into this matrix, how many unique paths would there be?

For example:
```
[[0, 0, 0],    \
 [0, 1, 0],    || => Output: 2
 [0, 0, 0]]    //
```

Advice: Use dynamic programming so you don't have to recompute the subproblems.
