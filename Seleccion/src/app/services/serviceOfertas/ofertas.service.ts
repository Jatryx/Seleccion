import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ofertas } from '../../models/modelOfertas/ofertas.model';

@Injectable({
  providedIn: 'root'
})
export class OfertasService {

  private apiRoot = "http://localhost:8080/api/ofertas";

  constructor(private http: HttpClient) { }

  getOfertas(): Observable<Ofertas[]>{
    return this.http.get<Ofertas[]>(this.apiRoot + '/consultar');
  }

  getOfertaPorNombreCandidatoEIdRecruiting(nombreCandidato: string, idRecruiting: number): Observable<Ofertas>{
    return this.http.get<Ofertas>(this.apiRoot + '/consultar/' + nombreCandidato + '/' + idRecruiting);
  }

  postOferta(oferta: Ofertas): Observable<Object>{
    return this.http.post<Ofertas>(this.apiRoot + '/insertar', oferta);
  }

  putOferta(nombreCandidato: string, idRecruiting: number, oferta: Ofertas): Observable<Object>{
    return this.http.put<Ofertas>(this.apiRoot + '/actualizar/' + nombreCandidato + '/' + idRecruiting, oferta);
  }

  deleteOferta(nombreCandidato: string, idRecruiting: number): Observable<Object>{
    return this.http.delete(this.apiRoot + '/desactivar/' + nombreCandidato + '/' + idRecruiting);
  }

}
