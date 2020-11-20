NOTE: if your pushes are not taking effect for others, consult the gitIgnore file to ensure your files are not on there.


Index page -
http://127.0.0.1:49000/

This is where the interaction with the site can happen.


Access points-

Local address
http://127.0.0.1:49000/api/

To view customers
@GET
customers/all

To create a customer
@POST
customers/

Example post-
{
"name" : "Harry",
"correspondenceAddress": "HogWarts",
"email": "harry@gmail.com",
"password" : "harry",
"userName" : "harry"
}

To search for a customer
@GET
customers/?username=xxx&password=xxx

To view select Customer edit * with Customer id
@GET
customers/*

To view all of a Customers accounts
@GET
customers/*/accounts

To create an account for a Customer
@POST
customers/*/accounts

Example post-
{
    "accountName": "current"
}

To view all a select customer account change # with account id 
@GET
customers/*/accounts/#/

To view all transactions for a customer account
@GET
customers/*/accounts/#/transactions

To lodge money in account
@POST
customers/*/accounts/#/transactions/lodge

Example post-
{
	"description": "wage",
    	"transactionAmount": 300.0
}

To withdraw money from an account
@POST
customers/*/accounts/#/transactions/withdraw

Example post-
{
	"description": "rent money paid",
    	"transactionAmount": 300.0
}

To transfer another money to another account of the same customer
Replace the @ symbol with a other account number
@POST
customers/*/accounts/#/transactions/transfer/@

Example post-
{
	"description": "money moved to savings account",
    	"transactionAmount": 300.0
}

To transfer money to another customers account 
Replace the & symbol with another customerID
@POST
customers/*/accounts/#/transactions/transfer/@?otherCustomerID=&

Example post-
{
	"description": "money sent to friend for sushi",
    	"transactionAmount": 300.0
}


Might be a better method to search the database for unique sort codes