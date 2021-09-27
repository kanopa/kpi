#!/usr/bin/env node

const readline = require("readline")

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
    terminal: false,
})

const words = new Map()

rl.on("line", line => {
    const [word, count] = line.split("\t")

    if (!words.has(word)) {
        words.set(word, parseInt(count))
    } else {
        words.set(word, words.get(word) + 1)
    }
})

rl.on("close", () => {
    words.forEach((v, k) => {
        if (v >= 5 && v <= 10) console.log(`${k}\t${v}`)
    })
    process.exit(0)
})