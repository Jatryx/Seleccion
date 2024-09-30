import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Usuario } from '../../models/modelUsuario/usuario.model';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private apiRoot = "http://localhost:8080/api/usuarios";

  constructor(private http: HttpClient) { }

  getUsuarios(){
    return this.http.get<Usuario[]>(this.apiRoot + '/consultar');
  }

  getUsarioCodope(codope: string){
    return this.http.get<Usuario>(this.apiRoot + '/consultar/' + codope);
  }

  postUsuario(usuario: Usuario){
    return this.http.post<Usuario>(this.apiRoot + '/insertar', usuario);
  }

  putUsuario(codope: string, usuario: Usuario){
    return this.http.put<Usuario>(this.apiRoot + '/actualizar/' + codope, usuario);
  }

  deleteUsuario(codope: string){
    return this.http.delete(this.apiRoot + '/desactivar/' + codope);
  }
}
