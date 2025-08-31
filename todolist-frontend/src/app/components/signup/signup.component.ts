import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  username: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  signup() {
    this.authService.register(this.username, this.password).subscribe({
      next: (res) => {
        localStorage.setItem('token', res.token); // auto login
        this.router.navigate(['/todolist']);
      },
      error: () => {
        this.errorMessage = 'Registration failed';
      }
    });
  }
}
