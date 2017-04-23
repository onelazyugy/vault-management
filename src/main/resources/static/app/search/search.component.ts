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
   
    searchFormGroup: FormGroup;
    seachControl = new FormControl();

    constructor(private searchService: SearchService) {
        this.searchFormGroup = new FormGroup({
            //angular reactive forms, https://blog.thoughtram.io/angular/2016/06/22/model-driven-forms-in-angular-2.html
        })
     }

    ngOnInit() {
        console.log('ngOnInit from search component');
        
        this.seachControl.valueChanges
            .debounceTime(500)
            .subscribe(
                query => {
                    this.model.query = query;
                    console.log("model for search is: " + this.model.query);
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
                    );
                    this.searchString = query;
                    console.log("my search query is: ==>: " + query);
                });

    /*
    query(){
        console.log('query() model==>' + this.model.query);
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

        /*
        this.searchQueryControl.valueChanges
        .debounceTime(500)
        .subscribe(
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
        );
        */
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