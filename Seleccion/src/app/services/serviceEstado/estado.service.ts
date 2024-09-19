import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Estado } from '../../models/modelEstado/estado.model';

@Injectable({
  providedIn: 'root'
})
export class EstadoService {

  private apiRoot = "http://localhost:8080/api/estado";

  constructor(private http: HttpClient) { }

  getEstados(){
    return this.http.get<Estado[]>(this.apiRoot);
  }

  postEstado(estado: any){
    return this.http.post(this.apiRoot, estado);
  }

  putEstado(idestado: number){
    return this.http.put(this.apiRoot + '/' + idestado, idestado);
  }

  deleteEstado(idestado: number){
    return this.http.delete(this.apiRoot + '/' + idestado);
  }

}
