import { Candidatos } from "../modelCandidato/candidatos.model";
import { Empresa } from "../modelEmpresa/empresa.model";
import { Estado } from "../modelEstado/estado.model";
import { Puesto } from "../modelPuesto/puesto.model";
import { Recruiting } from "../modelRecruiting/recruiting.model";
import { Ubicacion } from "../modelUbicacion/ubicacion.model";
import { Usuario } from "../modelUsuario/usuario.model";

export interface Ofertas {
    idOferta: number,
    nombreCandidato: string,
    usuario:Usuario,
    recruiting:Recruiting,
    ubicacion:Ubicacion,
    puesto:Puesto,
    tecnologias: string,
    experiencia: number,
    salario: number,
    estado: Estado,
    fechaActualizacion: Date,
    observaciones: string,
    historicoCambiosEstado: string,
    candidato: Candidatos
    activo: boolean,
}
