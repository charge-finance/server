-- V1_1__add_user_table.sql

-- Create a table for storing user information
CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY, -- Unique identifier for each user
    email VARCHAR(255) NOT NULL UNIQUE, -- Email address (must be unique)
    password VARCHAR(255) NOT NULL,     -- Password (hashed)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Timestamp of creation
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- Timestamp of last update
);
