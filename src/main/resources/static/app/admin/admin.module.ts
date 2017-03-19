import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';//for ngIf and pipe and more...
import { FormsModule } from '@angular/forms';//for ngModel

import { AdminAddContent } from './admin-add-content.component';
import { AdminEditContent } from './admin-edit-content.component';
import { AdminComponent } from './admin.component';
import { AdminRouteGuard } from './admin-guard.service';
import { UserAuthService } from '../services/user-auth.service';

@NgModule({
    declarations: [ 
       AdminAddContent,
       AdminEditContent
    ],
    imports: [
        FormsModule,
        CommonModule,
        RouterModule.forChild([
            {path: 'admin', canActivate: [AdminRouteGuard], component: AdminComponent}
        ])
    ],
    exports: [
        AdminAddContent,
        AdminEditContent
    ],
    providers: [
        AdminRouteGuard,
        UserAuthService
    ]    
})

export class AdminModule {}