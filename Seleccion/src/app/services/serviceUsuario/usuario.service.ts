import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Usuario } from '../../models/modelUsuario/usuario.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private apiRoot = "http://localhost:8080/api/usuarios";

  constructor(private http: HttpClient) { }

  getUsuarios(): Observable<Usuario[]>{
    return this.http.get<Usuario[]>(this.apiRoot + '/consultar');
  }

  getUsarioCodope(codope: string): Observable<Usuario>{
    return this.http.get<Usuario>(this.apiRoot + '/consultar/' + codope);
  }

  postUsuario(usuario: Usuario): Observable<Object>{
    return this.http.post<Usuario>(this.apiRoot + '/insertar', usuario);
  }

  putUsuario(codope: string, usuario: Usuario): Observable<Object>{
    return this.http.put<Usuario>(this.apiRoot + '/actualizar/' + codope, usuario);
  }

  deleteUsuario(codope: string): Observable<Object>{
    return this.http.delete(this.apiRoot + '/desactivar/' + codope);
  }
}
