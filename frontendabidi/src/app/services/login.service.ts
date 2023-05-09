import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Customer } from '../common/customer';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  getCustomerId(email: string):Observable<number> {
    return this.http.get<number>(`http://localhost:8888/api/customers/${email}`);
  }

  getCustomer(id: number):Observable<Customer> {
    return this.http.get<Customer>(`http://localhost:8888/api/customers/user/${id}`);
  }

  constructor(private http: HttpClient) { }
  validateLoginDetails(user: Customer) {
    window.sessionStorage.setItem("userdetails",JSON.stringify(user));
    return this.http.get('http://localhost:8888/api/customers/user', { observe: 'response',withCredentials: true });
  }

  private passwordUrl = 'http://localhost:8888/api/customers/password';


  updatePassword(currentPassword: string, newPassword: string,id?: number): Observable<any> {
    const body = { currentPassword, newPassword,id };
    const url = `${this.passwordUrl}/update`;
    return this.http.put(url, body);
  }
  updateInfo(bio?: string, phone?: string,id?: number): Observable<any> {
    const url = `http://localhost:8888/api/customers/user/${id}`;
    const body = { bio, phone };
    return this.http.put(url, body);
  }
}
