import { Component } from '@angular/core';
import {NgForm} from '@angular/forms';

import { SearchService } from '../services/search-service';
import { SearchModel } from './searchmodel';

@Component({
    selector: 'search',
    templateUrl: 'app/search/search.component.html',
    providers: [SearchService]
})

export class SearchComponent {
    model = new SearchModel('');
    searchResults: any[] = [];
    
    constructor(private searchService: SearchService) { }
    
    query(){
        console.log('login() model==>' + this.model.query);
        this.searchService.search(this.model).subscribe(
            data => {
                if(data){
                    console.log('search result: ' + data);
                    this.searchResults = data;
                }
            },
            error => {
                console.log('search request error: ' + error)
            },
            () => {
                console.log('Completed search request');
            }
        )
    }
}