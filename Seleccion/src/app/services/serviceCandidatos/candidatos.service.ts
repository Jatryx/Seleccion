import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Candidatos } from '../../models/modelCandidato/candidatos.model';

@Injectable({
  providedIn: 'root'
})
export class CandidatosService {

  private apiRoot = "http://localhost:8080/api/candidatos";

  constructor(private http: HttpClient) { }

  getCandidatos(): Observable<Candidatos[]>{
    return this.http.get<Candidatos[]>(this.apiRoot);
  }
  getCandidatoPorNombre(nombreCandidato: string): Observable<Candidatos>{
      return this.http.get<Candidatos>(this.apiRoot + '/consultar/' + nombreCandidato);
    }
  postCandidato(candidato: Candidatos): Observable<Object>{
    return this.http.post(this.apiRoot + '/insertar', candidato);
  }

  putCandidato(nombreCandidato: string, candidato: Candidatos): Observable<Object>{
    return this.http.put(this.apiRoot + '/actualizar/' + nombreCandidato, candidato);
  }

  deleteCandidato(nombreCandidato: string): Observable<Object>{
    return this.http.delete(this.apiRoot + '/desactivar/' + nombreCandidato);
  }
}
