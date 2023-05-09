import { ProductsService } from './../services/products.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit{
 
  categories: String[] = [];
  constructor(private productService: ProductsService) { }
  ngOnInit(): void {
    this.getCategories();
  }
  getCategories(): void {
    this.productService.getCategoriesFromProduct().subscribe(categories => {
      this.categories = categories;
    });
    
  }

}
