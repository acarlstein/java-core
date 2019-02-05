# Format Specifier

Parameters: *% [argument index] [flags] [width] [precision] [conversion]*

## Common Format Conversion

| Conversion  | Description       | Value  | Result       | 
|-------------|-------------------|--------|--------------|
| s           | String            | "Yes"  | Yes          |
| d           | Decimal (Integer) | 40     | 40           |
| f           | Decimal  (Float)  | 40.5   | 40.500000    |
| o           | Octal             | 40     | 50           |
| e E         | Scientific        | 40     | 40.50000e+02 |
| x X         | Hexadecimal       | 40     | 28           |

*Note*: If you pass an object, that is not a string, but you wish to use the String conversion '%s' 
then you should make sure the object has the toString method implemented this that method will be called.

## Flags 

| Flag    | Description                | Example  | Value    | Result    |
|---------|----------------------------|----------|----------|-----------|
| [Space] | Space for positive numbers | % d      | 40       | 40 (*)    |
| +       | Show positive sign, always | %+       | 40       | +40       |
| #       | Include Radix              | %#x      | 40       | 0x23      | 
| 0       | Zero padding               | %04d     | 40       | 0040      |
| -       | Left Justified             | x:%-4d X | 40       | x:40   X  |
| ,       | Group Separator            | %,.2f    | 40000.50 | 40,000.50 |
| (       | Parenthesis                | %(d      | 40       | (40)      |

*\(\*\) See unit test to see a running example of space for positive number*

#### Example

| Example | Value | Result |
|---------|-------|--------|
| %#o     | 40    | 050    |
| %#x     | 40    | 0x28   | 
| %#X     | 40    | 0X28   |

### Argument Index

| Index     | Description                                |
|-----------|--------------------------------------------|
| [nothing] |                                            |
| $[index]  | Index of argument                          |
| <         | Same argument as previous format specifier | 