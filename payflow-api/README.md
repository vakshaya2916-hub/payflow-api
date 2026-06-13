
# PayFlow API

A simplified backend REST API for user registration, wallet storage, and money transfer records.

## Tech stack
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database

## Project structure
- `entity` — JPA entities for the database tables
- `repository` — Spring Data JPA repositories
- `service` — business logic layer
- `controller` — REST endpoints

## How to run
1. Open the project in IntelliJ IDEA or VS Code.
2. Run the main class: `com.payflow.PayflowApiApplication`.
3. Or use Maven:
   ```bash
   mvn spring-boot:run
   ```
4. Open the H2 console at:
   ```
   http://localhost:8080/h2-console
   ```
5. Use this JDBC URL:
   ```
   jdbc:h2:mem:payflowdb
   ```
   Username: `sa`
   Password: empty

## API endpoints

### UserController
- `POST /users`
- `POST /users/demo-no-body`
- `GET /users`
- `GET /users/{id}`
- `GET /users/upi/{upiId}`
- `GET /users/balance-above?amount=1000`

### TransactionController
- `POST /transactions`

## Sample curl commands

### Register a user
```bash
curl -X POST http://localhost:8080/users \
  -H "Content-Type: application/json" \
  -d '{"name":"Priya","upiId":"priya@okaxis","phoneNumber":"9999999999","balance":5000}'
```

### Register a second user
```bash
curl -X POST http://localhost:8080/users \
  -H "Content-Type: application/json" \
  -d '{"name":"Rahul","upiId":"rahul@upi","phoneNumber":"8888888888","balance":2000}'
```

### List all users
```bash
curl http://localhost:8080/users
```

### Get user by ID
```bash
curl http://localhost:8080/users/1
```

### Search by UPI ID
```bash
curl http://localhost:8080/users/upi/priya@okaxis
```

### Send money record
```bash
curl -X POST http://localhost:8080/transactions \
  -H "Content-Type: application/json" \
  -d '{"senderUpiId":"priya@okaxis","receiverUpiId":"rahul@upi","amount":250}'
```

### Demo request body difference
With `@RequestBody`:
```bash
curl -X POST http://localhost:8080/users \
  -H "Content-Type: application/json" \
  -d '{"name":"Demo","upiId":"demo@upi","phoneNumber":"7777777777","balance":100}'
```

Without `@RequestBody`:
```bash
curl -X POST http://localhost:8080/users/demo-no-body \
  -H "Content-Type: application/json" \
  -d '{"name":"Demo","upiId":"demo@upi","phoneNumber":"7777777777","balance":100}'
```

In the second call, the fields are not bound from the JSON body, so the printed object will contain `null` values unless request parameters are supplied instead.

## Entity mapping
CamelCase Java fields such as `upiId` and `phoneNumber` are mapped by JPA/Hibernate to snake_case columns like `upi_id` and `phone_number`.

## SQL generated on startup
With `spring.jpa.show-sql=true`, Hibernate prints the `create table` statements in the console on first startup. Paste those exact statements into your assignment write-up after running the app once.

## SQL for `findByUpiId`
Spring Data JPA derives SQL from the method name:
- `findByUpiId(String upiId)` becomes a query that filters users by the `upi_id` column.
- The `?` placeholder in generated SQL means a bind parameter whose value is supplied at runtime.

Example representation:
```sql
select * from users where upi_id = ?
```

## Custom query comparison
### Derived query method
Example: `findByUpiId(String upiId)`.
Spring reads the method name and builds the query automatically.

### JPQL with `@Query`
Example: `@Query("SELECT u FROM User u WHERE u.balance > :minBalance")`
This is object-oriented and uses entity names and fields, not raw table names.

### Native SQL
This uses database-specific SQL directly.
It is the least preferred because it is less portable, ties the code to one database, and is harder to maintain.

## Spring Boot features in this project
### Embedded server
The app runs with embedded Tomcat when you launch the main class.

### Auto-configuration
Spring Boot automatically configures MVC, Jackson, JPA, H2, and the datasource from the dependencies and properties file.

### Production-ready defaults
You get sensible defaults such as a working HTTP server, default JSON serialization, JPA entity scanning, and standard error handling without writing configuration manually.

## Request lifecycle
When `curl` sends `POST /users`, the request reaches the embedded server, then `DispatcherServlet` receives it and asks the handler mapping which controller method should run. Spring then uses a `HandlerAdapter` to invoke the matched `UserController.registerUser` method. Because the method has `@RequestBody`, the HTTP body is converted into a `User` object before the method executes.

## Serialisation
When JSON like `{"name":"Priya","upiId":"priya@okaxis"}` is posted, Spring uses Jackson to deserialize the JSON into a Java object. If the key is `upi_id` instead of `upiId`, the field will not bind by default because the Java property name is `upiId` and the JSON names must match unless you configure a naming strategy or annotations.

## Spring vs Spring Boot
With plain Spring, you would configure the servlet container, datasource, JPA setup, component scanning, and MVC wiring manually. Spring Boot takes care of most of that automatically using starters and auto-configuration, so you only provide the app code and a few properties.

## Stateless REST
Stateless means the server does not rely on memory of previous requests to process the current one. Each request must contain everything needed to process it. That matters behind a load balancer because any request can land on any server, and the system still works without needing sticky sessions.

## Persistence
If transactions were stored in a Java `List`, they would disappear when the server restarts because the list lives only in memory. That is unacceptable for a payments app because transaction history must survive restarts, outages, and redeployments.

## Assignment deliverables checklist
- [x] Spring Boot project with Web, JPA, H2
- [x] Four packages: entity, repository, service, controller
- [x] `User` and `Transaction` entities
- [x] Repositories with derived query and JPQL query
- [x] Services with `@Autowired`
- [x] REST controllers and endpoints
- [x] H2 configuration
- [x] README explanations
- [x] Conceptual answers
- [ ] Add screenshots from your own local run
- [ ] Paste the startup `create table` SQL from your console
- [ ] Paste curl outputs from your own terminal

## Notes for submission
Before submitting, run the app from a fresh start and capture:
- H2 console screenshots for both tables before inserts
- H2 console screenshots after inserting data
- curl command outputs
- the generated table SQL from startup logs

