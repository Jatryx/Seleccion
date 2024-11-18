import { Candidatos } from "../modelCandidato/candidatos.model";
import { Estado } from "../modelEstado/estado.model";
import { Proveedor } from "../modelProveedor/proveedor.model";
import { Puesto } from "../modelPuesto/puesto.model";
import { Recruiting } from "../modelRecruiting/recruiting.model";
import { Ubicacion } from "../modelUbicacion/ubicacion.model";
import { Usuario } from "../modelUsuario/usuario.model";

export interface Ofertas {
    idOferta: number,
    usuario:Usuario,
    recruiting:Recruiting,
    ubicacion:Ubicacion,
    puesto:Puesto,
    tecnologias: string,
    experiencia: number,
    salario: number,
    tarifa: number,
    rentabilidadCliente: number,
    rentabilidadClienteIncorpor: number,
    estado: Estado,
    fechaActualizacion: Date,
    proveedor: Proveedor,
    observaciones: string,
    historicoCambioEstados: string,
    candidato: Candidatos,
    activo: boolean,
}
