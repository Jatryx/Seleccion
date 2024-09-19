import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Empresa } from '../../models/modelEmpresa/empresa.model';

@Injectable({
  providedIn: 'root'
})
export class EmpresaService {

  private apiRoot = "http://localhost:8080/api/empresa";

  constructor(private http: HttpClient) { }

  getEmpresas(){
    return this.http.get<Empresa[]>(this.apiRoot);
  }

  postEmpresa(empresa: any){
    return this.http.post(this.apiRoot, empresa);
  }

  putEmpresa(idempresa: number){
    return this.http.put(this.apiRoot + '/' + idempresa, idempresa);
  }

  deleteEmpresa(idempresa: number){
    return this.http.delete(this.apiRoot + '/' + idempresa);
  }
}
