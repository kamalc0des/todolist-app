import { Component } from '@angular/core';
import { TodoListComponent } from './components/todo-list/todo-list.component';
import { LoginComponent } from './components/login/login.component';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [TodoListComponent, LoginComponent, RouterOutlet],
  template: `
    <router-outlet></router-outlet>
  `
})
export class App { }
