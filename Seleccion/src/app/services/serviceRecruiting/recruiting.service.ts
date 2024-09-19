import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Recruiting } from '../../models/modelRecruiting/recruiting.model';

@Injectable({
  providedIn: 'root'
})
export class RecruitingService {

  private apiRoot = "http://localhost:8080/api/recruiting";

  constructor(private http: HttpClient) { }

  getRecruitings(){
    return this.http.get<Recruiting[]>(this.apiRoot);
  }

  getRecruitingIdrecruiting(idrecruiting: number){
    return this.http.get<Recruiting>(this.apiRoot + '/' + idrecruiting);
  }

  postRecruiting(recruiting: Recruiting){
    return this.http.post<Recruiting>(this.apiRoot, recruiting);
  }

  putRecruiting(idrecruiting: number, recruiting: Recruiting){
    return this.http.put<Recruiting>(this.apiRoot + '/' + idrecruiting, recruiting);
  }

  deleteRecruiting(idrecruiting: number){
    return this.http.delete(this.apiRoot + '/' + idrecruiting);
  }
}
