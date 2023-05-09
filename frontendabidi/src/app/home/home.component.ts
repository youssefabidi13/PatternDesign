import { Component, OnInit } from '@angular/core';
import { Product, ProductsService } from '../services/products.service';
import { CartServiceService } from '../services/cart-service.service';
import { Customer } from '../common/customer';
import Swal from 'sweetalert2/dist/sweetalert2.js';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  
  //Slider settings
  slideConfig = {"slidesToShow": 1, "slidesToScroll": 1} ;
  user = new Customer();
  products: Product[] = [];
  productPopular: Product[] = [];
  productNewest: Product[] =[];

  constructor(private productService: ProductsService,  private cartService: CartServiceService,private router: Router
    ) { }

  ngOnInit(): void {


       this.getProductList();
    this.getProductListPopu();
    this.getProductListNew();
    if(sessionStorage.getItem('userdetails')){
      this.user = JSON.parse(sessionStorage.getItem('userdetails')!);
    }

  }

  getProductList(): void {
    this.productService.getProducts().subscribe(products => {
      this.products = products;
    });
  }
  getProductListPopu(): void {
    this.productService.getProductsPopular().subscribe(products => {
      this.productPopular = products;
    });
    
  }
  getProductListNew(): void {
    this.productService.getProductsNewest().subscribe(products => {
      this.productNewest = products;
    });
    
  }

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