import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { CrearMatriculaPayload, MatriculaCurso } from '../models/matricula.model';

@Injectable({
    providedIn: 'root'
})
export class MatriculaService {
    private http = inject(HttpClient);
    private baseUrl = `${environment.apiUrl}/matriculas`;

    getMatriculas(): Observable<MatriculaCurso[]> {
        return this.http.get<MatriculaCurso[]>(this.baseUrl);
    }

    getMatriculasPorEstado(estado: string): Observable<MatriculaCurso[]> {
        return this.http.get<MatriculaCurso[]>(`${this.baseUrl}/estado/${estado}`);
    }

    crearMatricula(payload: CrearMatriculaPayload): Observable<MatriculaCurso> {
        return this.http.post<MatriculaCurso>(this.baseUrl, payload);
    }

    actualizarEstado(id: number, nuevoEstado: string): Observable<MatriculaCurso> {
        return this.http.patch<MatriculaCurso>(`${this.baseUrl}/${id}/estado?nuevoEstado=${nuevoEstado}`, {});
    }
}