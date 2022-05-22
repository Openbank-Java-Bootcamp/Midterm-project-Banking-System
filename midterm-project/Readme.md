# 1. DESCRIPTION OF THE PROJECT
More and more people are deciding to contract the services of an online bank because of its convenience and efficiency. That is why our company has developed this robust banking system that facilitates access to your account to our users, as well as ensures a good security system.

The banking system includes four types of accounts that the user can have, through the authorization of an administrator after providing their data. These accounts are:

1. Checking Account.
1. Student checking Account (Checking account for those under 24 years old).
1. CreditCard Account.
1. Saving Account.

To cover the needs of our customers, this banking system gathers in an intuitive way the following actions to be carried out by them:

1. Check the status of their accounts.
1. Make and receive transfers from other accounts.
1. Make and receive transfers from third parties.
# 2. SETUP




# 3. TECHNOLOGIES USED

The following technologies have been used for this project:

- "https://app.diagrams.net/" has been used to make a class diagram for the development of the project.
- TRELLO has been used to plan this project. It can be visited at the following link: https://trello.com/b/X4YEbDxI/banking-system.
- IntelliJ IDEA software to develop the code in the JAVA language.
- MySQL Workbench to carry out the data storage.
- The Postman program has been used to check and test the routes and methods developed for this banking system.
# 4. MODELS

- This banking system project has been delimited into three types of models: accounts, money, role and users.
- The accounts model includes the Account class and four subclasses derived from it, which correspond to the four types of accounts: Checking, StudentChecking, CreditCard and SavingAccount.
- Within the users model, we can find the Address class and the User and ThirdParty classes.



# 5. SERVER ROUTES TABLE(METHOD, ROUTE OR URL, DESCRIPTION AS COLUMNS)
## · Users Controller:
- Create a new user/account holder (POST; http://localhost:8080/api/users/account-holders): the route creates a new account holder which will be saved in the userRepository database. It is only accessible by admins.
- Create a new third party user (POST; http://localhost:8080/api/users/third-parties): the route creates a new third party user with a name and an encoded hashed key which will be stored in the thirdParty repository database. It is only accessible by admins.
- Delete a new user (DELETE; http://localhost:8080/api/users/delete/{id}): the route will delete a specific user from the userRepository table. It is only accessible by admins.
- Receive the information about a user (GET; http://localhost:8080/api/users/{username}): this method was created to access all the information about a user via its username. It is only accessible by admins.
## · Account Controller: 
- Receive the information of all accounts (GET; http://localhost:8080/api/accounts): this route was created to access all the information about all the accounts in the bank. It is only accessible by admins.
- Receive the information of one specific account (GET; http://localhost:8080/api/accounts/{id}): this route was created to access all the information about one specific accounts in the bank. It is only accessible by admins and the accountHolders of the account.
- Create checking/student account (POST; http://localhost:8080/api/accounts/checkings): this method is used to create a checking account. However, the system will detect automatically if a person is legible for a student account and if he/she accomplish the requirements a student account will be created for him/her. It is only accessible by admins.
- Transfers between internal accounts (PATCH; http://localhost:8080/api/accounts/transfers): this method was created to transfer money between two accounts of the bank. It will detect if the accounts exist, if the balance is enough to make the transfer and if the user is one of the accountHolders of the given account. It is only accessible by the accountHolders of the sender account.
- Transfers from a third-party user to an internal account (PATCH; http://localhost:8080/api/accounts/transfers/from-third-parties/{hashedKey}): this method will receive the hashed key from a third party user and it will send money to an internal account given the id of the account and its secret key. The system will detect if the hashed key is correct and if the secret key and account are correlated. It is only accessible by admins because the thirdParty user does not have a user to log in to the bank. 
- Transfers from an internal account to a third party user (PATCH; http://localhost:8080/api/accounts/transfers/to-third-parties/{hashedKey}): this method will receive the hashed key from a third party user and it will receive money to an internal account given the id of the account and its secret key. The system will detect if the hashed key is correct, if the secret key and account are correlated and if the sender account has enough funds. It is only accessible by admins because the thirdParty user does not have a user to log in to the bank.
- Delete a specific account (DELETE; http://localhost:8080/api/accounts/delete/{id}): this method is used to delete a specific account receiving its id. It is only accessible by admins.
- Adjust the balance of a specific account (PATCH; http://localhost:8080/api/accounts/balance/{id}): this method was created to set adjust manually the balance of a bank account by an admin.
## · Credit Card Controller:
- Create a credit card account (POST; http://localhost:8080/api/accounts/creditcards): this method was developed to create a new credit card account. It is only accessible by admins.
## · Saving Account Controller:
- Create a saving account (POST; http://localhost:8080/api/accounts/savings): this method was developed to create a new saving account. It is only accessible by admins.




# 6. FUTURE WORK
- A fraud system could be developed to create a more secure banking system.
- Right now the bank only is created to work with one currency, in this case, USD. However, thinking of expansion in the market it will be useful to have an exchange between currencies.
- Include functions from physical banks such as withdrawing money or depositing money in the online account.




# 7. RESOURCES
- https://www.baeldung.com/ : Find the explanation and examples of methods/functions that were new.
- https://docs.oracle.com/ : Used to find the methods or structure of different classes.
- https://stackoverflow.com/ : Used principally to find specific functions or to find the solution to some errors.
- https://www.youtube.com/ : Used to find video tutorials.
- The teacher and teacher Assistant of Ironhack :)
