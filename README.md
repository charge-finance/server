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
    - On Application start flyway will create all the database tables for you with the `V1__createdb.sql` script.

3. Start building the API functionality.

## Current Status

- Database schema has been defined.
- Initial migration script `V1__createdb.sql` is ready.
- Docker Compose setup for local development is available.

## Planned Features

- User authentication and management
- Budget creation and tracking
- Categorization of transactions