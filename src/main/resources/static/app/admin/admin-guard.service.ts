import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router} from '@angular/router';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';

import { UserAuthService } from '../services/user-auth.service';
import { IUser } from '../login/user';

@Injectable()
export class AdminRouteGuard implements CanActivate{
    isEnableAdminRoute: boolean = false;

    constructor(private _router: Router, private userAuthService: UserAuthService){
        console.log('AdminRouteGuard contructor...');
    }

    canActivate(route: ActivatedRouteSnapshot) {
        console.log('canActivate method from AdminRouteGuard: ' + route.url[0].path);
        return this.userAuthService.isUserLogin().map(
        res => {
            console.log('isUserLogin api returns: ' + res);
            if(res){
                return true;
            } 
            this._router.navigate(['/home']);
            return false;
        }).catch((err:Error) => {
            console.log('catch block.....: '+ err);
            this._router.navigate(['/home']);
            return Observable.of(false);
        });
    }
}