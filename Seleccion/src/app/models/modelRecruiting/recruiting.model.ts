import { Empresa } from "../modelEmpresa/empresa.model";

export interface Recruiting {
    map(arg0: (proyecto: any) => any): string[];
    idRecruiting: number,
    nombreProyecto: string,
    URL: string,
    activo: boolean,
    empresa: Empresa,
}