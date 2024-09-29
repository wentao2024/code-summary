# Code Summarization and Evaluation System

This project aims to create a software system for code summarization and evaluation, catering to both regular users and administrators. It provides functionalities to summarize code snippets and evaluate their performance based on predefined criteria.

## Installation Instructions
To install and set up the project, follow these steps:
1. Ensure you have Java 21, Gradle 8.7, and Docker installed on your system.
2. Clone the project repository from GitHub.
3. Navigate to the project directory.
4. Start the database using `docker-compose up`.

## Configuration
The project uses MySQL as the database. You can configure the database settings using tools like MySQL Workbench, IDEA Database Tool, or DataGrip. Update the database configuration in the application to match your setup.

## Running the Application
To run the application, use the following commands:
```bash
gradle bootJar
java --add-opens java.base/sun.nio.ch=ALL-UNNAMED -jar build/libs/code-summary-0.0.1-SNAPSHOT.jar
```

Begin your journey from:
http://localhost:8080/login

## Datasets
The system may utilize datasets for code analysis and evaluation. Make sure to provide the necessary datasets in the appropriate format for the system to function effectively. 
