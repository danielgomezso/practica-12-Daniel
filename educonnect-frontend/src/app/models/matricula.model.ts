export interface MatriculaCurso {
    id?: number;
    codigoMatricula: string;
    nombreEstudiante: string;
    carnetEstudiante: string;
    nombreProfesor: string;
    nombreCurso: string;
    creditos: number;
    periodo: string;
    estado: 'ACTIVA' | 'COMPLETADA' | 'CANCELADA';
    montoArancel: number;
    fechaMatricula: string;
}

export interface CrearMatriculaPayload {
    estudianteId: number;
    profesorId: number;
    nombreCurso: string;
    creditos: number;
    periodo: string;
    montoArancel: number;
}