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
    return this.http.get<Ofertas[]>(this.apiRoot);
  }

  getIdoferta(idoferta: number){
    return this.http.get<Ofertas>(this.apiRoot + '/' + idoferta);
  }

  postOferta(oferta: Ofertas){
    return this.http.post<Ofertas>(this.apiRoot, oferta);
  }

  putOferta(idoferta: number, oferta: Ofertas){
    return this.http.put<Ofertas>(this.apiRoot + '/' + idoferta, oferta);
  }

  deleteOferta(idoferta: number){
    return this.http.delete(this.apiRoot + '/' + idoferta);
  }

}
