import { Injectable } from '@angular/core';
import { Product } from './products.service';
import { HttpClient } from '@angular/common/http';
import { Customer } from '../common/customer';
import { Observable, catchError, of, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CartServiceService {
 
  
  constructor(private httpClient: HttpClient) { }
  // private orderUrl = 'http://localhost:8888/api/orders/add'


  // addToCart(theProduct: Product): Observable<Order> {
  //   const order = new Order();
  //   const customer = JSON.parse(sessionStorage.getItem('userdetails') || '{}') as Customer;
  //   order.customer = customer;
  //   order.product = theProduct;
  //   console.log(`Creating order for customer with ID ${customer.id}`);
  //   console.log(`Creating order for product with ID ${theProduct.id}`);
  
  //   return this.httpClient.post<Order>(this.orderUrl, order);
  // }
  private orderMany = 'http://localhost:8888/api/orders/addMany'
    addManyToCart(theProduct: Product,quantity: number): Observable<Order> {
    const order = new Order();
    const customer = JSON.parse(sessionStorage.getItem('userdetails') || '{}') as Customer;
    order.customer = customer;
    order.product = theProduct;
    order.quantity = quantity;
    
    console.log(`Creating order for customer with ID ${customer.id}`);
    console.table(order);

    console.log(`quantity ${order.quantity}`);
    console.log(`Creating order for product with ID ${theProduct.id}`);
  
    return this.httpClient.post<Order>(this.orderMany, order);
  }

  private myOrders = 'http://localhost:8888/api/orders'
  getOrdersByCustomerId(customerId: number): Observable<Order[]> {
    const url = `${this.myOrders}/${customerId}`;
    return this.httpClient.get<Order[]>(url);
  }

  deleteOrder(id: number): Observable<any> {
    return this.httpClient.delete(`${this.myOrders}/delete/${id}`, { observe: 'response' });
  }

  private totalAmount = 'http://localhost:8888/api/customers/customer'
  getTotalAmount(customerId: number): Observable<number> {
    const url = `${this.totalAmount}/${customerId}`;
    return this.httpClient.get<number>(url);
  }
}
export class Order {

  id?: number
  product?: Product
  customer?: Customer
  quantity?: number
  dateCreated: Date = new Date();
  lastUpdate: Date = new Date();
  totalPrice?: number;
  
  constructor() {
  }
  getProduct(): Product {
    return this.product!;
  }
  
}
  
  