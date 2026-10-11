import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { MatriculaService } from '../../services/matricula.service';
import { CrearMatriculaPayload } from '../../models/matricula.model';

@Component({
  selector: 'app-matricula-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './matricula-form.html',
  styleUrls: ['./matricula-form.css']
})
export class MatriculaFormComponent {
  
  private matriculaService = inject(MatriculaService);
  private router = inject(Router);

  // Objeto inicializado vacío para capturar los datos del formulario
  nuevaMatricula: CrearMatriculaPayload = {
    estudianteId: 0,
    profesorId: 0,
    nombreCurso: '',
    creditos: 0,
    periodo: '',
    montoArancel: 0
  };

  guardarMatricula(): void {
    this.matriculaService.crearMatricula(this.nuevaMatricula).subscribe({
      next: (respuesta) => {
        alert(`Matrícula creada exitosamente con código: ${respuesta.codigoMatricula}`);
        // Redireccionar a la tabla principal al guardar con éxito
        this.router.navigate(['/matriculas']);
      },
      error: (err) => {
        alert('Error al crear la matrícula. Revisa la consola.');
        console.error(err);
      }
    });
  }
}