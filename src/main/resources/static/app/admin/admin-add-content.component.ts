import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';

import { AddEntryModel } from './addentrymodel';
import { AdminService } from '../services/admin-services';

@Component({
    selector: 'admin-add',
    templateUrl: 'app/admin/admin-add-content.component.html',
    providers: [AdminService]
})

export class AdminAddContent {
    addEntryResults: any[] = [];
    categories: string[] = ['bank', 'shop', 'bill', 'miscellaneous'];
    entryModel = new AddEntryModel('default', '', '', '', '', '');
    hasCategoryError: boolean = false;
    responseMsg: string = '';
    successOrDangerClass: string = '';
    success: boolean = false;

    constructor(private AdminService: AdminService) { }

    addEntry(addEntryForm: NgForm) {
        let password = addEntryForm.value.password;
        let confirmPassword = addEntryForm.value.passwordConfirm;
        if (password !== confirmPassword) {
            this.success = false;
            this.successOrDangerClass = 'danger';
            this.responseMsg = 'passwords do not match. Try again!'
            return false;
        }
        this.AdminService.addEntry(this.entryModel).subscribe(
            data => {
                if (data != null) {
                    if(data.success){
                        this.addEntryResults = data;
                        this.success = data.success;
                        this.successOrDangerClass = 'success';
                        this.responseMsg = data.message;
                        addEntryForm.reset();
                    } else {
                        this.addEntryResults = data;
                        this.success = data.success;
                        this.successOrDangerClass = 'danger';
                        this.responseMsg = data.message;
                        addEntryForm.reset();
                    }
                } else {
                    this.addEntryResults = data;
                    this.successOrDangerClass = 'danger';
                    this.responseMsg = 'data is null';
                }
            },
            error => {
                this.success = false;
                console.error('Error addEntry: ' + error);
                this.responseMsg = 'error saving entry.';
                this.successOrDangerClass = 'danger';
                return false;
            },
            () => {
                console.log('Completed addEntry request');
            }
        );
    }

    validateCategory(value: string) {
        if (value === 'default') {
            this.hasCategoryError = true;
        } else {
            this.hasCategoryError = false;
        }
    }

    onTagInputChange(value: string, addEntryForm: NgForm) {
        this.entryModel.tag = value;
    }

    onUserNameInputChange(value: string, addEntryForm: NgForm) {
        this.entryModel.username = value;
    }

    onPasswordInputChange(value: string, addEntryForm: NgForm) {
        this.entryModel.password = value;
    }

    onPasswordConfirmInputChange(value: string, addEntryForm: NgForm) {
        this.entryModel.confirmPassword = value;
    }

    onComentChange(value: string, addEntryForm: NgForm) {
        this.entryModel.comment = value;
    }
}