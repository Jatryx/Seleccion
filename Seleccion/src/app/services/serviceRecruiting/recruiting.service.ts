import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Recruiting } from '../../models/modelRecruiting/recruiting.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RecruitingService {

  private apiRoot = "http://localhost:8080/api/recruitings";

  constructor(private http: HttpClient) { }

  getRecruitings(): Observable<Recruiting[]>{
    return this.http.get<Recruiting[]>(this.apiRoot + '/consultar');
  }

  getRecruitingIdrecruiting(idrecruiting: number): Observable<Recruiting>{
    return this.http.get<Recruiting>(this.apiRoot + '/consultar/' + idrecruiting);
  }

  postRecruiting(recruiting: Recruiting): Observable<Object>{
    return this.http.post<Recruiting>(this.apiRoot + '/insertar', recruiting);
  }

  putRecruiting(idrecruiting: number, recruiting: Recruiting): Observable<Object>{
    return this.http.put<Recruiting>(this.apiRoot + '/actualizar/' + idrecruiting, recruiting);
  }

  deleteRecruiting(idrecruiting: number): Observable<Object>{
    return this.http.delete(this.apiRoot + '/desactivar/' + idrecruiting);
  }
}
