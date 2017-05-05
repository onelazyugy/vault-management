import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

import { SearchService } from '../services/search-service';
import { SearchModel } from './searchmodel';

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
    isCliked = false;

    constructor(private searchService: SearchService) {
        this.searchFormGroup = new FormGroup({
            //angular reactive forms, https://blog.thoughtram.io/angular/2016/06/22/model-driven-forms-in-angular-2.html
        })
     }

     public view(id:string){
        console.log('view clicked!: ' + id);
        this.isCliked = true;
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