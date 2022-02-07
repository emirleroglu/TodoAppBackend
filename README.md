# TodoAppBackend
## About The Project
This is a backend application developed with the Rest architecture made for a Todo List application.In the application, users should be able to register, login and create/update/delete their own to-do lists.Authentication is provided in the login and register section.
## Build With
- Java 8+
- Spring Boot
- Spring Data
- H2 Database
- Spring Security
- Jwt
- Maven
- JUnit
## Getting Started
This is an example of how you may give instructions on setting up your project locally. To get a local copy up and running follow these simple example steps.
### Installation
1.Clone the repo
```bash
git clone https://github.com/emirleroglu/TodoAppBackend.git
```


2)Open the folder with your favorite ide

### Build and Run
1.Build your app
```bash
mvn package
```
2.Run the test suite
```bash
mvn test
```

You can run `TaskTest` and `MvcTest` classes to run WebMvcTest tests.

3.Run your app with dependencies
```bash
mvn spring-boot:run
```
## Usage
1.Registeration
<img width="1116" alt="Screen Shot 2022-02-06 at 17 57 00" src="https://user-images.githubusercontent.com/52790536/152741479-89d12a92-b14d-4b7b-9758-76c101ad4051.png">
2.Login-Request
<img width="1140" alt="Screen Shot 2022-02-06 at 17 57 45" src="https://user-images.githubusercontent.com/52790536/152741853-05726b42-4c22-4f06-b33f-6876cabab4c6.png">
Login-Response
<img width="918" alt="Screen Shot 2022-02-06 at 17 57 58" src="https://user-images.githubusercontent.com/52790536/152741889-785af8c3-2dd2-42f8-ac78-f4ce803243f4.png">


To run other functions, it is necessary to use this Bearer token in the header first.

3.Create Task

<img width="1051" alt="Screen Shot 2022-02-06 at 18 00 07" src="https://user-images.githubusercontent.com/52790536/152742373-d5907ee6-dd11-474d-933a-e25ba6d6ff62.png">

4.Update Task

<img width="739" alt="Screen Shot 2022-02-07 at 10 24 42" src="https://user-images.githubusercontent.com/52790536/152743179-643d3801-ced9-40d2-acc0-5518d05386bd.png">


5.Delete Task
<img width="921" alt="Screen Shot 2022-02-07 at 10 21 49" src="https://user-images.githubusercontent.com/52790536/152742783-9d86d4bb-3ab8-464a-92b8-6ce686b6d724.png">

## Contact
Halil Emirleroğlu - emirlerogluhalil@gmail.com

Project Link: https://todolistspring12.herokuapp.com/
