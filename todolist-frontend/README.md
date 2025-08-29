# ✅ Todo List App

A fullstack **Todo List application** with authentication, built using:

- **Frontend:** Angular 20 (standalone components) + TailwindCSS  
- **Backend:** Spring Boot 3 (Java, Maven, Spring Security, Spring Data JPA, JWT)  

---

## 🚀 Features

- 🔑 User authentication with JWT (Register & Login)  
- 🛡️ Protected routes (AuthGuard on Angular, Spring Security on backend)  
- ➕ Add, ✏️ update, ❌ delete tasks  
- ✅ Mark tasks as completed (with strikethrough)  
- 🪟 Edit tasks in a modal dialog  
- 💾 Persistent backend storage (H2/PostgreSQL)  
- 🎨 Modern responsive UI with TailwindCSS  

---

## 🛠️ Tech Stack

### Frontend
- Angular 20 (Standalone Components + Angular Router)
- TailwindCSS
- TypeScript
- AuthGuard + LocalStorage for JWT token

### Backend
- Spring Boot 3.x
- Spring Data JPA
- Spring Security + JWT (stateless authentication)
- Lombok (for boilerplate reduction)
- H2 Database (default, in-memory)  
- PostgreSQL (optional for production)

---

## ⚙️ Installation & Usage

### 1. Clone the repo
```bash
git clone https://github.com/kamalc0des/todolist-app.git
cd todolist-app
```

---

### 2. Run the backend (Spring Boot)
```bash
cd todolist
./mvnw spring-boot:run
```

API endpoints:
- `POST /api/auth/register` → Register a new user  
- `POST /api/auth/login` → Authenticate & receive a JWT  
- `GET /api/tasks` → Fetch tasks (requires JWT in `Authorization: Bearer <token>` header)  

👉 Backend runs on: `http://localhost:8080/`

---

### 3. Run the frontend (Angular)
```bash
cd todolist-frontend
npm install
ng serve
```

Frontend available at:  
👉 `http://localhost:4200/`

Default routes:
- `/login` → Login page  
- `/todolist` → Task list (requires authentication)  

---

### 4. Build frontend for production
```bash
cd todolist-frontend
ng build --configuration production
```

---

## 📂 Project Structure
```
todolist-app/
 ├── todolist/                # Spring Boot backend
 │   ├── model/               # Entities (User, Task)
 │   ├── repository/          # Spring Data Repositories
 │   ├── service/             # Business logic (UserService, TaskService, JwtService)
 │   ├── controller/          # REST Controllers (TaskController, AuthController)
 │   ├── security/            # JWT Filter + SecurityConfig
 │   └── dto/                 # DTOs for requests/responses
 │
 ├── todolist-frontend/       # Angular + Tailwind frontend
 │   ├── components/          # LoginComponent, TodoListComponent
 │   ├── services/            # AuthService, TaskService
 │   ├── guards/              # AuthGuard
 │   └── app.routes.ts        # Angular routes
 │
 └── README.md                # Documentation
```

---

## 🔐 Authentication Flow

1. **Register/Login** via Angular form → calls `/api/auth/register` or `/api/auth/login`.  
2. Backend returns a **JWT token**.  
3. Token is saved in **localStorage**.  
4. Angular attaches `Authorization: Bearer <token>` header on every request.  
5. Spring Security validates token with `JwtAuthenticationFilter`.  
6. Access to `/api/tasks` is **granted only if token is valid**.  

---

## 📜 License

This project is licensed under the **MIT License**.
