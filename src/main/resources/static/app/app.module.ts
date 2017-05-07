import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router';

import { BootstrapModalModule } from 'ng2-bootstrap-modal';

import { AppComponent }  from './app.component';
import { LogoModule } from './shared/shared-logo.module';
import { FooterModule } from './shared/shared-footer.module';
import { HomeModule } from './home/home.module';
import { AdminModule } from './admin/admin.module';
import { HomeComponent } from './home/home.component';
import { AdminComponent } from './admin/admin.component';
import { CommonService } from './services/common-service';

@NgModule({
  imports: [ 
      BrowserModule,
      BootstrapModalModule,
      HttpModule,
      LogoModule,
      FooterModule,
      HomeModule,
      AdminModule,
      RouterModule.forRoot([
        {path: 'home', component: HomeComponent},
        {path: '', redirectTo: 'home', pathMatch: 'full'},//if empty router, then redirect to welcome page
        {path: '**', redirectTo: 'home', pathMatch: 'full'}//any other route that are not found, redirect to welcome page or you can do a custom 404 page
      ],{useHash:true}) 
  ],
  declarations: [ 
    AppComponent,
    HomeComponent, 
    AdminComponent,
  ],
  providers:[
    CommonService//provide a common service for all modules to use
  ],
  bootstrap: [ 
    AppComponent
  ]
})
export class AppModule { }
