import { Component, OnInit } from '@angular/core';
import { Customer } from '../common/customer';
import { AbstractControl, FormBuilder, Validators } from '@angular/forms';
import { LoginService } from '../services/login.service';
import Swal from 'sweetalert2/dist/sweetalert2.js';
import { Router } from '@angular/router';

function passwordMatchValidator(
  control: AbstractControl
): { [key: string]: boolean } | null {
  const password = control.get('password');
  const confirm = control.get('confirm');
  if (password?.value !== confirm?.value) {
    return { passwordMismatch: true };
  }
  return null;
}

@Component({
  selector: 'app-profile-details',
  templateUrl: './profile-details.component.html',
  styleUrls: ['./profile-details.component.css'],
})
export class ProfileDetailsComponent implements OnInit {
  user = new Customer();
  constructor(
    private fb: FormBuilder,
    private fbI: FormBuilder,
    private loginService: LoginService,
    private router: Router
  ) {}
  updateForm = this.fb.group(
    {
      current_password: ['', Validators.required],
      password: ['', Validators.required],
      confirm: ['', Validators.required],
    },
    {
      validator: passwordMatchValidator,
    }
  );

  updateInfo = this.fbI.group({
    bio: [''],
    phone: [''],
  });
  ngOnInit(): void {
    if (sessionStorage.getItem('userdetails')) {
      this.user = JSON.parse(sessionStorage.getItem('userdetails')!);
    }
  }

  onSubmit() {
    if (this.updateForm.invalid) {
      return;
    }

    const currentPassword = this.updateForm.get('current_password')?.value;
    const newPassword = this.updateForm.get('password')?.value;

    this.loginService.updatePassword(currentPassword, newPassword,this.user.id).subscribe(
      (data) => {
        // Handle success
        console.log('Password updated successfully');
      }
    );
  }
  onSubmitInfo() {
    if (this.updateInfo.invalid) {
      return;
    }
    const bio = this.updateInfo.get('bio')?.value || '';
    const phone = this.updateInfo.get('phone')?.value || '';
    //this.user.bio = bio;
    // console.log(this.user.bio+'ZDZDZ '+this.user.phoneNumber);
    // this.user.phoneNumber = phone;
    // this.user.authStatus = '';
    // console.log(this.user);
    this.loginService.updateInfo(bio,phone,this.user.id).subscribe(
      (data) => {
        Swal.fire({
          position: 'center',
          icon: 'success',
          title: 'Update info successful!',
          showConfirmButton: false,
          timer: 1500
        })

        // Handle success
        console.log('Info updated successfully');
      },
      (error) => {
        // Handle error
        Swal.fire({
          position: 'center',
          icon: 'error',
          title: 'Update info failed!',
          showConfirmButton: false,
          timer: 1500
        })

        console.log(error);
      }


    );
  }
}
