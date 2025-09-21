💰 My Budget Bro – Expense Tracker App
📌 Overview

My Budget Bro is a personal Expense trackor application built with Spring Boot.
It helps users log, categorize, and track their expenses efficiently, providing clear insights into their spending habits.

This project is being developed in two phases:

Thymeleaf Integration (directly connecting frontend templates to Spring Boot).

API-based Integration (separating backend + frontend, allowing React/Angular or mobile clients to connect).

Future scope includes integrating AI-based expense insights.

⚙️ Tech Stack

Backend: Spring Boot (Java)

Frontend: Thymeleaf (initially), later external frontend via APIs

Database: H2

Build Tool: Maven/Gradle

Languages: Java, HTML, CSS, JavaScript

✨ Features

👤 User registration & authentication

➕ Add, edit, and delete expenses

📈 Generate spending summaries and reports

🔒 Secure one-to-many mapping (User → Expenses)

🚀 Scalable for future API/AI integration

📂 Project Structure
MyBudgetBro/
 ├── src/main/java/com/mybudgetbro/    # Source code
 │    ├── controller/                  # Controllers
 │    ├── model/                       # Entities
 │    ├── repository/                  # JPA repositories
 │    └── service/                      # Business logic
 ├── src/main/resources/
 │    ├── templates/                   # Thymeleaf HTML templates
 │    └── application.properties       # Configurations
 └── pom.xml                           # Maven dependencies

🚀 Getting Started
1. Clone the repository
git clone https://github.com/your-username/my-budget-bro.git
cd my-budget-bro

2. Configure Database

Update your application.properties:

spring.datasource.url=jdbc:H2://localhost:3306/mybudgetbro
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update

3. Run the Application
mvn spring-boot:run


App will run at http://localhost:8080

🛠️ Future Enhancements

✅ REST API integration for frontend/mobile apps

✅ AI-based financial insights (predict overspending, savings suggestions)

✅ Export data (CSV, Excel, PDF)

✅ Multi-user roles (Admin, Standard User)
