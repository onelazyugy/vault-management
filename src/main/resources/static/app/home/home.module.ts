import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';//for ngModel
import { CommonModule } from '@angular/common';//for ngIf and pipe and more...

import { SearchModule } from '../search/search.module';
import { SearchComponent } from '../search/search.component';
import { HomeComponent } from './home.component';
import { LoginComponent } from '../login/login.component';

//modal
import {PromptComponent} from "../search/enter.password.dialog.component";
import { BootstrapModalModule } from 'ng2-bootstrap-modal';


@NgModule({
    declarations: [
        SearchComponent,
        LoginComponent,
        PromptComponent
    ],
    imports:[
        FormsModule,
        ReactiveFormsModule,
        //for more
        BootstrapModalModule,
        CommonModule,
        RouterModule.forChild([
            {path: 'home', component: HomeComponent}
        ])
    ],
    //for modal
    entryComponents: [
        PromptComponent
    ],
    exports: [
        SearchComponent,
        LoginComponent
    ],
    providers: [
    
    ]
})

export class HomeModule{}