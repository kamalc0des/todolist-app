import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule,FormsModule, RouterModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  login() {
    console.log('Login attempt:', this.username, this.password);
    this.authService.login(this.username, this.password).subscribe({
      next: (res) => {
        localStorage.setItem('token', res.token); // save the token into localStorage
        this.router.navigate(['/todolist']);
      },
      error: () => {
        this.errorMessage = 'Invalid username or password';
      }
    });
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }

  goToRegister() {
    this.router.navigate(['/signup']); // new register page
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }
}
