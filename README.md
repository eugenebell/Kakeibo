# Kakeibo - The Japanese Art of Mindful Saving and Budgeting

Kakeibo is a Spring Boot application inspired by the Japanese art of mindful saving and budgeting. This application helps users track their income and expenses, manage budgets, and practice the principles of mindful financial management.

## Features

- **Dual Database Support**: In-memory H2 for development and MySQL for production
- **Budget Tracking**: Daily, monthly, and yearly budget management
- **Transaction Management**: Comprehensive income and expense tracking
- **Kubernetes-inspired UI**: Clean, modern interface with a focus on usability
- **Responsive Design**: Works on desktop and mobile devices

## Architecture

### Models
- **Budget** - Core budget entity with start/end dates and category
- **Transaction** - Income and expense records with categories and dates
- **MonthlyBudget** - Monthly breakdown of budgets with spending tracking
- **YearlyBudget** - Annual budget tracking

### Technology Stack
- **Backend**: Spring Boot 3.4.5, Java 17
- **Database**: H2 (in-memory) and MySQL support
- **Frontend**: Thymeleaf templates, HTML, CSS, JavaScript
- **Build Tool**: Maven

## Getting Started

### Prerequisites
- JDK 17 or higher
- Maven 3.6+
- MySQL (for production database configuration)

### Running the Application

1. **Clone the repository:**
   ```bash
   git clone https://github.com/eugenebell/Kakeibo.git
   cd Kakeibo
   ```

2. **Build the project:**
   ```bash
   ./mvnw clean package
   ```

3. **Run the application:**
   ```bash
   java -jar target/kakeibo-0.0.1-SNAPSHOT.jar
   ```

4. **Access the application:**
   Open your browser and navigate to `http://localhost:8080`

### Database Configuration

#### In-Memory H2 (Development)
The application uses an in-memory H2 database by default. You can access the H2 console at:
`http://localhost:8080/h2-console`

#### MySQL (Production)
To switch to MySQL, update `application.properties`:

```properties
# MySQL configuration
spring.datasource.url=jdbc:mysql://localhost:3306/kakeibo
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
```

## Project Structure

```
src/
├── main/
│   ├── java/com/eugene/kakeibo/
│   │   ├── KakeiboApplication.java
│   │   ├── config/
│   │   ├── controller/
│   │   ├── model/
│   │   ├── repository/
│   │   └── service/
│   └── resources/
│       ├── templates/          # HTML templates with Thymeleaf
│       ├── static/             # CSS, JS, images
│       └── application.properties
```

## Kakeibo Method Principles

This application embodies the four principles of the Kakeibo method:

1. **Record everything** - Log every income and expense
2. **Make a monthly budget** - Plan spending for the month ahead
3. **Divide your money into categories** - Group expenses for better understanding
4. **Track and review all spending** - Regularly review what was spent

## Next Steps for Development

- Add user authentication and multi-user support
- Implement monthly/yearly reporting and analytics
- Add mobile app support using React Native or Flutter
- Create budget alerts and notifications
- Implement import/export functionality for CSV/Excel
- Add investment tracking features
- Include financial goal setting and monitoring

## License

This project is licensed under the MIT License.