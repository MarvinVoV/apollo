# focusblog
A blog website for programmers

## SQL Initialization
    Read **blog.sql** file.
## Technology
* Semantic UI
* Spring Security
* Spring MVC
* MySQL
* Redis

## Module
### blog-framework-cache
    the cache module provids servial annotations for DAO and somewhere else. The most
     important annotation is **@Cacheable**. We can put it on the DAO methods and
     specified some parameters which with a namespace. then the spring AOP will try
     to save the result to the cache which usualy represented by redis.

## The Web
#### Authentication
Spring Security used by this blog web. The Access Control consisted of User and Role and Function. A typical
method of access control.
### Scene
#### Edit Blog
This scene contains editing category, editing article.



