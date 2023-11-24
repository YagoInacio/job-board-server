<p align="center">
  <img alt="GitHub language count" src="https://img.shields.io/github/languages/count/yagoinacio/job-board-server?color=353949">

  <img alt="Repository size" src="https://img.shields.io/github/repo-size/yagoinacio/job-board-server">

  <a href="https://github.com/yagoinacio/job-board-server/commits/main">
    <img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/yagoinacio/job-board-server">
  </a>

   <img alt="License" src="https://img.shields.io/badge/license-MIT-brightgreen">

  <a href="https://yagofaran.dev">
    <img alt="Made by Yago Faran" src="https://img.shields.io/badge/made_by-Yago_Faran-353949">
  </a>
</p>

<h1 align="center">
    <img alt="Logo Yago Faran" title="#YagoFaran" src="./assets/logo.svg" />
</h1>

<p align="center">
 <a href="#-job-board-server">About</a> •
 <a href="#-tech-stack">Tech Stack</a> • 
 <a href="#-features">Features</a> •
 <!-- <a href="#-layout">Layout</a> •  -->
 <a href="#-how-it-works">How it works</a> • 
 <!-- <a href="#-contributors">Contributors</a> •  -->
 <a href="#-author">Author</a> • 
 <a href="#-license">License</a>
</p>

## 💻 Job Board Server

This API simplifies the interaction between companies and candidates, enabling organizations to display job positions and efficiently handle applicant profiles. Candidates can easily register within the system, sharing detailed information, including descriptions and CVs, and apply for positions with ease.

## 🛠 Tech Stack

-   **[JAVA](https://www.java.com)**
-   **[Spring Boot](https://spring.io/projects/spring-boot)**
-   **[Spring Data JPA](https://spring.io/projects/spring-data-jpa)**
-   **[Spring Security](https://spring.io/projects/spring-security)**
-   **[PostgreSQL](https://www.postgresql.org)**
-   **[JUnit](https://junit.org)**

## ✨ Features

- [x] Candidates:
  - [x] Register
  - [x] Authenticate
  - [x] Check profile
  - [x] List jobs by filter
  - [x] Apply for a job
  - [ ] Update Profile

- [x] Companies:
  - [x] Register
  - [x] Authenticate
  - [x] Create a job position
  - [ ] View candidates for a job
  - [ ] Update job position
  - [ ] Close job position
  - [ ] Update profile

## 🚀 How it works

This instructions will allow you to run a functional version of the project on your local machine.

### 📋 Pre-requisites

Before you begin, you will need to have the following tools installed on your machine:
[Git](https://git-scm.com), [JDK](https://www.oracle.com/br/java/technologies/downloads/), [Maven](https://www.apache.org).
In addition, it is good to have an editor to work with the code like [VSCode](https://code.visualstudio.com/) or [IntelliJ](https://www.jetbrains.com/pt-br/idea/download).

#### 🔧 Instalation

```bash
# Clone this repository
$ git clone git@github.com:yagoinacio/job-board-server.git

# Access the project folder cmd/terminal
$ cd job-board-server

# install the dependencies
$ mvn dependency:copy-dependencies
```

#### 🔧 Configuration

To be able tu run the application you need to set up the environment variables.

For that, create the files ```db.env.properties``` (containing database variables) and ```app.env.properties``` (containing the application variables).

You can follow the examples bellow:

```bash
# db.env.properties:
POSTGRES_USER=admin
POSTGRES_PASSWORD=admin
POSTGRES_DB=job_board
POSTGRES_HOST=localhost
```

```bash
# app.env.properties:
SECURITY_TOKEN_SECRET = mP63^#Y#S4Ie
SECURITY_TOKEN_SECRET_CANDIDATE = @bJtFd@Q8khP
```

#### 🎲 Running the application

```bash
# If you don't have a running database, run
$ docker compose up -d

# Run the application in development mode
$ mvn spring-boot:run

# The server will start at port: 8080 - go to http://localhost:8080
```

You can try out the API using its swagger documentation on http://localhost:8080/swagger-ui/index.html

#### ✅ Running automated tests

```bash
# Run automated tests
$ mvn test

# The test automation will run for unit and integration tests
```

## 🦸 Author

<a href="https://yagofaran.dev">
 <img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/yagoinacio" width="100px;" alt=""/>
 <br />
 <sub><b>Yago Faran 💧</b></sub>
</a>

[![Github Badge](https://img.shields.io/badge/-YagoInacio-gray?style=flat-square&labelColor=gray&logo=github&logoColor=white&link=https://github.com/yagoinacio)](https://github.com/yagoinacio)
[![Linkedin Badge](https://img.shields.io/badge/-Yago-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/yagoinacio/)](https://www.linkedin.com/in/yagoinacio/) 
[![Gmail Badge](https://img.shields.io/badge/-yagofaran@gmail.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:yagofaran@gmail.com)](mailto:yagofaran@gmail.com)

## 📝 License

This project is under the license [MIT](./LICENSE).

Made with ❤️ by Yago Faran 👋🏽 [Get in touch!](https://www.linkedin.com/in/yagoinacio/)