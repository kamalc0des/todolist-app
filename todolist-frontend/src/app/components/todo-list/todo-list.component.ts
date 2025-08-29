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

  errorMessage: string = '';
  errorMessageModal: string = '';

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
          this.errorMessageModal = "";
          this.errorMessage = "";
        },
        error: (err) => {
          if (err.error && err.error.errors) {
            const errors = err.error.errors;
            // Get the first error found
            const firstKey = Object.keys(errors)[0];
            this.errorMessage = errors[firstKey];
          } else {
            this.errorMessage = "Error not defined";
          }
        }
      });
    } else { // default error (ex: the user do not enter valeu into the input)
      this.errorMessage = "The title must ne not empty";
    }
  }

  updateTask(task: Task) {
    this.taskService.updateTask(task).subscribe({
      next: () => {
        this.loadTasks();
        this.errorMessageModal = "";
        this.errorMessage = "";
      },
      error: (err) => {
        console.error("❌ Error not declared during the update", err);
        this.errorMessageModal = "❌ Error not declared during the update";
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
    this.selectedTask = { ...task }; // clone the task, do not update the current directly
    this.isEditModalOpen = true;
    this.errorMessageModal = "";
    this.errorMessage = "";
  }

  closeEditModal() {
    this.isEditModalOpen = false;
  }

  saveEditedTask() {
    this.taskService.updateTask(this.selectedTask).subscribe({
      next: () => {
        this.loadTasks();
        this.closeEditModal();
        this.errorMessageModal = '';
      },
      error: (err) => {
          if (err.error && err.error.errors) {
            const errors = err.error.errors;
            // Get the first error
            const firstKey = Object.keys(errors)[0];
            this.errorMessageModal = errors[firstKey];
          } else {
            this.errorMessageModal = "Error not defined";
          }
      }
    });
  }
}
