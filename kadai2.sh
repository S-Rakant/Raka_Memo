#!/bin/sh
# 課題2の検証処理を記述
for file in graph/graph010.txt graph/graph020.txt graph/graph030.txt; do
    java BFSTester "$file"
    echo "\n"
done