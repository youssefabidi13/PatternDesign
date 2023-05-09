import { Component, OnInit } from '@angular/core';
import { Customer } from '../common/customer';
import { CartServiceService, Order } from '../services/cart-service.service';
import Swal from 'sweetalert2/dist/sweetalert2.js';


@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit{
  user = new Customer();
  orders?: Order[];
  totalAmount?: number;

  constructor(private cartService: CartServiceService) {
    
  }
  ngOnInit(): void {
    if(sessionStorage.getItem('userdetails')){
      this.user = JSON.parse(sessionStorage.getItem('userdetails')!);
    }
    this.cartService.getOrdersByCustomerId(this.user.id!).subscribe(
      orders => {
        this.orders= orders ;
        console.log(this.orders);
      },
      error => console.log(error)
    );
    this.cartService.getTotalAmount(this.user.id!).subscribe(
      total => {
        this.totalAmount = total;
      },
      error => console.log(error)
    )
  }
  deleteOrder(id: number): void {
    this.cartService.deleteOrder(id).subscribe(() => {
    Swal.fire({
      position: 'center',
      icon: 'success',
      title: 'Delete order successful!',
      showConfirmButton: false,
      timer: 1500
    })

      this.orders = this.orders!.filter(order => order.id !== id);
    });
  }

}
