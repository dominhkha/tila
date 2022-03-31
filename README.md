# Tila Scanner: A Tiny Language Scanner
## Overview
The repo contains source code for Tiny Language Scanner.

[//]: # (## Table of Contents)

[//]: # ()
[//]: # (* [Overview]&#40;#overview&#41;)

[//]: # (* [Getting Started]&#40;#getting-started&#41;)

[//]: # (    * [Prerequisites]&#40;#prerequisites&#41;)

[//]: # (    * [Get Repo]&#40;#get-repo&#41;)

[//]: # ()
[//]: # (* [Run experiment]&#40;#Run-experiment&#41;)

[//]: # (    * [Prepare Input]&#40;#prepare-input&#41;)

[//]: # (    * [Run]&#40;#run&#41;)

[//]: # (    * [Result]&#40;#View-Result&#41;)

## Getting started
### Prerequisite
Java >= 1.8

## Run experiment
### Prepare Input
Create a .txt file containing the source code of Tiny Language with following grammars:
```
Program	        -> begin Statements end EOF
Statements	-> Statement; Statements
                   | ϵ
Statement	-> Decl
                   | Assigment
                   | Loop
                   | print Expr
Decl 	        -> Type ID ; Decl
                   | ϵ
Type		-> int

Assigment	-> Id = Expr
Exp 		-> Expr - Expr
                   |->Expr * Expr
                   |->Expr ^ Expr
                   |->( Expr )
                   |-> ID
                   |-> NUMBER
Loop		-> while Expr do begin Statements end

ID = [a..z]|[A..Z] ([a..z]|[A..Z]|[0..9])*
NUMBER = 0|[1..9][0..9]*
```
An Example of the input file: [local/input.txt](local/input.txt)

### Run

[//]: # (* Compile:  ``make compile``)

[//]: # (* Run : ``make run inputFile={The full path of input file}``)

[//]: # (* Clean: ``make clean``)
```java -jar tila.jar <path to input>```
### Result
The results have type, value of each token and the line number where token stays. They will be printed in the console. Such as:
```
(Type: BEGIN, Value: "begin", Line: 1)
(Type: INT, Value: "int", Line: 2)
(Type: ID, Value: "x", Line: 2)
(Type: SEMICOLON, Value: ";", Line: 2)
(Type: INT, Value: "int", Line: 2)
...
```

### Contributing
This project welcomes contributions and suggestions.
### Contact
@dominhkha
### Acknowledgements

