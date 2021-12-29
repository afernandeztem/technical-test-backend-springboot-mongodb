# Wallets Service

This exercise consists of building a proof of concept of this wallet service.
You have to code endpoints for these operations:
1. Get a wallet using its identifier.
1. Top-up money in that wallet using a credit card number. It has to charge that amount internally using a third-party platform.

The basic structure of a wallet is its identifier and its current balance. If you think you need extra fields, add them. We will discuss it in the interview. 

So you can focus on these problems, you have here a maven project with a Spring Boot application. It already contains
the basic dependencies and in-memory version of MongoDB for testing. There are develop and test profiles.

You can also find an implementation of the service that would call to the real payments platform (StripePaymentService).
This implementation is calling to a simulator deployed in one of our environments. Take into account
that this simulator will return 422 http error codes under certain conditions.

Consider that this service must work in a microservices environment in high availability. You should care about concurrency.

# Api Testing
1. Save a wallet.
- Post petition as: **localhost:8090/wallets/save** with the body as:
{
    "systemId" : "61c8377bc8af4074b7fb8008",
    "walletNumberId" : "12341234",
    "balance" : 100
}
2. Get a wallet using its identifier.
- Get petition as: **localhost:8090/wallets/byWalletNumber/12341234**
3. Top-up money in that wallet using a credit card number. It has to charge that amount internally using a third-party platform.
- Post petition as: **localhost:8090/wallets/reloadWallet/** with the body as: 
{
    "walletNumberId" : "12341234",
    "amount" : 20,
    "creditCard" : "4242 4242 4242 4242"
}
