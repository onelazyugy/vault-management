import {Location} from '@angular/common';
import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/do';

import { AddEntryModel } from '../admin/addentrymodel';
import { CommonService } from './common-service';

@Injectable()
export class AdminService {
    private _location: Location;
    private _addEntryURL = '';
    private _queryEntriesURL = '';

    constructor(private _http: Http, private commonService: CommonService){}

    addEntry(addEntry: AddEntryModel): Observable<any> {
        this._addEntryURL = this.commonService.buildRequestURL() + '/rs/addEntry';
        let bodyRequest = JSON.stringify(addEntry);
        let headers = new Headers({'Content-Type': 'application/json'});
        let options = new RequestOptions({headers: headers});
        return this._http.post(this._addEntryURL, bodyRequest, options)
                .map((res: Response) => <string> res.json())
                .do(data => console.log('/addEntry api result ==>: ' + JSON.stringify(data)))
                .catch(this.handleError);
    }

    queryEntries(): Observable<any> {
        this._queryEntriesURL = this.commonService.buildRequestURL() + '/rs/queryEntries';
        return this._http.get(this._queryEntriesURL)
                .map((res: Response) => res.json())
                .do(data => console.log('/queryEntries api result ==>: ' + JSON.stringify(data)))
                .catch(this.handleError);
    }

    private handleError(error: Response){
        console.error('handleError ==>: ' + error);
        return Observable.throw(error.json().error || 'Server error');
    }
}