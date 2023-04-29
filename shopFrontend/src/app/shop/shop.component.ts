import { Component, OnInit } from '@angular/core';
import {  Product, ProductsService } from '../services/products.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.css'],
})
export class ShopComponent implements OnInit {
  products: Product[] = [];

  constructor(private productService: ProductsService) { }

  ngOnInit(): void {
    this.getProductList();
  }

  getProductList(): void {
    this.productService.getProducts().subscribe(products => {
      this.products = products;
    });
  }
}
