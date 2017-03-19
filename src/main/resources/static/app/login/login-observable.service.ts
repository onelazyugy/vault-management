import { Injectable } from '@angular/core';
import { Subject }    from 'rxjs/Subject';

import { IUser } from './user';

@Injectable()
export class LoginObservableService {
    //Observable string sources
    private isUserLogin = new Subject<IUser>();

    //Observable string streams
    userLoginAnnounced$ = this.isUserLogin.asObservable();

    //Service message commands
    announceUserIsLogin(user: IUser){
        console.log('announceUserIsLogin() method and message is: ' + JSON.stringify(user));
        this.isUserLogin.next(user);
    }
}