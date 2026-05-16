import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { Auth } from '../services/auth'

@Component({
  selector: 'app-navbar',
  imports: [CommonModule,RouterLink],
  templateUrl: './navbar.html',
  styleUrl: './navbar.css',
})
export class Navbar {
  constructor(public authService : Auth, private router : Router) {
  }
  handleLogout(){
    this.authService.logout();
    this.router.navigateByUrl("/login");
  }
}
