-- Author: Meet Maheta, Parth Patel, Aditya Hirpara, Gurminder Singh Badwal

-- Drop database if it exists and create a new one
DROP DATABASE IF EXISTS FWRP;
CREATE DATABASE FWRP;

-- Use the FWRP database
USE FWRP;

-- Create Users table to store user information
CREATE TABLE Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY, -- Unique identifier for users
    name VARCHAR(255) NOT NULL, -- User's name
    email VARCHAR(255) UNIQUE NOT NULL, -- User's email address (unique)
    password VARCHAR(255) NOT NULL, -- User's password
    user_type ENUM('retailer', 'consumer', 'charity') NOT NULL -- Type of user
);

-- Create inventory_items table to store inventory item details
CREATE TABLE inventory_items (
    item_id INT AUTO_INCREMENT PRIMARY KEY, -- Unique identifier for items
    name VARCHAR(255) NOT NULL, -- Name of the item
    quantity INT NOT NULL, -- Quantity of the item
    expiration_date DATE NOT NULL, -- Expiration date of the item
    price DECIMAL(10, 2) NOT NULL, -- Price of the item
    status VARCHAR(50) NOT NULL, -- Status of the item
    action VARCHAR(255) -- Additional action for the item
);

-- Create subscriptions table to store subscription details
CREATE TABLE subscriptions (
    subscription_id INT AUTO_INCREMENT PRIMARY KEY, -- Unique identifier for subscriptions
    email VARCHAR(255) UNIQUE NOT NULL, -- Subscriber's email address (unique)
    location VARCHAR(255) NOT NULL, -- Location of the subscriber
    phone_number VARCHAR(255) NOT NULL -- Phone number of the subscriber
);
