#!/bin/sh
# バッチ処理用のシェルスクリプトを作成する
for file in input/*.txt; do
    filename=$(basename "$file")
    output_file="./output/$filename"
    java KP "$file" > "$output_file"
    if diff "./ans/$filename" "./output/$filename"; then
        echo "正しい答えです."
        echo "$filename"
    fi
done
