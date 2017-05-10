import { Component, OnInit } from '@angular/core';

import { AdminService } from '../services/admin-services';

@Component({
    selector: 'admin-edit',
    templateUrl: 'app/admin/admin-edit-content.component.html',
    providers: [AdminService]
})

export class AdminEditContent {
    entries: any[] = [];
    response: string = '';
    resultCount: number = 0;

    constructor(private AdminService: AdminService) { }

    ngOnInit(): void {
        console.log('onInit of AdminEditContent');
        //query all availble entries
        this.queryEntries();
    }

    private queryEntries(){
        this.AdminService.queryEntries().subscribe(
            data => {
                if (data != null && data.adminEntryResponse != null) {
                    console.log("Data from queryEntries: " + JSON.stringify(data));
                    if(data.adminEntryResponse.success){
                        this.entries = data.adminEntries;
                        this.response = data.adminEntryResponse.message;
                        this.resultCount = data.adminEntries.length;
                    } else {
                        this.response = data.adminEntryResponse.message;
                    }                                                       
                }
            },
            error => {
                this.response = error;
                return false;
            },
            () => {
                console.log('Completed queryEntries request');
            }
        );
    }
}