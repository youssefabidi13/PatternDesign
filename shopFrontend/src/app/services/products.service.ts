import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { catchError, map } from 'rxjs/operators';



@Injectable({
  providedIn: 'root'
})
export class ProductsService {

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
  
  constructor(private httpClient: HttpClient) { }
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

