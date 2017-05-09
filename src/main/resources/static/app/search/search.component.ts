import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

import { SearchService } from '../services/search-service';
import { SearchModel } from './searchmodel';

import { PromptComponent } from './enter.password.dialog.component';
import { DialogService } from "ng2-bootstrap-modal";

import { Subject } from 'rxjs/Subject';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import 'rxjs/add/operator/debounceTime';

@Component({
    selector: 'search',
    templateUrl: 'app/search/search.component.html',
    providers: [SearchService]
})

//https://blog.thoughtram.io/angular/2016/06/22/model-driven-forms-in-angular-2.html
//http://stackoverflow.com/questions/32051273/angular2-and-debounce
export class SearchComponent implements OnInit{
    model = new SearchModel('');

    searchResults: any[] = [];
    serviceResponseMessage = '';
    serviceResponseStatus : boolean;
    resultCount: number;
    searchString = '';
    searchFormGroup: FormGroup;
    seachControl = new FormControl();
    //input data from the prompt dialog modal
    passwordFromPromptDialog:string = '';

    constructor(private searchService: SearchService, private dialogService:DialogService) {
        this.searchFormGroup = new FormGroup({
            //angular reactive forms, https://blog.thoughtram.io/angular/2016/06/22/model-driven-forms-in-angular-2.html
        })
     }

     public view(id:string){
        this.dialogService.addDialog(PromptComponent, {
        title:'Prompt',
        question:'Enter your master password*: ',
        data: id   }, { closeByClickingOutside:false,  backdropColor: 'rgba(6, 25, 13, 0.57)'})
        .subscribe((message)=>{
            //We get dialog result
            console.log('input result from prompt dialog: ' + message)
            this.passwordFromPromptDialog = message;
        });

     }

    ngOnInit() {  
        this.seachControl
        .valueChanges
        .debounceTime(500)
        .subscribe(
            userSearchInput => {
                this.resultCount = 0;
                this.model.query = userSearchInput;
                this.searchService.search(this.model).subscribe(
                    data => {
                        if(data != null && data.queryResponses != null){
                            if(data.queryResponses.length > 0){
                                this.searchResults = data;
                                this.resultCount = data.queryResponses.length;
                                this.serviceResponseMessage = data.serviceResponseStatus.message
                                this.serviceResponseStatus = data.serviceResponseStatus.success;
                            }
                        } else {
                            this.searchResults = data;
                        }
                    }, 
                    error => {
                        console.log('search request error: ' + error)
                    },
                    () => {
                        console.log('Completed search request');
                    }
                );
            });
    }
}