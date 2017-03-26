import { Component } from '@angular/core';
import {NgForm} from '@angular/forms';

import { SearchService } from '../services/search-service';
import { SearchModel } from './searchmodel';

import { Subject } from 'rxjs/Subject';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';

@Component({
    selector: 'search',
    templateUrl: 'app/search/search.component.html',
    providers: [SearchService]
})

export class SearchComponent {
    model = new SearchModel('');
    searchResults: any[] = [];
    
    /*
    txt: string;
    txtChanged: Subject<string> = new Subject<string>();
    */

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

/*
    changed(text: string){
        this.txtChanged.next(text);
        this.txtChanged.debounceTime(500)
        .distinctUntilChanged()
        .subscribe(v => this.txt = v);
        this.model.query = this.txt;

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
    */
}