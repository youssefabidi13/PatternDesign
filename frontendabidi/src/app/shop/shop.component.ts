import { Component, OnInit } from '@angular/core';
import {  Product, ProductsService } from '../services/products.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CartServiceService } from '../services/cart-service.service';
import Swal from 'sweetalert2/dist/sweetalert2.js';

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.css'],
})
export class ShopComponent implements OnInit {
  products: Product[] = [];

  constructor(private productService: ProductsService, private cartService: CartServiceService, private router: Router) { }

  ngOnInit(): void {
    this.getProductList();
  }

  getProductList(): void {
    this.productService.getProducts().subscribe(products => {
      this.products = products;
    });
  }
//   addToCart(theProduct: Product){
//     this.cartService.addManyToCart(theProduct,1).subscribe(
//     //  (order) => {
//     //    console.log('Order created successfully:', order);
//     //  },
//      (error) => {
//        console.log('Error creating order:', error);
//      }
//    );

//  }
addToCart(theProduct: Product){
  this.cartService.addManyToCart(theProduct,1).subscribe(
   
    (order) => {
     Swal.fire({
       position: 'center',
       icon: 'success',
       title: 'Add to cart successful!',
       showConfirmButton: false,
       timer: 1500
     }).then(() =>{
       this.router.navigateByUrl('/cart');
    },
 
   (error) => {
     console.log('Error creating order:', error);
     
     Swal.fire({
       position: 'center',
       icon: 'error',
       title: 'Add to cart failed!',
       showConfirmButton: false,
       timer: 1500
     })
   }
 );
  
}
  )
}
}
