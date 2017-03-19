import {Location} from '@angular/common';
import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/do';

import { IUser } from '../login/user';
import { CommonService } from './common-service';


@Injectable()
export class UserAuthService {
    private _location: Location;
    private _loginUrl = "";
    private _isUserStillAliveUrl = "";
    private _isUserLoggedIn = "";
    private _logoutUrl = "";

    constructor(private _http: Http, private commonService: CommonService){}

    login(user: IUser): Observable<string> {
        this._loginUrl = this.commonService.buildRequestURL() + "/rs/login";//this.buildRequestURL() + "/rs/login";
        let bodyRequest = JSON.stringify(user);
        let headers = new Headers({'Content-Type':'application/json'});
        let options = new RequestOptions({headers: headers});
        return this._http.post(this._loginUrl, bodyRequest, options)
                .map((res: Response) => <string> res.json())
                .do(data => console.log('/login api result ==>: ' + JSON.stringify(data)))
                .catch(this.handleError);   
    }

    userStillAlive(){
        this._isUserStillAliveUrl = this.commonService.buildRequestURL() + "/rs/userStillAlive";//this.buildRequestURL() + "/rs/userStillAlive";
        return this._http.get(this._isUserStillAliveUrl)
                .map((res: Response) => <IUser> res.json())
                .do(data => console.log('/isUserStillAlive api result ==>: ' + JSON.stringify(data)))
                .catch(this.handleError);
    }
    
    isUserLogin(): Observable<boolean> {
        this._isUserLoggedIn = this.commonService.buildRequestURL() + "/rs/isUserLoggedIn";//this.buildRequestURL() + "/rs/isUserLoggedIn";
        return this._http.get(this._isUserLoggedIn)
                    .map((res: Response) => <boolean> res.json())
                    .do(data => console.log('/isUserLoggedIn api result ==>: ' + JSON.stringify(data)))
                    .catch(this.handleError);;
    }

    logout(){
        this._logoutUrl = this.commonService.buildRequestURL() + "/rs/logout";//this.buildRequestURL() + "/rs/logout";
        return this._http.get(this._logoutUrl)
                .map((res: Response) => <string> res.json())
                .do(data => console.log('/logout api result ==>: ' + JSON.stringify(data)))
                .catch(this.handleError);
    }
    
    private handleError(error: Response){
        console.error('handleError ==>: ' + error);
        return Observable.throw(error.json().error || 'Server error');
    }
}