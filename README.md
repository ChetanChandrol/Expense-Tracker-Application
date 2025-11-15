# Splitwise Application - README

## 📌 Overview

This project is a **Splitwise-like expense sharing application** built using **Java** and **Spring Boot**. It allows users to:

* Create groups
* Add members to groups
* Add expenses
* Split expenses among users
* Track who owes whom
* Manage settlements

The backend provides REST APIs for managing all core Splitwise features.

---

## 🏗️ Project Architecture

**Layers Used:**

* **Controller Layer** – Handles incoming API requests
* **Service Layer** – Contains business logic
* **Repository Layer** – Interacts with the database
* **Model Layer** – Contains entities such as User, Expense, Group

**Tech Stack:**

* Java 17/21
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL / PostgreSQL (any relational database)
* Lombok

---

## 📂 Folder Structure

```
src/main/java/com/dev/Splitwise/
│
├── controller         # REST controllers
├── service            # Business logic
├── repository         # JPA repositories
├── entity             # Model/entity classes
├── dto                # Request/response DTOs
└── SplitwiseApplication.java  # Main application
```

---

## 🧩 Key Entities

### **User**

Represents an application user.

* id, name, email
* List of expenses

### **Group**

A collection of users sharing expenses.

* id, groupName
* members
* list of expenses

### **Expense**

Represents any amount spent by a user.

* id, description, amount
* addedBy (User)
* group (Group)
* currency

---

## 🚀 API Endpoints

### **Group APIs**

```
POST /groups            → Create group
GET /groups/{id}        → Get group details
POST /groups/{id}/add   → Add member to group
```

### **Expense APIs**

```
POST /expenses                 → Add expense
DELETE /expenses/{id}          → Delete expense
GET /groups/{id}/expenses      → List all expenses of group
```

### **Settlement APIs**

```
GET /groups/{id}/balance       → Who owes whom
```

---

## 🗄️ Database Schema 

```
User (id, name, email)
Group (id, name)
Expense (id, description, amount, user_id, group_id)
Group_Members (group_id, user_id)
```

---

## 🔧 How to Run the Project

### 1. Clone repository

```
git clone <repo-url>
```

### 2. Configure Database

Update `application.properties`:

```
spring.datasource.url=jdbc:mysql://localhost:3306/splitwise
spring.datasource.username=root
spring.datasource.password=yourpassword
```

### 3. Run Project

```
./mvnw spring-boot:run
```

Or run from IDE.

---

## 📘 Sample JSON Requests

### **Create Group**

```json
{
  "groupName": "Trip to Goa",
  "memberId": [1, 2, 3]
}
```

### **Add Expense**

```json
{
  "description": "Dinner",
  "amount": 1500,
  "addedBy": 2,
  "groupId": 1
}
```

---

## 📝 Future Enhancements

* Add authentication using JWT
* Add email notification service
* Detailed balance calculations
* Support for multiple currencies

---

## 🧑‍💻 Author

Developed by **Chetan Chandrol**.




