# PayFlow Assignment Write-up Template

Replace the placeholder sections below with screenshots and the exact console output from your own local run.

## 1. Startup SQL from console
Paste the two `create table (...)` statements printed by Hibernate on first startup.

```sql
-- create table statement for users

-- create table statement for transactions
```

## 2. H2 screenshots before inserts
Insert screenshots here:
- `SELECT * FROM USERS;`
- `SELECT * FROM TRANSACTIONS;`

## 3. Curl commands and outputs
Paste the exact curl commands and terminal output for:
- `POST /users`
- `GET /users`
- `GET /users/{id}`
- `POST /transactions`
- `GET /users/upi/{upiId}`

## 4. `@RequestBody` demonstration
Paste one controller log line from the request with `@RequestBody` and one log line from the request without it.
Explain why the object fields were `null` in the no-body version.

## 5. Conceptual answers
The README already contains draft answers. Move them here if your submission requires a separate document.

## 6. H2 screenshots after inserts
Insert screenshots here showing both tables after creating users and transactions.
