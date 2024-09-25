import { Injectable } from '@angular/core';
import { Puesto } from '../../models/modelPuesto/puesto.model';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PuestoService {

  private apiRoot = "http://localhost:8080/api/puesto";

  constructor(private http: HttpClient) { }

  getPuestos(){
    return this.http.get<Puesto[]>(this.apiRoot + '/consultar/');
  }

  postPuestos(nombrePuesto :string){
    return this.http.post<Puesto>(this.apiRoot + '/insertar/', nombrePuesto);
  }

  putPuestos(nombrePuesto :string, puesto: Puesto){
    return this.http.put<Puesto>(this.apiRoot + '/actualizar/' + nombrePuesto, puesto);
  }

  borradoLógicoPuesto(nombrePuesto :string){
    return this.http.put<Puesto>(this.apiRoot + '/activar/', nombrePuesto);
  }
}
