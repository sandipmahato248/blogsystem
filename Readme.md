**Project: Medium Clone with Microservices Architecture using Spring Boot**
**Description:**
We will build a clone of the Medium platform using microservices architecture with Spring Boot. The application will consist of several microservices, each responsible for a specific domain of functionality. The microservices will communicate with each other via RESTful APIs, enabling seamless integration.

**Database:** MySQL server

**Microservices Overview:**

**User Service:** Manages user authentication, registration, and profile information.
**Database Schema:**
Table: User
Columns: id (Primary Key), username, email, password, created_at, updated_at

**Article Service:** Handles article creation, retrieval, and management.
**Database Schema:**
Table: Article
Columns: id (Primary Key), title, content, author_id (Foreign Key), created_at, updated_at
Table: Tag
Columns: id (Primary Key), name
Table: Article_Tag
Columns: article_id (Foreign Key), tag_id (Foreign Key)

**Comment Service:** Manages comments on articles.
**Database Schema:**
Table: Comment
Columns: id (Primary Key), content, article_id (Foreign Key), user_id (Foreign Key), created_at, updated_at

**Upvote Service:** Handles upvotes for articles.
Database Schema:
Table: Upvote
Columns: id (Primary Key), article_id (Foreign Key), user_id (Foreign Key), created_at, updated_at


**Microservice Communication:**
User Service communicates with Article Service for author information.
Article Service communicates with Comment Service for article comments.
Article Service communicates with Upvote Service for managing article upvotes.

**Team Division:**
To facilitate collaboration among the team of 4, we divide the microservices as follows:

**Team Member 1:**
User Service: Responsible for user management and authentication.
**Team Member 2:**
Article Service: Manages article creation, retrieval, updates, and communication with the Upvote Service.
**Team Member 3:**
Comment Service & Eureka: Handles comments on articles.
**Team Member 4:**
Upvote Service, Integration(API gateway): Manages article upvotes and communication with the Article Service.

This division allows each team member to focus on their respective microservice's functionality while enabling collaboration and integration.
 
