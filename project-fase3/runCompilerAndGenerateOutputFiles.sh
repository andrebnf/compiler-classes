#! /bin/bash

javac AST/*.java Lexer/*.java Error/*.java *.java;
java MainWithTests;
find . -name '*.class' -delete;
