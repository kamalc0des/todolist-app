import { Component } from '@angular/core';
import { TodoListComponent } from './components/todo-list/todo-list.component';
import { LoginComponent } from './components/login/login.component';
import { Router, RouterOutlet } from '@angular/router';
import { SignupComponent } from './components/signup/signup.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  template: `
    <router-outlet></router-outlet>
  `
})
export class App { 
  constructor(private router: Router) {
    this.router.events.subscribe(event => {
      console.log('Router event:', event);
    });
  }
}
