import { Component } from '@angular/core';
import { TodoListComponent } from './components/todo-list/todo-list.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [TodoListComponent],
  template: `
    <h1>Todo List App</h1>
    <app-todo-list></app-todo-list>
  `
})
export class App { }
