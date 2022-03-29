# Tila: A Tiny Language Compiler
## Overview
The repo contains source code for Tiny Language Compiler.

## Table of Contents

* [Overview](#overview)
* [Getting Started](#getting-started)
    * [Prerequisites](#prerequisites)
    * [Get Repo](#get-repo)

* [Run experiment](#Run-experiment)
    * [Prepare Input](#prepare-input)
    * [Run](#run)
    * [Result](#View-Result)

## Getting started
### Prerequisite
Java >= 1.8
### Get Repo
``git clone https://github.com/dominhkha/tila.git``
## Run experiment
### Prepare Input
Create a .txt file containing the soruce code of Tiny Language with following grammars:
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
* Compile:  ``make compile``
* Run : ``make run inputFile={The full path of input file}``
* Clean: ``make clean``

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

