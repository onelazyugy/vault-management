import { NgModule } from '@angular/core';

import { SearchComponent } from './search.component';

import {PromptComponent} from "./enter.password.dialog.component";

@NgModule({
    declarations: [
        SearchComponent,
        PromptComponent
    ],
    providers: [

    ],
    //Don't forget add component to entryComponents section
    entryComponents: [
        PromptComponent
    ]

})

export class SearchModule{}