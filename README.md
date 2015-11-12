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

## Module
### blog-framework-cache
    the cache module provids servial annotations for DAO and somewhere else. The most important annotation is
    **@Cacheable**. We can put it on the DAO methods and specified some parameters which with a namespace. then
    the spring AOP will try to save the result to the cache which usualy represented by redis.

## The Web
#### Authentication
Spring Security used by this blog web. The Access Control consisted of User and Role and Function. A typical
method of access control.
### Scene
#### Edit Blog
This scene contains editing column, editing article.
The table design as follows:
