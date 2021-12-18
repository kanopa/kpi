import fs from 'fs'
import { v4 as uuidv4 } from 'uuid'
import randomWords from 'random-words'

const type = {
    hadoop: 'hadoop',
    spark: 'spark',
    kafka: 'kafka'
}

const keys = Object.keys(type)

const delayed = () => {
    return new Promise((resolve) => {
        setTimeout(() => {
            const data = fs.readFileSync("./data2/data2.json")
            const myObject = JSON.parse(data)
            const prop = keys[Math.floor(Math.random() * keys.length)]
            const newData = {
                idData2: uuidv4(),
                text: randomWords(),
                type: prop,
                createDate2: Date.now()
            };
            myObject.push(newData)
            const newJson = JSON.stringify(myObject);
    
            fs.writeFile("./data2/data2.json", newJson, (err) => {
            if (err) throw err
            console.log("New data added")
            console.log(newData)
            });
            resolve()
        }, 1000);
    })
}
    
for (; ; ) {
    await delayed()
}