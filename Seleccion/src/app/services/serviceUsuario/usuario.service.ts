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
    return this.http.get<Usuario[]>(this.apiRoot);
  }

  getUsarioCodope(codope: string){
    return this.http.get<Usuario>(this.apiRoot + '/' + codope);
  }

  postUsuario(usuario: Usuario){
    return this.http.post<Usuario>(this.apiRoot, usuario);
  }

  putUsuario(codope: string, usuario: Usuario){
    return this.http.put<Usuario>(this.apiRoot + '/' + codope, usuario);
  }

  deleteUsuario(codope: string){
    return this.http.delete(this.apiRoot + '/' + codope);
  }
}
