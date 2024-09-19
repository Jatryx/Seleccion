import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Candidatos } from '../../models/modelCandidato/candidatos.model';

@Injectable({
  providedIn: 'root'
})
export class CandidatosService {

  private apiRoot = "http://localhost:8080/api/candidatos";

  constructor(private http: HttpClient) { }

  getCandidatos(){
    return this.http.get<Candidatos[]>(this.apiRoot);
  }

  postCandidato(candidato: any){
    return this.http.post(this.apiRoot, candidato);
  }

  putCandidato(idcandidato: number){
    return this.http.put(this.apiRoot + '/' + idcandidato, idcandidato);
  }

  deleteCandidato(idcandidato: number){
    return this.http.delete(this.apiRoot + '/' + idcandidato);
  }
}
