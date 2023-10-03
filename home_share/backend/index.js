const express = require ('express');
const cors = require('cors');
const twilio = require('twilio');

//twilio requirements  -- Texting API 
const accountSiD = 'ACf5a3595d60c9a49607efe47bf3b3102d';
const authToken ='377ff4f8b56332cbed779b35ff0d2018';
const client = new twilio (accountSiD, authToken); 

const app =express()

app.use(cors());

app.get('/', (req, res) => {
    res.send('Welcome to the Express Server')
})


app.get('/send-text', (req, res) => {
    //Welcome Message
    // res.send('Hello to the Twilio Server')

    //_GET Variables
    const { textmessage } = req.query;
    client.messages.create({
        body: textmessage,
        to: '+17788867253',  // Text this number
        from: '+15673443322' // From a valid Twilio number
    }).then((message) => console.log(message.body)); })

app.listen(4050, () => console.log("Running on Port 4050"));