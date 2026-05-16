import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { jwtDecode } from 'jwt-decode';
@Injectable({
  providedIn: 'root',
})
export class Auth {

  isAuthenticated : boolean = false;
  roles : any;
  username : any;
  accessToken! : any;
  constructor(private http: HttpClient) {
    if (typeof window !== 'undefined') {
      let token = localStorage.getItem("access-token");
      if (token) {
        this.loadProfile({ "access-token": token });
      }
    }
  }

  public login(username : string, password : string){
    let options = {
      headers : new HttpHeaders().set("Content-Type","application/x-www-form-urlencoded")
    }
    let params = new HttpParams()
      .set("username",username).set("password",password);
    return this.http.post("http://localhost:8085/auth/login",params,options)
  }
  loadProfile(data: any){
    this.isAuthenticated = true;
    this.accessToken = data["access-token"];
    localStorage.setItem("access-token", this.accessToken);
  let decodedJwt = jwtDecode(this.accessToken) as any;
  this.username = decodedJwt.sub;
  this.roles = decodedJwt.scope;
  }
  logout(){
    this.isAuthenticated=false;
    this.accessToken=undefined;
    this.username=undefined;
    this.roles=undefined;
    localStorage.removeItem("access-token");
  }
}
