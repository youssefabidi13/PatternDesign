import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { catchError, map } from 'rxjs/operators';



@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  constructor(private httpClient: HttpClient) { }
  private baseUrl='http://localhost:8888/api/products/all';
  
  getProducts(): Observable<Product[]> {
    const url = `${this.baseUrl}`;
    return this.httpClient.get<Product[]>(url)
      .pipe(
        catchError((error) => {
          console.log(error);
          throw error;
        })
      );
  }

  private baseUrl1='http://localhost:8888/api/products/popular';
  getProductsPopular(): Observable<Product[]> {
    const url = `${this.baseUrl1}`;
    return this.httpClient.get<Product[]>(url)
      .pipe(
        catchError((error) => {
          console.log(error);
          throw error;
        })
      );
  }
  private baseUrl2='http://localhost:8888/api/products/latest';
  getProductsNewest(): Observable<Product[]> {
    const url = `${this.baseUrl2}`;
    return this.httpClient.get<Product[]>(url)
      .pipe(
        catchError((error) => {
          console.log(error);
          throw error;
        })
      );
  }
  private categories='http://localhost:8888/api/products/categories';
  getCategoriesFromProduct(): Observable<String[]> {
    const url = `${this.categories}`;
    return this.httpClient.get<String[]>(url)
      .pipe(
        catchError((error) => {
          console.log(error);
          throw error;
        })
      );
  }

  private productUrl='http://localhost:8888/api/products';
  getProduct(theProductId: number): Observable<Product> {
    // need to build a url based on product id
    const productUrl = `${this.productUrl}/${theProductId}`;
    return this.httpClient.get<Product>(productUrl);
  }

  private category='http://localhost:8888/api/products/categories';
  getProductCategories(theCategory: String): Observable<Product[]> {
    // need to build a url based on product id
    const categoryUrl = `${this.category}/${theCategory}`;
    return this.httpClient.get<Product[]>(categoryUrl);
  }
  
}

export class Product {
  id?: number
  sku?: string
  nbAchat?: number
  name?: string
  description?: string
  unitPrice?: number
  salesPrice?: number
  active?: boolean
  unitsInStock?: number
  dateCreated?: string
  lastUpdated?: string
  category?: string[]
  images?: Image[]
  sizes?: Size[]
}

export class Image {
  id?: number
  imageName?: string
}

export class Size {
  id?: number
  size?: string
}


