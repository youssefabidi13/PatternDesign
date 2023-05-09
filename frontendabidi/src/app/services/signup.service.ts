import { Injectable } from '@angular/core';
import { Customer } from '../common/customer';
import { Observable } from 'rxjs/internal/Observable';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SignupService {

  private customerUrl = 'http://localhost:8888/api/customers/register'

  constructor(private httpClient: HttpClient) { }
  register(customer: Customer): Observable<any>{
    
    return this.httpClient.post<Customer>(this.customerUrl,customer);
  }
}
