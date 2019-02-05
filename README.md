# Java Core Course
<sub>Version 0.11.0</sub>

*Note:* In both main and test, in each package, I may be leaving a file named 
README.md. Please take a moment to read it. Inside them, you may find explanations
instructions and/or exercises.

In this course, I tried to cover most of the features related with Java Core.
If there is something that you believe that should be included, please let me know.
This is a work in progress. I will try my best to keep updating it and improving it.

I tried to make this a progressive course. 
The idea is to exposure you little by little to different common mistakes, to different coding styles, to inefficient and efficient code.
Its imperative that you don't just go thorough the motions. I want you to analyze what you read. 

Ask yourself, questions such as:

* Is there a mistake there?
* How could this code fail? Which would be the conditions for such failure?
* How could I write a more efficient code?
* How could I write a easy to expand code?
* What are the trade-off between efficient and clear?
* Do the names use for the classes, methods and variables make any sense?
* What could be done to improve the code in respect of time and/or space?
 
Little by little, as you read the code, you will be seeing new features being used.
The reason is that I want you to have a reference for comparison. 

For example, when reading streams, you can use the Try-Catch-Finally closures to deal with exceptions and closing the stream.
However, there is a feature in Java Core named Try-Resources which allows you to write a cleaner and better code.
More details will be reveal as you run the unit tests and read the source code.

## Unit Tests

In this course, I tried the best to provide you with some unit tests so 
you can see how the source code works.

Be aware that there are incomplete unit tests. 
This is not a mistake. It was done in purpose. 
They don't cover all the scenarios that one should be testing. 

Most of the unit test included follow the "happy path".
Try to see if you can come up with unit test that cover when things don't go as planned.
Also, you are welcome to include more unit tests of things that were not cover by the original unit tests.

The reason is that I want you, in your spear time, to come up with your own unit tests.
I want you to analyze the unit test you see, and ask yourself questions about them.
I want you to read these unit test and see if you could write better ones.

## Collaboration

Don't forget to share your findings, your suggestions and your unit tests.
Its important to create an environment where everyone can share what they think.
There is no right or wrong when asking questions and everyone can make mistakes.

## Powerpoint and Videos

I may include powerpoints and videos based on the feedback I receive 
and the time I can expend. 

## Versioning

We will follow the following versioning template: *major.minor[[.revision].build]*

### Major

The major value will be increased when a big changes are made.
For example:

* When the whole file directory has being changed
* When code changes are not compatible with previous version
* When we added new a whole new section.

### Minor

The minor value will be increased when we add small changes.
For example:

* When we add new content but no so much that would required a major version increase.
* When we did a small changes in the code that keep the original behavior and/or intent.
* When We corrected some mistakes in the documentation or code

### Revision

We use the revision to indicate the status of the content we added.

* 0 for alpha (status)
* 1 for beta (status)
* 2 for release candidate

For example:

* Alpha: We added incomplete content.
* Beta: We added new content but we haven't tested it yet.
* Release: We are happy with the content and we have test it. 
We are going to do a last minutes revision to ensure everything is fine. 

### Patches

Fixed issues in the release that do not required us to consider them a minor or major change. 