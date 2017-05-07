import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';//for ngModel
import { CommonModule } from '@angular/common';//for ngIf and pipe and more...

import { SearchModule } from '../search/search.module';
import { SearchComponent } from '../search/search.component';
import { HomeComponent } from './home.component';
import { LoginComponent } from '../login/login.component';


import { BootstrapModalModule } from 'ng2-bootstrap-modal';


@NgModule({
    declarations: [
        SearchComponent,
        LoginComponent
    ],
    imports:[
        FormsModule,
        ReactiveFormsModule,
        BootstrapModalModule,
        CommonModule,
        RouterModule.forChild([
            {path: 'home', component: HomeComponent}
        ])
    ],
    exports: [
        SearchComponent,
        LoginComponent
    ],
    providers: [
    
    ]
})

export class HomeModule{}