import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { SweetAlert2Module } from '@sweetalert2/ngx-sweetalert2';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { HomeComponent } from './home/home.component';
import { ProductsingleComponent } from './productsingle/productsingle.component';
import { CartComponent } from './cart/cart.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { ShopComponent } from './shop/shop.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { OrdersComponent } from './orders/orders.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { ProfileDetailsComponent } from './profile-details/profile-details.component';
import { AddressComponent } from './address/address.component';
import { EditAddressComponent } from './edit-address/edit-address.component';
import { SlickCarouselModule } from 'ngx-slick-carousel';
import { RouterModule, Routes } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
const routes: Routes = [
  { path:"", component:HomeComponent },
  { path:"cart", component:CartComponent },
  { path:"checkout", component:CheckoutComponent },
  { path:"dashboard", component:DashboardComponent },
  { path:"address", component:AddressComponent },
  { path:"edit-address", component:EditAddressComponent },
  { path:"forgot-password", component:ForgotPasswordComponent },
  { path:"login", component:LoginComponent },
  { path:"orders", component:OrdersComponent },
  { path:"profile-details", component:ProfileDetailsComponent },
  { path:"product-single/:id", component:ProductsingleComponent },
  { path:"shop", component:ShopComponent },
  { path:"signup", component:SignupComponent }

];
@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    ProductsingleComponent,
    CartComponent,
    CheckoutComponent,
    ShopComponent,
    DashboardComponent,
    OrdersComponent,
    LoginComponent,
    SignupComponent,
    ForgotPasswordComponent,
    ProfileDetailsComponent,
    AddressComponent,
    EditAddressComponent,
    
  ],
  imports: [
    // RouterModule.forRoot(routes),
    BrowserModule,
    SlickCarouselModule,RouterModule.forRoot(routes),
    // NgModule,
    ReactiveFormsModule,
    HttpClientModule,
    SweetAlert2Module.forRoot()

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
