# Spring Boot JWT Authentication & Authorization  

This project demonstrates **authentication and authorization** using **Spring Boot**, **JWT (JSON Web Tokens)**, and **role-based access control**.  
It’s a hands-on implementation of **Spring Security** concepts such as password hashing, token-based authentication, and securing endpoints by roles.  

## 🚀 Features  

- User registration & login with **BCrypt password hashing**  
- **JWT-based authentication** (stateless, no sessions)  
- Role-based access (`USER`, `ADMIN`)  
- Pre-seeded `admin` user (`admin1 / Admin@123`)  
- Secured REST APIs with `Spring Security`  
- In-memory **H2 Database** for simplicity (viewable at `/h2-console`)  
- API testing with **Swagger UI**

## ⚙️ Getting Started  

### 1️⃣ Clone the Repository  
```bash
git clone https://github.com/your-username/Spring_Security_Jwt_Auth.git
```
### 2️⃣ Configure Application

- The project already comes with application.properties configured for H2 Database and JWT.
- Default JWT secret & expiry are set in application.properties.
- H2 Console is enabled at /h2-console.

No extra configuration is needed to run locally. ✅

### 3️⃣ Run the Application

### 4️⃣ API Endpoints

- POST /api/auth/signup → Register new user
- POST /api/auth/login → Authenticate and receive JWT
- GET /api/user/me → Get current user details (Requires JWT)
- GET /api/admin/users → List all users (Requires ROLE_ADMIN)

### 5️⃣Test with Swagger UI

- Swagger UI: http://localhost:8080/swagger-ui.html
- First, login via /api/auth/login to get a token
- Click Authorize in Swagger and enter token
- Now test secured endpoints

### 6️⃣ Default Admin User

On startup, a default admin user is seeded into the database:
- Username: admin1
- Password: Admin@123

You can log in with this user to access admin-only APIs.
