<h1>BankSystem Application</h1>

## Overview
The BankSystem is a Java Spring Boot application that simulates basic banking operations using Java 21 and an H2 in-memory database. It supports managing data across three primary entities: Account, Transaction, and Bank. This system enables the creation of banks and accounts and supports operations such as deposits, withdrawals, and transfers with transaction fees applied based on configurable rules.

## Features
### Bank Management
- **Create New Banks** and **Retrieve Details of All Existing Banks**.

### Account Management
- **Open New Accounts** with an existing bank, **Retrieve Account Details**, and **List All Accounts**.

### Transactions
- **Deposits**: Add funds to an existing account with a fee applied either as a fixed amount or a percentage.
- **Withdrawals**: Withdraw funds from an existing account with the same fee structure as deposits.
- **Transfers**: Transfer funds between accounts, subject to the same fees as other transaction types.

### Fee Calculation Logic
Fees for transactions are calculated based on the greater of two possible values:
- **Fixed Fee:**: A predetermined flat rate applied to each transaction.
- **Percentage Fee:**: A variable fee calculated as a percentage of the transaction amount.

The specific fee applied to each transaction is determined by the following rule:

- If the fixed fee is less than the product of the transaction amount and the percentage fee rate, then the percentage fee is applied.
- Otherwise, the fixed fee is applied.

This ensures that the fee charged is optimized for both small and large transactions, providing a fair and balanced approach to transaction costs.

## Setup and Installation
### Prerequisites
- Java 21
- Maven
- IntelliJ IDE

### Steps
1. **Clone the repository**:
   ```bash
   git clone https://github.com/Lavdim-Krasniqi/Bank-System.git
2. **Open and run project with IntelliJ**


## API Endpoints

### Bank Operations
- **Create Bank**:
  - **Method**: POST
  - **Endpoint**: `POST http://localhost:8080/bank`
  - **Body**:
    ```json
    {
      "name": "Bank Name",
      "totalTransactionFeeAmount": 0.0,
      "totalTransferAmount": 0.0,
      "transactionFlatFeeAmount": 10,
      "transactionPercentFeeValue": 5
    }
    ```
- **Get All Banks**:
  - **Method**: GET
  - **Endpoint**: `GET http://localhost:8080/bank`

- **Get Total Transaction Fee Amount**:
  - **Method**: GET
  - **Endpoint**: `GET http://localhost:8080/bank/getTotalTransactionFeeAmount/{bankId}`
 
- **Get Total Transaction Amount**:
  - **Method**: GET
  - **Endpoint**: `GET http://localhost:8080/bank/getTotalTransferAmount/{bankId}`
  

### Account Operations
- **Create Account**:
  - **Method**: POST
  - **Endpoint**: `POST http://localhost:8080/account`
  - **Body**:
    ```json
    {
      "user_name": "John Doe",
      "balance": 100,
      "bankId": 1
    }
    ```
- **Get All Account**:
  - **Method**: GET
  - **Endpoint**: `GET http://localhost:8080/account`
    
- **Deposit Money**:
  - **Method**: PUT
  - **Endpoint**: `PUT http://localhost:8080/account/deposit`
  -  **Body**:
    ```json
    {
    "accountId": 1,
    "amount": 1000,
    "reason": "salary"
    }
    ```
- **Withdraw Money**:
  - **Method**: PUT
  - **Endpoint**: `PUT http://localhost:8080/account/withdrawal`
  -  **Body**:
    ```json
    {
    "accountId": 1,
    "amount": 300,
    "reason": "for my needs"
    }
    ```
- **Transfer Money**:
  - **Method**: PUT
  - **Endpoint**: `PUT http://localhost:8080/account/transfer`
  -  **Body**:
    ```json
    {
    "amount": 10,
    "originatingAccountId": 1,
    "resultingAccountId":2,
    "reason": "Gift"
    }
    ```
- **List Of Transaction For Account**:
  - **Method**: GET 
  - **Endpoint**: `GET http://localhost:8080/account/getAllTransactionByAccountId/{id}`
 
- **Get Balance Of Account**:
  - **Method**: GET 
  - **Endpoint**: `GET http://localhost:8080/account/checkAccountBalance/{accountId}}`


