# 8_Arithmetic

The purpose of this is to provide a datatype which expands past the 2.147 billion primitive int 32-bit size limit.

Currently, this datatype supports addition, subtraction, multiplication, division, and greatest common divisor operations.
It can also up to 2.147 billion digits (a number of length 2.147 billion), which is the maximum size of the array indexing.

The main file takes input from STDIN in the format "n <op> n" where <op> is any of the following operations +,-,>,<,=,/,*.
                                                                                                                     
 
## Example Usage 

In the repository is a sample.txt, which is a file containing a number of valid operations. Simply compile using the `Make` command or `javac` compiler.

Once compiled can run by piping the input file in like so:
`java Main < sample.txt`

The resulting output should be:
```
1 * 1
# 1

2 + 2
# 4

5000 * 99999
# 499995000

100 < 99
# false

99 < 100
# true

100 = 100
# true

1000 = 1001
# false

2000000000 * 20
# 40000000000

2 / 2
# 1 0
```
