import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Estado } from '../../models/modelEstado/estado.model';

@Injectable({
  providedIn: 'root'
})
export class EstadoService {

  private apiRoot = "http://localhost:8080/api/estado";

  constructor(private http: HttpClient) { }

  getEstados(): Observable<Estado[]>{
    return this.http.get<Estado[]>(this.apiRoot + '/consultar');
  }

  postEstado(estado: Estado): Observable<Object>{
    return this.http.post(this.apiRoot + '/insertar', estado);
  }

  putEstado(nombreEstado: string, estado: Estado): Observable<Object>{
    return this.http.put(this.apiRoot + '/actualizar/' + nombreEstado, estado);
  }

  deleteEstado(nombreEstado: string): Observable<Object>{
    return this.http.delete(this.apiRoot + '/desactivar/' + nombreEstado);
  }

}
