import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Ubicacion } from '../../models/modelUbicacion/ubicacion.model';

@Injectable({
  providedIn: 'root'
})
export class UbicacionService {

  private apiRoot = "http://localhost:8080/api/ubicacion";

  constructor(private http: HttpClient) { }

  getUbicaciones(){
    return this.http.get<Ubicacion[]>(this.apiRoot);
  }

  getUbicacionIdubicacion(idubicacion: number){
    return this.http.get<Ubicacion>(this.apiRoot + '/' + idubicacion);
  }

  postUbicacion(ubicacion: Ubicacion){
    return this.http.post<Ubicacion>(this.apiRoot, ubicacion);
  }

  putUbicacion(idubicacion: number, ubicacion: Ubicacion){
    return this.http.put<Ubicacion>(this.apiRoot + '/' + idubicacion, ubicacion);
  }

  deleteUbicacion(idubicacion: number){
    return this.http.delete(this.apiRoot + '/' + idubicacion);
  }
  
}
