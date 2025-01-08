CREATE DATABASE spring_crud;

USE spring_crud;

CREATE TABLE user (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR (50) NOT NULL,
    `lastname` VARCHAR (50) NOT NULL,
    `email` VARCHAR (50) NOT NULL,
    `phone` VARCHAR (50) NOT NULL
);