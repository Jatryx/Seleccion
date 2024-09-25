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
  getCandidatoPorNombre(nombreCandidato: string){
      return this.http.get<Candidatos>(this.apiRoot + '/consultar/' + nombreCandidato);
    }
  postCandidato(candidato: any){
    return this.http.post(this.apiRoot + '/insertar', candidato);
  }

  putCandidato(nombreCandidato: string, candidato: Candidatos){
    return this.http.put(this.apiRoot + '/actualizar/' + nombreCandidato, candidato);
  }

  deleteCandidato(nombreCandidato: string){
    return this.http.delete(this.apiRoot + '/desactivar/' + nombreCandidato);
  }
}
