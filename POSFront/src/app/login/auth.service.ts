import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {Employee} from '../shared/employee.model';

interface loginResponse {
  token: string;
  email: string;
}

@Injectable({providedIn: 'root'})
export class AuthService {

  constructor(private http: HttpClient, private router: Router) {}

  login(employee: Employee) {
    return this.http.post<loginResponse>('http://localhost:8080/login', employee);
  }

}
