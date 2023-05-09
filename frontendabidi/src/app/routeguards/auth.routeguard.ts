import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot,Router } from '@angular/router';
import { Customer } from '../common/customer';

@Injectable()
export class AuthActivateRouteGuard implements CanActivate {
    user = new Customer();
    
    constructor(private router: Router){

    }

    canActivate(): boolean {
        let user = JSON.parse(sessionStorage.getItem('userdetails') || '{}');
        if (!user || !user.authStatus) {
            this.router.navigate(['/login']);
            return false;
        }
        return true;
    }
    

}
