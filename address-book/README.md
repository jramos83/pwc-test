# Address-Book
Coding challenge that allows to save, list and compare address books

## Design Decisions
- Storage is a relational in memory database - h2
- Persistence layer is Spring Data JPA
- The application is built as a Restful API using Spring Boot (Spring MVC)
- Entities include addressBook which contains contacts which represent name and phonenumbers
- The APIs are documented through Swagger UI which can be accessed via the URL http://localhost:8080/swagger-ui.html
- Due to time constraints, only test available is API test using Rest-Assured

### Assumptions
- A contact's uniqueness is defined by the name and phone number (as there can be many friends with the same name and phone numbers can be shared between people, albeit the chance being small)

### Prerequisites
- Java 11

### Running
The application can be run by executing the Spring Boot Jar