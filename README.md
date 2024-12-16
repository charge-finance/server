# Charge API

## Overview

This API is under development and will provide endpoints for the Charge Mobile App

## Getting Started

1. Clone the repository:

   ```bash
   git clone https://github.com/charge-finance/server.git
   cd charge-api
   ```

2. Set up the database:

   - Use the provided `docker-compose.yml` file to create a local development environment with the database.
   - On Application start Flyway will create all the database tables for you with the `V1__createdb.sql` script.

3. Start building the API functionality.

## Current Status

- Database schema has been defined.
- Initial migration script `V1__createdb.sql` is ready.
- Docker Compose setup for local development is available.

## Planned Features

- User authentication and management
- Budget creation and tracking
- Categorization of transactions

## Endpoints

### User Endpoints

#### 1. Retrieve all users

**URL:** `/user`

**Method:** `GET`

**Description:** Fetch a list of all users in the database.

**Status:** Implemented

---

#### 2. Retrieve a user by email

**URL:** `/user/{email}`

**Method:** `GET`

**Description:** Fetch a user's details using their email address.

**Status:** Implemented

---

#### 3. Delete a user

**URL:** `/user/{id}`

**Method:** `DELETE`

**Description:** Delete a user from the database using their ID.

**Status:** Implemented

---

#### 4. Update a user

**URL:** `/user/{id}`

**Method:** `PUT`

**Description:** Update an existing user's details using their ID.

**Status:** Implemented

---

### Authentication Endpoints

#### 1. Login

**URL:** `/auth/login`

**Method:** `POST`

**Description:** Authenticate a user by email and password.

**Status:** Implemented

---

#### 2. Register a new user

**URL:** `/auth/register`

**Method:** `POST`

**Description:** Register a new user by providing user details.

**Status:** Implemented

---

### Future Endpoints

#### 1. Create Budget

**URL:** `/budget`

**Method:** `POST`

**Description:** Create a new budget.

**Status:** Not Implemented

---

#### 2. Retrieve Budgets

**URL:** `/budget`

**Method:** `GET`

**Description:** Fetch all budgets associated with a user.

**Status:** Not Implemented

---

#### 3. Categorize Transactions

**URL:** `/transactions/categorize`

**Method:** `POST`

**Description:** Categorize a list of transactions.

**Status:** Not Implemented
