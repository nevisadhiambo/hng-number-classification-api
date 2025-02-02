# Number Classification API

This is a simple backend API that classifies numbers based on different mathematical properties and returns a fun fact about them. The API allows users to submit a number and get various properties of the number, including whether it's prime, perfect, Armstrong, and whether it's odd or even. Additionally, a fun fact about the number is fetched from the Numbers API.

## Features
- Accepts a number as input.
- Returns properties like "prime", "perfect", "Armstrong", "odd/even".
- Provides a fun fact about the number.
- Validates the input to ensure it's a valid integer.
- Returns JSON-formatted responses.

## Endpoints

### GET `https://hng-number-classification-api-ha7u.onrender.com/api/classify-number?number={number}`

#### Query Parameters:
- `number` (required): The number you want to classify.

#### Response Format (200 OK):

```json
{
  "number": 371,
  "is_prime": false,
  "is_perfect": false,
  "properties": ["armstrong", "odd"],
  "digit_sum": 11,
  "fun_fact": "371 is an Armstrong number because 3^3 + 7^3 + 1^3 = 371"
}
```

---

## 🏗 How to Run the Project Locally

### **Prerequisites**
Ensure you have the following installed:
- [Java JDK 21](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Git](https://git-scm.com/)
- A code editor like **VS Code** or **IntelliJ IDEA**

### **Steps to Run Locally**
1. **Clone the repository**  
   Open a terminal and run:
   ```sh
   git clone https://github.com/nevisadhiambo/hng-number-classification-api.git
   ```
2. **Navigate into the project directory**
   ```sh
   cd hng-number-classification-api
   ```
3. **Run the application**
- If using Maven:
  ```sh
  ./mvnw spring-boot:run  # Mac/Linux
  mvnw.cmd spring-boot:run  # Windows
  ```
- Alternatively, you can build and run the JAR file:
  ```sh
  mvn clean package
  java -jar target/*.jar
  ```
4. **Access the API**  
   Open your browser or use Postman to test the endpoint:
   ```
   http://localhost:8080/api/classify-number?number={number}
   ```

---
