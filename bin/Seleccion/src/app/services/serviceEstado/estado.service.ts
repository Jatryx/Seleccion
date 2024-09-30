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
    return this.http.get<Estado[]>(this.apiRoot + '/consultar');
  }

  postEstado(estado: any){
    return this.http.post(this.apiRoot + '/insertar', estado);
  }

  putEstado(nombreEstado: string, estado: Estado){
    return this.http.put(this.apiRoot + '/actualizar/' + nombreEstado, estado);
  }

  deleteEstado(nombreEstado: string){
    return this.http.delete(this.apiRoot + '/desactivar/' + nombreEstado);
  }

}
