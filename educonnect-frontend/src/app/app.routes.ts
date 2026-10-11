import { Routes } from '@angular/router';

import { MatriculaListComponent } from './components/matricula-list/matricula-list';
import { MatriculaFormComponent } from './components/matricula-form/matricula-form';

export const routes: Routes = [
  { path: 'matriculas', component: MatriculaListComponent },
  { path: 'matriculas/nueva', component: MatriculaFormComponent },
  { path: '', redirectTo: 'matriculas', pathMatch: 'full' },
  { path: '**', redirectTo: 'matriculas' }
];