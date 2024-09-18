import { Empresa } from "../modelEmpresa/empresa.model";

export interface Recruiting {
    idRecruiting: number,
    nombreProyecto: string,
    activo: boolean,
    empresa: Empresa,
}