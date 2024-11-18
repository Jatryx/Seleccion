import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Proveedor } from '../../models/modelProveedor/proveedor.model';

@Injectable({
  providedIn: 'root'
})
export class ProveedorService {

  private apiRoot = "http://localhost:8080/api/proveedores";

  constructor(private http: HttpClient) { }

  // Obtener todos los proveedores
  getProveedores(): Observable<Proveedor[]> {
    return this.http.get<Proveedor[]>(this.apiRoot + '/consultar');
  }

  // Obtener un proveedor por su nombre
  getProveedorPorNombre(nombreProveedor: string): Observable<Proveedor> {
    return this.http.get<Proveedor>(`${this.apiRoot}/consultar/${nombreProveedor}`);
  }

  // Insertar un nuevo proveedor
  postProveedor(proveedor: Proveedor): Observable<Object> {
    return this.http.post(this.apiRoot + '/insertar', proveedor);
  }

  // Actualizar un proveedor por su nombre
  putProveedor(nombreProveedor: string, proveedor: Proveedor): Observable<Object> {
    return this.http.put(`${this.apiRoot}/actualizar/${nombreProveedor}`, proveedor);
  }

  // Desactivar un proveedor por su nombre
  deleteProveedor(nombreProveedor: string): Observable<Object> {
    return this.http.put(`${this.apiRoot}/desactivar/${nombreProveedor}`, {});
  }
}
