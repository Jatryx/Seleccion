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
    return this.http.get<Recruiting[]>(this.apiRoot + '/consultar');
  }

  getRecruitingIdrecruiting(idrecruiting: number){
    return this.http.get<Recruiting>(this.apiRoot + '/consultar/' + idrecruiting);
  }

  postRecruiting(recruiting: Recruiting){
    return this.http.post<Recruiting>(this.apiRoot + '/insertar', recruiting);
  }

  putRecruiting(idrecruiting: number, recruiting: Recruiting){
    return this.http.put<Recruiting>(this.apiRoot + '/actualizar/' + idrecruiting, recruiting);
  }

  deleteRecruiting(idrecruiting: number){
    return this.http.delete(this.apiRoot + '/desactivar/' + idrecruiting);
  }
}
