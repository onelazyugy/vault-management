import { Injectable } from '@angular/core';
import { Subject }    from 'rxjs/Subject';

@Injectable()
export class LogoutObservableService {
    //Observable string sources
    private isUserLogout = new Subject<string>();

    //Observable string streams
    userLogoutAnnounced$ = this.isUserLogout.asObservable();

    //Service message commands
    announceUserIsLogout(message: string){
        console.log('announceUserIsLogout() method');
        this.isUserLogout.next(message);
    }
}