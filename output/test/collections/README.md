# SimpleCollectionTest

We are going to go over some of these interfaces and classes but not all of them.

## Collection Methods Most Commonly Used
| Method   | Description                                                    |
|-----------|---------------------------------------------------------------|
| add       | Add single element                                            |
| addAll    | Add all elements from another collection                      |
| remove    | Remove single element                                         |
| removeAll | Remove all elements specified in another collection           |
| retainAll | Remove all elements that doesn't exists in another collection |
| contains  | Indicates if collection has an element                        |
| iterator  | Returns an iterator of the collection                         |
| clear     | Remove all elements                                           |
| isEmpty   | Indicates if there is no elements                             |
| size      | Return number of elements in collection                       |

*Note: There is more methods in the Collection interface but these I found to be most commonly used.*

## Common Collection Interfaces

| Interface   | Description                                         |
|-------------|-----------------------------------------------------|
| Collection  | Collection                                          |
| List        | Collection with order                               |
| Queue       | First In First Out (FIFO) Collection                |
| Set         | Collection without duplicates                       |
| SortedSet   | Set sorted                                          |
| Map         | Maps keys to values                                 |
| SortedMap   | Provides a total ordering of its elements           |
| NavigateMap | Extension of SortedMap. Provides navigation methods |

## Common Collection Classes

| Class         | Description                                           |
|---------------|-------------------------------------------------------|
| Stack (*)	    | Last In First Out (LIFO) Collection                   |
| Vector (**)   | List based on resizable array. Vector is synchronized |                                                     |
| ArrayList     | List based on resizable array                         |
| ArrayDeque    | Implements (FIFO) queue or (LIFO) queue               |                                                    |
| LinkedList    | List & Queue on doubly-linked list                    |
| PriorityQueue | High priority elements get to the head first          |                                      |
| HashMap       | Stores element by using hashing. No order guaranteed  |                                              |
| TreeMap       | Stores key-value pairs in a sorted ascending order    |
| HashSet       | Set implemented as hash table                         |
| LinkedHashSet | Hashtable and LinkedList implementation               |
| TreeSet       | Set sorted as balance binary tree                     |

*(\*) Stack is a legacy class, use Deque instead*
*(\*\*)The collection Vector is deprecated. 
Use ArrayList instead. 
If you need concurrency, check Collections.synchronizedList() method or CopyOnWriteArrayList.*


# SortingTest

## Exercise ST.1

In the method testReverseTreeSet() of the class SortingTest, 
try to obtain a subset between the first and middle element (both inclusive).

* ST.1.Q.1: What exception shows up when you try to obtain the subSet in the TreeSet?

* ST.1.Q.2: What changes you would apply to ClassComparable to make it work? 

*Note: You are free to recreate ClassComparable from scratch. Just try to ensure it pass all tests related.* 

Share your findings.

## Exercise ST.2

* ST.2.Q.1: Can you change the ClassComparator and ClassReverseComparator 
so it can change the way it sorts the TreeSet?

Share your findings.

# DictionariesTest

## Common Map Interfaces

| Interface   | Description                                         |
|-------------|-----------------------------------------------------|
| Map         | Basic Map operations                                |
| SortedMap   | Map in which keys are sorted                        | 
| NavigateMap | Extension of SortedMap. Provides navigation methods |

## Common Map Classes

| Class         | Description                                |
|---------------|--------------------------------------------|
| HashMap       | General map implementation                 |
| LinkedHashMap | HashMap maintained by a double-linked list | 
| TreeMap       | SortedMap, self-balancing tree             |

