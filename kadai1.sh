#!/bin/sh
# 課題1の検証処理を記述
for file in graph/*.txt; do
    filename=$(basename "$file")
    output_file="./output/$filename"
    java GraphTester "$file" > "$output_file" 
    if ! diff "./graph/$filename" $output_file; then
        echo "間違った答えです."
        echo $filename
    fi
done