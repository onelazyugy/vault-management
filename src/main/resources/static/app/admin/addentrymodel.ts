export class AddEntryModel{
    constructor(public category:string, public tag:string,
                public password:string, public confirmPassword:string,
                public username:string, public comment:string, public masterPassword:string){}
}