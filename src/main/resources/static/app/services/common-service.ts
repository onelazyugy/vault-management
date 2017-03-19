import {Location} from '@angular/common';

export class CommonService{
    private _location: Location;

    constructor(){}

    public buildRequestURL() : string{
        let hostname = location.hostname;
        let port = location.port;
        let protocol = location.protocol;
        if(port != ""){
            return location.protocol + "//" + location.hostname + ":" + location.port;
        } else {
            return location.protocol + "//" + location.hostname;
        }
    }
}