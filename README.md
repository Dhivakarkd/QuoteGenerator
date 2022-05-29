![Build](https://img.shields.io/github/workflow/status/Dhivakarkd/QuoteGenerator/Build)
# QuoteGenerator

A project which provides a restful endpoint for Motivational Quotes  

#### Run Project with `DEV` Config  

    ./mvnw spring-boot:run

#### Run Project with `PROD` Config  

    ./mvnw spring-boot:run -P prod

# REST API

The REST API to the Quote Generator is described below.

### Request

`GET quote/randomQuote/`

    https://quotegenerator123.herokuapp.com/randomQuote/

### Response

    "id":619,
    "author":"Brian Tracy",
    "quote":"You can only grow if you're willing to feel awkward 
     and uncomfortable when you try something new."




