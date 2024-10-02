import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Ubicacion } from '../../models/modelUbicacion/ubicacion.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UbicacionService {

  private apiRoot = "http://localhost:8080/api/ubicacion";

  constructor(private http: HttpClient) { }

  getUbicaciones(): Observable<Ubicacion[]>{
    return this.http.get<Ubicacion[]>(this.apiRoot + '/consultar');
  }

  getUbicacionPorNombre(nombreUbicacion: string): Observable<Ubicacion>{
    return this.http.get<Ubicacion>(this.apiRoot + '/consultar/' + nombreUbicacion);
  }

  postUbicacion(ubicacion: Ubicacion): Observable<Object>{
    return this.http.post<Ubicacion>(this.apiRoot + '/insertar', ubicacion);
  }

  putUbicacion(nombreUbicacion: string, ubicacion: Ubicacion): Observable<Object>{
    return this.http.put<Ubicacion>(this.apiRoot + '/actualizar/' + nombreUbicacion, ubicacion);
  }

  deleteUbicacion(nombreUbicacion: string): Observable<Object>{
    return this.http.delete(this.apiRoot + '/desactivar/' + nombreUbicacion);
  }
  
}
