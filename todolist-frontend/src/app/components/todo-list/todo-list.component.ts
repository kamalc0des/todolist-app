import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TaskService } from '../../services/task.service';
import { Task } from '../../models/Task';

@Component({
  selector: 'app-todo-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.css']
})
export class TodoListComponent implements OnInit {
  tasks: Task[] = [];
  newTask: string = '';

  isEditModalOpen = false;
  selectedTask: Task = { title: '', completed: false };

  constructor(private taskService: TaskService) { }

  ngOnInit() {
    this.loadTasks();
  }

  loadTasks() {
    this.taskService.getTasks().subscribe(data => this.tasks = data);
  }

  addTask() {
    if (this.newTask.trim()) {
      const task: Task = { title: this.newTask, completed: false };
      this.taskService.addTask(task).subscribe({
        next: () => {
          this.newTask = '';
          this.loadTasks();
        },
        error: (err) => {
          if (err.error && err.error.title) {
            alert(err.error.title);
          } else {
            alert('Erreur during task creation');
          }
        }
      });
    } else {
      alert("The title must ne not empty");
    }
  }

  updateTask(task: Task) {
    this.taskService.updateTask(task).subscribe({
      next: () => {
        console.log('Task updated');
      },
      error: (err) => {
        if (err.error && err.error.title) {
          alert(err.error.title); // message envoyé par ton backend
        } else {
          alert("Error during the update");
        }
      }
    });
  }

  deleteTask(id?: number) {
    if (id) {
      this.taskService.deleteTask(id).subscribe(() => this.loadTasks());
    }
  }

  // 🔑 modal logic
  openEditModal(task: Task) {
    this.selectedTask = { ...task }; // clone pour éviter de modifier directement
    this.isEditModalOpen = true;
  }

  closeEditModal() {
    this.isEditModalOpen = false;
  }

  saveEditedTask() {
    this.taskService.updateTask(this.selectedTask).subscribe(() => {
      this.loadTasks();
      this.closeEditModal();
    });
  }
}
