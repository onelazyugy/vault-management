import { Component, OnInit } from '@angular/core';

import { AdminService } from '../services/admin-services';

@Component({
    selector: 'admin-edit',
    templateUrl: 'app/admin/admin-edit-content.component.html',
    providers: [AdminService]
})

export class AdminEditContent {
    entries: any[] = [];
    constructor(private AdminService: AdminService) { }

    ngOnInit(): void {
        console.log('onInit of AdminEditContent');
        //query all availble entries
        this.queryEntries();
    }

    private queryEntries(){
        this.AdminService.queryEntries().subscribe(
            data => {
                if (data) {
                    console.log("Data from queryEntries: " + JSON.stringify(data));
                    this.entries = data;
                    console.log("entries: " + this.entries);
                } else {
                    
                }
            },
            error => {
                return false;
            },
            () => {
                console.log('Completed queryEntries request');
            }
        );
    }
}