# Task card Service

This is a simple Spring boot application focused on the interaction with a Spring REST server.

The data-centric application offers a basic user-management section, with the possibility to sign-in new users.
Each user can manage a list of task cards, each one associated to a name, color, status date, and a description.
There are three different roles:
* Admin: has full permissions on the whole data
* Member manager: has permissions on user data
* User: has full permissions on owned Card data.

The application has the following features:
* data access with pagination, sorting, ordering and filtering;
* CRUD operations on whole data;
* sign-in to permit the registration of new users;
* authentication based on oauth2 JWT, with token refresh;
* consumes REST resources `/api/card`, `/api/auth/users` and `/api/auth/sign-in`

The server side is implemented with Spring boot, and mySQL.
Server implementation features the following:
* oauth2 JWT authentication with spring-boot, using grant types `password` and `refresh token`;
* publish 2 authenticated REST resources `/api/card`, `/api/users` and two public `/api/signin` for user registration and `/swagger-ui/` api documentation;
* method-level authorization based on `@PreAuthorize` and `@PostAuthorize`;
* data access based on logged user's permissions;
* Spring based JSR-349 data validation;

Available users are:

|User email|Password| Role  |
|----------|--------|-------|
|admin@admin.com|pwd| Admin |
|userman@userman.com|pwd| Admin |
|user1@user1.com|pwd| User  |
|user2@user2.com|pwd| User  |
|...|


