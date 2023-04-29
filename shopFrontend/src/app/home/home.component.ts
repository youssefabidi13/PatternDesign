import { Component, OnInit } from '@angular/core';
import { Product, ProductsService } from '../services/products.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  //Slider settings
  slideConfig = {"slidesToShow": 1, "slidesToScroll": 1} ;
  
  products: Product[] = [];
  productPopular: Product[] = [];
  productNewest: Product[] =[];
  constructor(private productService: ProductsService) { }

  ngOnInit(): void {
    this.getProductList();
    this.getProductListPopu();
    this.getProductListNew();
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
}