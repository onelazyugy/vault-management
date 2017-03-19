import { Component } from '@angular/core';
import {NgForm} from '@angular/forms';

@Component({
    selector: 'search',
    templateUrl: 'app/search/search.component.html'
})

export class SearchComponent {
    model = {'query': ''};

    query(){
        console.log('login() model==>' + this.model.query);
    }

    oneTextEnter(queryString: string, searchForm: NgForm){
        //TODO: 
        this.model.query = queryString;
    }
}