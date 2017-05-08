import { Component } from '@angular/core';
import { DialogComponent, DialogService } from "ng2-bootstrap-modal";

import { RetrievePasswordModel } from './retrievepasswordmodel';
import { SearchService } from '../services/search-service';

export interface PromptModel {
    title:string;
    question:string;
    data:string
}

@Component({
    selector: 'prompt',
    providers: [SearchService],
    template: `<div class="modal-dialog">
                <div class="modal-content">
                   <div class="modal-header">
                     <button type="button" class="close" (click)="close()">&times;</button>
                     <h4 class="modal-title">{{title || 'Prompt'}}</h4>
                   </div>
                   <div class="modal-body">
                    <label>{{question}}</label><input type="password" class="form-control" [(ngModel)]="message" name="name" ><br />
                    <div class="row">
                        <div class="col-md-6">{{response}}</div>                
                        <div class="col-md-6">
                            <button type="button" class="btn btn-primary pull-right" (click)="getPassword()">GET PASSWORD</button>
                        </div>
                    </div>
                   </div>
                   <div class="modal-footer">
                     <button type="button" class="btn btn-primary" (click)="apply()">OK</button>
                     <button type="button" class="btn btn-default" (click)="close()" >Cancel</button>
                   </div>
                 </div>
                </div>`
})
export class PromptComponent extends DialogComponent<PromptModel, string> implements PromptModel {
    title: string;
    question: string;
    message: string = '';
    data: string = '';
    response: string = '';
    model = new RetrievePasswordModel('', '');
    
    constructor(private searchService: SearchService, dialogService: DialogService) {
        super(dialogService);
    }

    apply() {
        this.result = this.message;
        this.close();
    }

    getPassword(){
        console.log('getPassword button clicked!--' + this.message);
        this.model.id = this.data.trim();
        this.model.password = this.message.trim();
        this.searchService.getPassword(this.model).subscribe(
            data => {
                console.log("data from getPassword==> " + JSON.stringify(data));
                if(data != null && data.serviceResponseStatus != null){
                    if(data.serviceResponseStatus.success){
                        this.response = data.password;
                    } else {
                        this.response = data.serviceResponseStatus.message;
                    }
                }
            }, 
            error => {
                console.log('search request error: ' + error)
                this.response = error;
            },
            () => {
                console.log('Completed search request');
            }
        )
    }
}