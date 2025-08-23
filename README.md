# ✅ Todo List App

A fullstack **Todo List application** built with:

- **Frontend:** Angular 20 (standalone components) + TailwindCSS  
- **Backend:** Spring Boot (Java, Maven, Spring Data JPA, H2/PostgreSQL)  

---

## 🚀 Features

- Add, update, delete tasks  
- Mark tasks as completed (with strikethrough)  
- Edit tasks in a modal dialog  
- Persistent backend storage (H2/PostgreSQL)  
- Modern responsive UI with TailwindCSS  

---

## 🛠️ Tech Stack

### Frontend
- Angular 20 (Standalone components)
- TailwindCSS
- TypeScript

### Backend
- Spring Boot 3.x
- Spring Data JPA
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

API will be available at:  
👉 `http://localhost:8080/api/tasks`

---

### 3. Run the frontend (Angular)
```bash
cd todolist-frontend
npm install
ng serve
```

Frontend will be available at:  
👉 `http://localhost:4200/`

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
 ├── todolist/     # Spring Boot backend
 ├── todolist-frontend/    # Angular + Tailwind frontend
 └── README.md             # Documentation
```

---

## 📜 License

This project is licensed under the **MIT License**.
