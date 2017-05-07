import { Component } from '@angular/core';
import { DialogComponent, DialogService } from "ng2-bootstrap-modal";

export interface PromptModel {
    title:string;
    question:string;
    data:string
}

@Component({
    selector: 'prompt',
    template: `<div class="modal-dialog">
                <div class="modal-content">
                   <div class="modal-header">
                     <button type="button" class="close" (click)="close()">&times;</button>
                     <h4 class="modal-title">{{title || 'Prompt'}}</h4>
                   </div>
                   <div class="modal-body">
                    <label>Selected: {{data}}</label><br />
                    <label>{{question}}</label><input type="password" class="form-control" [(ngModel)]="message" name="name" ><br />
                    <div class="row">
                        <div class="col-md-6">{{password}}</div>                
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
    password: string = '';
    
    constructor(dialogService: DialogService) {
        super(dialogService);
    }

    apply() {
        this.result = this.message;
        this.close();
    }

    getPassword(){
        console.log('getPassword button clicked!--' + this.message);
        this.password = 'password is ...';
    }
}