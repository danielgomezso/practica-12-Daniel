import { Component, inject, OnInit, signal, computed } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatriculaService } from '../../services/matricula.service';
import { MatriculaCurso } from '../../models/matricula.model';

@Component({
  selector: 'app-matricula-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './matricula-list.html',
  styleUrls: ['./matricula-list.css']
})
export class MatriculaListComponent implements OnInit {
  
  private matriculaService = inject(MatriculaService);

  // Signals para estado reactivo
  matriculas = signal<MatriculaCurso[]>([]);
  filtroEstado = signal<string>('TODAS');

  // Propiedad computada que reacciona automáticamente
  matriculasFiltradas = computed(() => {
    const estado = this.filtroEstado();
    const lista = this.matriculas();
    
    if (estado === 'TODAS') {
      return lista;
    }
    return lista.filter(m => m.estado === estado);
  });

  ngOnInit(): void {
    this.cargarMatriculas();
  }

  cargarMatriculas(): void {
    this.matriculaService.getMatriculas().subscribe({
      next: (datos) => {
        this.matriculas.set(datos);
      },
      error: (err) => console.error('Error al cargar las matrículas:', err)
    });
  }

  cambiarFiltro(nuevoEstado: string): void {
    this.filtroEstado.set(nuevoEstado);
  }
}