# SimpleLoggerTest

## Warning
When logging, always be careful with sensitive information.
Information such as user credentials and financial information should remaing private. 
Categorize your data so you can better decide what you log and in which level you should perform the logging.

Another thing is be careful of excessive logging. 
Logging everything can decrease the performance of your application 
even when the Logger you are susing uses asynchronous logging (such as the logger *log4j*)   

Plus too much log messages can make it harder to read a log fine 
and identify the information required to solve the problem.

## Logging Levels

| Level   | Value             | Description                           |
|---------|-------------------|---------------------------------------|
| ALL     | Integer.MIN_VALUE | Show everything                       |
| FINEST  | 300               | Highly detailed tracing messages      |
| FINER   | 400               | Fairly detailed tracing messages.       | 
| FINE    | 500               | General developer information         |
| CONFIG  | 700               | Configuration information             |
| INFO    | 800               | General information                   |
| WARNING | 900               | Potential problem                     |
| SEVERE  | 1000              | Serious failure                       |
| OFF     | Integer.MAX_VALUE | Off log                               |

*Note: 
FINE for the lowest volume and important messages.
FINER for somewhat less detailed output.
FINEST should be used for the most voluminous detailed output.*

## Table: setLevel() vs. isLoggable()

The table shows the value that isLoggable() method will return based 
on the level set as an argument and the argument given to the method setLevel(). 

| setLevel()/isLoggable() | SEVERE | WARNING | INFO  | CONFIG | FINE  | FINER | FINEST |
|-------------------------|--------|---------|-------|--------|-------|-------|--------|
| SEVERE                  | True   | False   | False | False  | False | False | False  |
| WARNING                 | True   | True    | False | False  | False | False | False  |
| INFO                    | True   | True    | True  | False  | False | False | False  |
| CONFIG                  | True   | True    | True  | True   | False | False | False  |
| FINE                    | True   | True    | True  | True   | True  | False | False  |
| FINER                   | True   | True    | True  | True   | True  | True  | False  |
| FINEST                  | True   | True    | True  | True   | True  | True  | True   |

Example:
* If *setLevel(Level.INFO)* then isLoggable(Level.INFO) will return True.
* If *setLevel(Level.INFO)* then isLoggable(Level.CONFIG) will return False.

## Type of Handlers

### Console Handler

The Console Handler produces the Log output to Console Windows.
Its default Level is INFO.

### File Handler

The File Handler produces the Log output to a flat file or XML formatted.
We can specify the number of lines and the "Rotating File Set"
Its default Level is ALL. 
 
### Socket Handler

The Socket Handler productes a log record that can be send to a dedicated machine.

### Other Handlers

* Memory Handler
* Stream Handler

## Logger Naming Research

When working with Logger, you will find a topic call Logger Naming.
The basic idea is that you can create a hierarchy tree, 
based on the packages in your project for logging purposes.

Your assignment is to investigate Logger Naming and share an example.
Write some unit tests or code that shows the implementation of this concept.