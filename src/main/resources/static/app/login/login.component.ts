import { Component, Output, EventEmitter } from '@angular/core';
import {NgForm} from '@angular/forms';

import { IUser } from './user';
import { UserModel } from './usermodel';
import { LoginObservableService } from './login-observable.service';
import { UserAuthService } from '../services/user-auth.service';

@Component({
    selector: 'login',
    templateUrl: 'app/login/login.component.html',
    providers: [UserAuthService]
})

export class LoginComponent {
    panelTitle: string = 'Login';
    user: IUser = {'username': '', 'password': '', isLogin: false};
    model = new UserModel('', ''); 
    messageLabel: string = '';

    constructor(private loginObservableService: LoginObservableService, private userAuthService: UserAuthService){}
    
    onUserNameChange(value: string, loginForm: NgForm){
        this.model.username = value;
        if(loginForm.valid){
            this.messageLabel = '';
        }
    }

    onPasswordChange(value: string, loginForm: NgForm){
        this.model.password = value;
        if(loginForm.valid){
            this.messageLabel = '';
        }
    }

    login(loginForm: NgForm){
        console.log('login() model==>' + JSON.stringify(loginForm.value));
        let username = loginForm.value.username;
        let password = loginForm.value.password;

        if(username && password){            
            if(loginForm.value.password != '' && loginForm.value.username != ''){
                //call backend to verify the credentials
                this.user.username = username;
                this.user.password = password;
                this.userAuthService.login(this.user).subscribe(
                        data => {
                            console.log('/login result ==>: ' + data);
                            if(data){
                                //notify parents but empty the password first
                                this.user.isLogin = true;
                                this.user.password = null;
                                this.loginObservableService.announceUserIsLogin(this.user);
                            } else {
                                this.user.isLogin = false;
                                this.messageLabel = 'incorrect credentials';
                            }                      
                        },
                        error => {
                            console.log('Error login: ' + error);
                            this.user.isLogin = false;
                            this.messageLabel = 'failed login';
                            return false;
                        },
                        () => {
                            console.log('Completed login request');
                        }
                    );                
            } else {
                this.messageLabel = 'field cannot be empty';
            }
        } else {
            this.messageLabel = 'fail';
        }
    }
}