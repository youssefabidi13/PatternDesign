import { SignupService } from './../services/signup.service';
import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, Validators } from '@angular/forms';
import { Customer } from '../common/customer';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';


function passwordMatchValidator(control: AbstractControl): { [key: string]: boolean } | null {
  const password = control.get('password');
  const confirmPassword = control.get('confirmPassword');
  if (password?.value !== confirmPassword?.value) {
    return { 'passwordMismatch': true };
  }
  return null;
}

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent{
  constructor(private fb: FormBuilder,private signupService: SignupService, private router: Router) { }

  signupForm = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    firstname: ['', Validators.required],
    lastname: ['', Validators.required],
    password: ['', Validators.required],
    confirmPassword: ['', Validators.required]
  }, {
    validator: passwordMatchValidator
  });

  onSubmit() {
    console.log(this.signupForm.value);
    if (this.signupForm.invalid) {
      this.signupForm.markAllAsTouched();
      return;
    }
    let customer = new Customer();
    customer.email = this.signupForm.value.email !;
    customer.firstName = this.signupForm.value.firstname!;
    customer.lastName = this.signupForm.value.lastname!;
    customer.password = this.signupForm.value.password!;
    customer.encryptedPassword = this.signupForm.value.password!;

    this.signupService.register(customer).subscribe({
      //success path

      next: (response) => { 
        this.router.navigateByUrl('/login');
      },
      //exception path
      error: (err) => {
        alert(`There was an error: ${err.message}`);
      },
    });

  }
  
  get email() {
    return this.signupForm.get('email');
  }
  get firstname() {
    return this.signupForm.get('firstname');
  }
  get lastname() {
    return this.signupForm.get('lastname');
  }
  get password() {
    return this.signupForm.get('password');
  }
  get confirmPassword() {
    return this.signupForm.get('confirmPassword');
  }

  
}
