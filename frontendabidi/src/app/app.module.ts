import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

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
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule, HttpClientXsrfModule } from '@angular/common/http';
import { AuthActivateRouteGuard } from './routeguards/auth.routeguard';
import { XhrInterceptor } from './interceptors/app.request.interceptor';
import { LogoutComponent } from './logout/logout.component';
import { SweetAlert2Module } from '@sweetalert2/ngx-sweetalert2';
const routes: Routes = [
  { path:"", component:HomeComponent , canActivate:[AuthActivateRouteGuard]},
  { path:"cart", component:CartComponent , canActivate:[AuthActivateRouteGuard]},
  { path:"checkout", component:CheckoutComponent , canActivate:[AuthActivateRouteGuard]},
  //{ path:"dashboard", component:DashboardComponent, canActivate:[AuthActivateRouteGuard] },
  //{ path:"address", component:AddressComponent , canActivate:[AuthActivateRouteGuard]},
  //{ path:"edit-address", component:EditAddressComponent , canActivate:[AuthActivateRouteGuard]},
  { path:"forgot-password", component:ForgotPasswordComponent , canActivate:[AuthActivateRouteGuard]},
  { path:"login", component:LoginComponent },
  //{ path:"orders", component:OrdersComponent , canActivate:[AuthActivateRouteGuard]},
  { path:"profile-details", component:ProfileDetailsComponent, canActivate:[AuthActivateRouteGuard] },
  { path:"product-single/:id", component:ProductsingleComponent, canActivate:[AuthActivateRouteGuard] },
  { path:"shop", component:ShopComponent , canActivate:[AuthActivateRouteGuard]},
  { path:"signup", component:SignupComponent },
  { path: 'logout', component: LogoutComponent},

  { path:"**", redirectTo:"/login", pathMatch:"full"}
];

export const routing = RouterModule.forRoot(routes);

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
    LogoutComponent,
    
  ],
  imports: [
    // RouterModule.forRoot(routes),
    BrowserModule,
    SlickCarouselModule,RouterModule.forRoot(routes),
    // NgModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(routes),
    HttpClientXsrfModule.withOptions({
      cookieName: 'XSRF-TOKEN',
      headerName: 'X-XSRF-TOKEN',
    }),
    SweetAlert2Module.forRoot()

  ],  exports: [RouterModule],

   providers: [{
    provide : HTTP_INTERCEPTORS,
    useClass : XhrInterceptor,
    multi : true
  },
 AuthActivateRouteGuard
],
  bootstrap: [AppComponent]
})
export class AppModule { }
