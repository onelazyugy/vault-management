import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/do';

import { SearchModel } from '../search/searchmodel';
import { RetrievePasswordModel } from '../search/retrievepasswordmodel';
import { CommonService } from './common-service';

@Injectable()
export class SearchService {
    private _searchURL = '';
    private _retrievePasswordByIdURL = '';

    constructor(private _http: Http, private CommonService: CommonService){}

    search(searchModel: SearchModel): Observable<any>{
        this._searchURL = this.CommonService.buildRequestURL() + "/rs/search";
        let bodyRequest = JSON.stringify(searchModel);
        let headers = new Headers({'Content-Type':'application/json'});
        let options = new RequestOptions({headers: headers});
        return this._http.post(this._searchURL, bodyRequest, options)
                .map((res: Response) => <string> res.json())
                .do(data => console.log('/search api result ==>: ' + JSON.stringify(data)))
                .catch(this.handleError);
    }   

    getPassword(retrievePasswordModel: RetrievePasswordModel): Observable<any>{
        this._retrievePasswordByIdURL = this.CommonService.buildRequestURL() + "/rs/queryEntryById/" + retrievePasswordModel.id;
        return this._http.get(this._retrievePasswordByIdURL)
                        .map((res: Response) => res.json())
                        .do(data => console.log('/queryEntryById api result ==>: ' + JSON.stringify(data)))
                        .catch(this.handleError);                
    }

    private handleError(error: Response){
        console.error('handleError ==>: ' + error);
        return Observable.throw(error.json().error || 'Server error');
    }
}