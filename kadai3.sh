#!/bin/sh
# 課題3の検証処理を記述
for file in graph/graph005.txt graph/graph015.txt graph/graph025.txt; do
    java DFSTester "$file" 150 250
    echo "\n"
done