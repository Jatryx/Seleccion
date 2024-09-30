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
    return this.http.get<Empresa[]>(this.apiRoot + '/consultar');
  }
  
  getEmpresaPorNombre(nombreEmpresa: string){
      return this.http.get<Empresa>(this.apiRoot + '/consultar/' + nombreEmpresa);
    }

  postEmpresa(empresa: any){
    return this.http.post(this.apiRoot + '/insertar', empresa);
  }

  putEmpresa(nombreEmpresa: string, empresa: Empresa){
    return this.http.put(this.apiRoot + '/actualizar/' + nombreEmpresa, empresa);
  }

  deleteEmpresa(nombreEmpresa: string){
    return this.http.delete(this.apiRoot + '/desactivar/' + nombreEmpresa);
  }
}
