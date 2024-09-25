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
    return this.http.get<Ubicacion[]>(this.apiRoot + '/consultar');
  }

  getUbicacionPorNombre(nombreUbicacion: string){
    return this.http.get<Ubicacion>(this.apiRoot + '/consultar/' + nombreUbicacion);
  }

  postUbicacion(ubicacion: Ubicacion){
    return this.http.post<Ubicacion>(this.apiRoot + '/insertar', ubicacion);
  }

  putUbicacion(nombreUbicacion: string, ubicacion: Ubicacion){
    return this.http.put<Ubicacion>(this.apiRoot + '/actualizar/' + nombreUbicacion, ubicacion);
  }

  deleteUbicacion(nombreUbicacion: string){
    return this.http.delete(this.apiRoot + '/desactivar/' + nombreUbicacion);
  }
  
}
