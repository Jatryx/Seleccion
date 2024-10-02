import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Ofertas } from '../../models/modelOfertas/ofertas.model';

@Injectable({
  providedIn: 'root'
})
export class OfertasService {

  private apiRoot = "http://localhost:8080/api/ofertas";

  constructor(private http: HttpClient) { }

  getOfertas(){
    return this.http.get<Ofertas[]>(this.apiRoot + '/consultar');
  }

  getOfertaPorNombreCandidatoEIdRecruiting(nombreCandidato: string, idRecruiting: number){
    return this.http.get<Ofertas>(this.apiRoot + '/consultar/' + nombreCandidato + '/' + idRecruiting);
  }

  postOferta(oferta: any){
    return this.http.post<any>(this.apiRoot + '/insertar', oferta);
  }

  putOferta(nombreCandidato: string, idRecruiting: number, oferta: Ofertas){
    return this.http.put<Ofertas>(this.apiRoot + '/actualizar/' + nombreCandidato + '/' + idRecruiting, oferta);
  }

  deleteOferta(nombreCandidato: string, idRecruiting: number){
    return this.http.delete(this.apiRoot + '/desactivar/' + nombreCandidato + '/' + idRecruiting);
  }

}
