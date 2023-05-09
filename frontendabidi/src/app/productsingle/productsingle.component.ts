import { Component, OnInit } from '@angular/core';
import { Product, ProductsService } from '../services/products.service';
import { ActivatedRoute, Route } from '@angular/router';
import { Observable, map } from 'rxjs';
import { Router } from '@angular/router';
import { CartServiceService } from '../services/cart-service.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2/dist/sweetalert2.js';


@Component({
  selector: 'app-productsingle',
  templateUrl: './productsingle.component.html',
  styleUrls: ['./productsingle.component.css'],
})
export class ProductsingleComponent implements OnInit {
  product: Product = new Product();
  products: Product[] = [];
  cartForm!: FormGroup;

  constructor(
    private productSerivce: ProductsService,
    private route: ActivatedRoute,private router: Router,
    private cartService: CartServiceService,
    private formBuilder: FormBuilder
  ) {}
  ngOnInit(): void {
    this.handleProductDetails();
    this.cartForm = this.formBuilder.group({
      quantity: [1, [Validators.required, Validators.min(1)]]
    });
  }

  handleProductDetails() {
    const theProductId: number = +this.route.snapshot.paramMap.get('id')!;
    console.log(theProductId);

    this.productSerivce.getProduct(theProductId).subscribe((data) => {
      this.product = data;
      console.log(data);
      const category = this.product?.category || 'Chemise';
      this.handleProduct(category.toString());
    });
  }

  handleProduct(category: string) {
    console.log('Category:', category);

    this.productSerivce.getProductCategories(category).subscribe((data) => {
      this.products = data;
      console.log(data);
    });
  }
  onSubmit(theProduct: Product){
    console.log(this.cartForm.value);
    if (this.cartForm.invalid) {
      this.cartForm.markAllAsTouched();
      return;
    }
    this.cartService.addManyToCart(theProduct,this.cartForm.value.quantity).subscribe(
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
   );

 }
  
}
