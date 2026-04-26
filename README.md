# Mobile Banking Application

## 📌 Overview

This project is a simple Mobile Banking Application built as part of a Full Stack Engineer coding assessment.

The application allows users to:

* Authenticate securely using JWT
* Check account balance
* Transfer funds between accounts
* View transaction history and details

---

## 🛠 Tech Stack

### Backend

* Java Spring Boot
* Spring Security (JWT Authentication)
* PostgreSQL
* Flyway Migration

### Frontend

* Next.js (App Router)
* React + TypeScript
* Custom CSS (mobile-first UI)

### Infrastructure

* Docker & Docker Compose

---

## 🚀 How to Run

### ✅ Option 1 — Using Docker (Recommended)

Make sure Docker Desktop is running.

```bash
# Create network
docker network create mobile-banking-network

# Run all services
docker compose up --build
```

### Access the application:

* Frontend: http://localhost:3000
* Backend API: http://localhost:8080

---

### 🧪 Option 2 — Manual Run (Without Docker)

#### Backend

```bash
cd backend
./mvnw spring-boot:run
```

#### Frontend

```bash
cd frontend
npm install
npm run dev
```

---

## 🔐 Test Credentials

Use the following pre-seeded users:

### User 1

* Username: `andi`
* Password: `password123`
* Account Number: `10001`

### User 2

* Username: `budi`
* Password: `password123`
* Account Number: `10002`

---

## 📡 API Endpoints

### Authentication

```
POST /api/auth/login
```

### Account

```
GET /api/accounts/balance
```

### Transactions

```
POST /api/transactions/transfer
GET /api/transactions
GET /api/transactions/{id}
```

---

## ✨ Features Implemented

* ✅ JWT-based Authentication
* ✅ Secure API with user scoping
* ✅ Check Balance
* ✅ Transfer Between Accounts
* ✅ Transaction History
* ✅ Transaction Detail
* ✅ Frontend Integration (Real API, no mock)
* ✅ Form Validation (Frontend)
* ✅ Error Handling (Frontend & Backend)
* ✅ Mobile-first UI design

---

## ⚙️ Architecture Notes

* JWT is used for stateless authentication
* User scoping ensures users only access their own data
* Separation of `users` and `accounts` tables
* Flyway used for version-controlled database migration
* RESTful API design with consistent response format
* Transfers use a simple ledger entries approach (debit & credit) to ensure consistency and traceability  

---

## ❗ Error Handling Example

```json
{
  "success": false,
  "error": {
    "code": "INSUFFICIENT_BALANCE",
    "message": "Insufficient balance"
  }
}
```

---

## 📱 UI Preview

<img width="1536" height="1024" alt="ChatGPT Image Apr 26, 2026, 08_59_20 PM" src="https://github.com/user-attachments/assets/fa568c53-ca99-4436-b005-02f7f9984672" />


Recommended screenshots:

* Login Page
* Dashboard (Balance)
* Transfer Modal
* Transaction History

---

## 📂 Project Structure

This project is organized into two separate repositories:

### Backend Repository

```
mobile-banking-backend/
│
├── src/ # Spring Boot source code
├── Dockerfile
├── docker-compose.yml
└── pom.xml
```

### Frontend Repository

```
mobile-banking-frontend/
│
├── app/ # Next.js App Router
├── public/
├── Dockerfile
├── docker-compose.yml
└── package.json
```
