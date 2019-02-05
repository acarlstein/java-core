# CmdLinePropertiesTest

## Property file rules

* Blank lines are ignored

* We add comments by starting a line with **#** or **!**

* Whitespace surrounding the characters **= , :** are ignored
  * Example, these two are the same:
    * *variable_name = variable_value*
    * *variable_name=variable_value*

* Key and value are separated by **=**, **:**, or **[whitespace]**
  * Example, these two are the same:
    * *variable_name=variable_value*
    * *variable_name:variable_value*
    * *variable_name variable_value*
    
* We can "escape" the  **=**, **:**, and **[whitespace]** by using **\\**
  * Example, these characters escape the parser:
    * \\=
    * \\:
    * \\[whitespaces]

### Example of content of property file
```
#Comment to display on first line of property file
#Mon Jan 14 11:33:24 MST 2019
fullname=Juan Munoz
username=jmunoz
description: In JSON, values are separated with colons <key>\:<value> 
```
*Note that we "escape" the colon by adding the backslash*

