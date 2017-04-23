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
    searchString = '';
    serviceResponseMessage = '';
    serviceResponseStatus : boolean;
    resultCount: number;
   
    searchFormGroup: FormGroup;
    seachControl = new FormControl();

    constructor(private searchService: SearchService) {
        this.searchFormGroup = new FormGroup({
            //angular reactive forms, https://blog.thoughtram.io/angular/2016/06/22/model-driven-forms-in-angular-2.html
        })
     }

    ngOnInit() {  
        this.seachControl
        .valueChanges
        .debounceTime(500)
        .subscribe(
            userSearchInput => {
                this.model.query = userSearchInput;
                this.searchService.search(this.model).subscribe(
                    data => {
                        if(data){                            
                            this.searchResults = data;
                            this.resultCount = data.queryResponses.length;                            
                            this.serviceResponseMessage = data.serviceResponseStatus.message
                            this.serviceResponseStatus = data.serviceResponseStatus.success;                        
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