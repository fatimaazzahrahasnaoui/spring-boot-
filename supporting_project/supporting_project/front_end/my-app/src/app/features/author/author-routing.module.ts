import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FindAllAuthorsComponent } from './components/find-all-authors/find-all-authors.component';
import { ViewAuthorComponent } from './components/view-author/view-author.component';
import { UpdateAuthorComponent } from './components/update-author/update-author.component';
import { DeleteAuthorComponent } from './components/delete_author/delete-author.component';

const routes: Routes = [

  { path: '', redirectTo: '/authors', pathMatch: 'full' },
  { path: 'authors', component: FindAllAuthorsComponent },
  { path: 'authors/view', component: ViewAuthorComponent },
  { path: 'authors/update', component: UpdateAuthorComponent },
  { path: 'authors/delete' , component : DeleteAuthorComponent}


]; 

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthorRoutingModule { }
