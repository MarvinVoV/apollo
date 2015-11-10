# yamorn-blog
A blog website for programmers

## Initialization
    CREATE DATABASE blog CHARACTER
    SET utf8;
    
    CREATE TABLE USER (
        user_id VARCHAR(100) NOT NULL UNIQUE,
        user_name VARCHAR (100),
        user_pwd VARCHAR (20),
        ENABLE bit,
        create_date TIMESTAMP
    );
    
    CREATE TABLE roles (
        role_id INT NOT NULL UNIQUE,
        role_name VARCHAR (100)
    );
    
    CREATE TABLE FUNCTION (
        function_id INT NOT NULL UNIQUE,
        function_name VARCHAR (100),
        parent_id INT
    );
    
    CREATE TABLE user_roles (
        user_id VARCHAR(100) NOT NULL,
        role_id INT NOT NULL
    );
    
    CREATE TABLE function_roles (
        function_id INT NOT NULL,
        role_id INT NOT NULL
    );

    
