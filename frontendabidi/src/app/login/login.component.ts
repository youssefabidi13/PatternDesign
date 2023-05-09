import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, NgForm } from '@angular/forms';
import { Customer } from '../common/customer';
import { Router } from '@angular/router';
import { LoginService } from '../services/login.service';
import { getCookie } from 'typescript-cookie';
import Swal from 'sweetalert2/dist/sweetalert2.js';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  authStatus: string = '';
  model = new Customer();

  constructor(private loginService: LoginService, private router: Router) {}

  ngOnInit(): void {}

  // validateUser(loginForm: NgForm) {
  //   this.loginService
  //     .validateLoginDetails(this.model)
  //     .subscribe((responseData) => {
  //       console.log(responseData);
  //       //read jwt token from response header
  //       window.sessionStorage.setItem(
  //         'Authorization',
  //         responseData.headers.get('Authorization')!
  //       );
  //       this.model = <any>responseData.body;
  //       let xsrf = getCookie('XSRF-TOKEN')!;
  //       window.sessionStorage.setItem('XSRF-TOKEN', xsrf);
  //       this.model.authStatus = 'AUTH';
  //       this.model.password = loginForm.value.password;
        

  //       this.loginService
  //         .getCustomerId(loginForm.value.email)
  //         .subscribe((data) => {
  //           this.model.id = data;
  //           console.log(data);
  //           window.sessionStorage.setItem(
  //             'userdetails',
  //             JSON.stringify(this.model)
  //           );
            
            
  //             this.router.navigate(['/']);
        
  //         });
  //     });
  // }
  validateUser(loginForm: NgForm) {
    this.loginService.validateLoginDetails(this.model).subscribe(
      (responseData) => {
        if (responseData.status === 200) {
          // show success message with SweetAlert
          Swal.fire({
            position: 'center',
            icon: 'success',
            title: 'Login successful!',
            showConfirmButton: false,
            timer: 1500,
          }).then(() => {
            window.location.reload();
          });
        }
  
        // read jwt token from response header
        window.sessionStorage.setItem(
          'Authorization',
          responseData.headers.get('Authorization')!
        );
        
        this.model = <any>responseData.body;
        let xsrf = getCookie('XSRF-TOKEN')!;
        window.sessionStorage.setItem('XSRF-TOKEN', xsrf);
        this.model.authStatus = 'AUTH';
        this.model.password = loginForm.value.password;
        
        this.loginService.getCustomerId(loginForm.value.email).subscribe(
          (data) => {
            this.model.id = data;
            console.log(data);
            window.sessionStorage.setItem(
              'userdetails',
              JSON.stringify(this.model)
            );
            
            this.router.navigate(['/']);
          }
        );
      },
      (error) => {
        // show error message with SweetAlert
        Swal.fire({
          icon: 'error',
          title: 'Login failed!',
          text:'you have entered wrong email or password',
          showConfirmButton: true
        });
      }
    );
  }
  
  
}
