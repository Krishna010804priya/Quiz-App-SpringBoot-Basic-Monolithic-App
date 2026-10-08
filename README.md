# Quiz App (Spring Boot)

A REST API built with Spring Boot where you can manage a bank of questions, generate a quiz from a chosen category, play the quiz, and submit your answers to get a score. API documentation is available through Swagger UI.

## Features

- Add, view, update, and delete questions (CRUD)
- Filter questions by category
- Create a quiz by picking a category, number of questions, and a title (questions are chosen randomly)
- Fetch a quiz's questions **without** the correct answers (via `QuestionWrapper`)
- Submit answers and get the score
- Interactive API docs with Swagger UI

## Tech Stack

- Java 17
- Spring Boot 4.1.1
- Spring Web MVC
- Spring Data JPA (Hibernate)
- MySQL
- Lombok
- Springdoc OpenAPI 3.0.1 (Swagger UI)
- Maven

## Project Structure

```
src/main/java/com/priya/Quiz_App_SpringBoot
├── controller
│   ├── QuestionController.java
│   └── QuizController.java
├── service
│   ├── QuestionService.java
│   └── QuizService.java
├── model
│   ├── Question.java
│   ├── QuestionWrapper.java
│   ├── Quiz.java
│   └── Response.java
└── repository (dao)
│   ├── QuestionRepo.java
|__ └── QuizRepo.java
    

```

## Getting Started

### Prerequisites

- JDK 17 or higher
- Maven 3.6+
- MySQL running on `localhost:3306`

### 1. Clone the repository

```bash
git clone https://github.com/Krishna010804priya/Quiz-App-SpringBoot-Basic-Monolithic-App.git
cd Quiz-App-SpringBoot-Basic-Monolithic-App 
```

### 2. Create the database

```sql
CREATE DATABASE questiondb;
```

Tables are created automatically on first run (`spring.jpa.hibernate.ddl-auto=update`).

### 3. Configure `application.properties`

Open `src/main/resources/application.properties` and set your own credentials:

```properties
spring.application.name=Quiz-App-SpringBoot

spring.datasource.url=jdbc:mysql://localhost:3306/questiondb
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

### 4. Run the application

```bash
mvn spring-boot:run
```

The app starts at `http://localhost:8080`.

## API Documentation (Swagger)

Once the app is running, open:

- Swagger UI: http://localhost:8080/swagger-ui/index.html
- OpenAPI JSON: http://localhost:8080/v3/api-docs

![Swagger UI](docs/swagger.png)

## API Endpoints

### Question APIs (`/question`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/question/allQuestions` | Get all questions |
| GET | `/question/category/{cat}` | Get questions by category |
| POST | `/question/add` | Add a new question |
| PUT | `/question/updateQuestion` | Update an existing question |
| DELETE | `/question/deleteQuestionById/{id}` | Delete a question by ID |

### Quiz APIs (`/quiz`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/quiz/create?category=Java&numQ=5&title=JQuiz` | Create a quiz |
| GET | `/quiz/get/{id}` | Get the questions of a quiz (no answers included) |
| POST | `/quiz/submit/{id}` | Submit answers and get the score |

## Example Requests

### Add a question

`POST /question/add`

```json
{
  "questionTitle": "Which keyword is used to inherit a class in Java?",
  "option1": "implements",
  "option2": "extends",
  "option3": "inherits",
  "option4": "super",
  "rightAnswer": "extends",
  "difficultylevel": "Easy",
  "category": "Java"
}
```

### Create a quiz

`POST /quiz/create?category=Java&numQ=5&title=JavaQuiz`

Response:

```
Success
```

### Get quiz questions

`GET /quiz/get/1`

Returns the questions and options only, without the correct answers.

### Submit a quiz

`POST /quiz/submit/1`

```json
[
  { "id": 1, "response": "extends" },
  { "id": 2, "response": "int" },
  { "id": 3, "response": "static" }
]
```

Response (the score):

```
2
```

> `submit` uses **POST** because it sends a request body. A GET request with a body is rejected by browsers and Swagger UI.

## How It Works

1. Add questions to the question bank with their category.
2. Create a quiz by choosing a category and number of questions.
3. Fetch the quiz questions using the quiz ID and show them to the player.
4. Submit the player's answers. The service compares them with the stored correct answers and returns the score.

## Author

**Krishnapriya_S**
GitHub: [Krishna010804priya]([https://github.com/Krishna010804priya/Quiz-App-SpringBoot-Basic-Monolithic-App.git])