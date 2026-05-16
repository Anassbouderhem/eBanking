import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Auth } from '../services/auth';
@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login implements OnInit{
  formLogin! :FormGroup;
  constructor(private fb : FormBuilder, private authService : Auth) {
  }
  ngOnInit() {
    this.formLogin=this.fb.group({
      username : this.fb.control(""),
      password : this.fb.control("")
    })
  }
  handleLogin(){
    let username = this.formLogin.value.username;
    let pwd = this.formLogin.value.password;
    this.authService.login(username,pwd).subscribe({
     next : data => {
       console.log(data)
     },
      error : err => {
       console.log(err);
      }
    })
  }
}
