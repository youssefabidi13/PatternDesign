import { Component, OnInit } from '@angular/core';
import { Product, ProductsService } from '../services/products.service';
import { ActivatedRoute, Route } from '@angular/router';

@Component({
  selector: 'app-productsingle',
  templateUrl: './productsingle.component.html',
  styleUrls: ['./productsingle.component.css'],
})
export class ProductsingleComponent implements OnInit{
  product: Product = new Product();
  constructor(private productSerivce: ProductsService,
    private route: ActivatedRoute) {}
  ngOnInit(): void {
    this.handleProductDetails();
  }

  handleProductDetails() {
    const theProductId: number = +this.route.snapshot.paramMap.get('id')!;
    console.log(theProductId);

    this.productSerivce.getProduct(theProductId).subscribe(
      data =>{
        this.product=data;
        console.log(data);
      }
    )
  }
}
