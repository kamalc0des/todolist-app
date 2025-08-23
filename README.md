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
git clone https://github.com/your-username/todolist-app.git
cd todolist-app
```

---

### 2. Run the backend (Spring Boot)
```bash
cd todolist-backend
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
 ├── todolist-backend/     # Spring Boot backend
 ├── todolist-frontend/    # Angular + Tailwind frontend
 └── README.md             # Documentation
```

---

## 📜 License

This project is licensed under the **MIT License**.

```
MIT License

Copyright (c) 2025 YOUR NAME

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
