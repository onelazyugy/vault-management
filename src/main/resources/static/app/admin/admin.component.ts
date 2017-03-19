import { Component, OnInit } from '@angular/core';

@Component({
    templateUrl: 'app/admin/admin.component.html',
    providers: []
})

export class AdminComponent implements OnInit{
    adminMenuTitle: string = 'Admin Menu';
    adminContentTitle: string = '';
    defaultContentTitle: string = 'Add';
    add: string = 'Add';
    edit: string = 'Edit';

    constructor(){
        console.log('AdminComponent constructor..');
    }

    ngOnInit(): void {
        console.log('onInit of AdminCompnent');
        this.adminContentTitle = this.defaultContentTitle;
    }

    public adminMenuClick(option:string): void {
        this.adminContentTitle = option;
    }
}