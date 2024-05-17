#!/bin/sh
n=0
file="sample.txt"
while test $n -le 6; do
    java DijkstraTester $file 0 $n
    n=`expr $n + 1`
done