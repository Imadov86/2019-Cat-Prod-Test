import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {AuthenticationService} from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class CatalogueService {
public host:string="http://localhost:8087";
  constructor(private http: HttpClient,
              private authService:AuthenticationService) { }
  getAllCategories(){
    return this.http.get(this.host+"/categories");
  }
  getRessource(url){
    return this.http.get(url);
  }

  deleteRessource(url: any) {
    let header=new HttpHeaders({'authorization':'Bearer '+this.authService.jwt});
    return this.http.delete(url,{headers:header});
  }
  postRessource(url: any,data) {
    let header=new HttpHeaders({'authorization':'Bearer '+this.authService.jwt});
    return this.http.post(url,data ,{headers:header});
  }

  putRessource(url: any, data: any) {
    let header=new HttpHeaders({'authorization':'Bearer '+this.authService.jwt});
    return this.http.put(url,data ,{headers:header});
  }
  patchRessource(url: any, data: any) {
    let header=new HttpHeaders({'authorization':'Bearer '+this.authService.jwt});
    return this.http.patch(url,data ,{headers:header});
  }
}

