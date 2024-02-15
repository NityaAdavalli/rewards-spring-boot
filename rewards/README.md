This is a springboot Application which uses Gradle and Rest webservices to calculate rewards for a customer. 

Requirement :
A retailer offers a rewards program to its customers, awarding points based on each recorded purchase. A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent over $50 in each transaction (e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points). Given a record of every transaction during a three month period, calculate the reward points earned for each customer per month and total.

App runs on default port 8080
All the values that are used in calculation of points can be changed by updating values in the application.properties file
We can use the postman for testing the API

Url : http://localhost:8080/rewards/calculate

RequestBody : 

[
{
"customerId": "112",
"purchasedMonth": "JANUARY",
"purchaseAmt": 110.0
},
{
"customerId": "12",
"purchasedMonth": "JANUARY",
"purchaseAmt": 10.0
},
{
"customerId": "112",
"purchasedMonth": "FEBRUARY",
"purchaseAmt": 10.0
},
{
"customerId": "132",
"purchasedMonth": "FEBRUARY",
"purchaseAmt": 150.0
}
]

Notes: AcceptedValues for purchasedMonth in the RequestBody are  [JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER]]

