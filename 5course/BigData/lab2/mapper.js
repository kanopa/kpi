#!/usr/bin/env node

const readline = require("readline")

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
})

const stopwords = ["a", "and", "around", "every", "for", "from", "in",
"is", "it", "not", "on", "one", "the", "to", "under", "of", "at", "are", "as", "or"]

rl.on("line", line => {
    if (!line) return

    line.split(" ").map(word => {
        const cleanWord = word.replace(/[^a-zA-Z]/g, "")
        if (stopwords.includes(cleanWord)) return

        console.log(`${cleanWord}\t1`)
    })
})

rl.on("close", () => {
    process.exit(0)
})