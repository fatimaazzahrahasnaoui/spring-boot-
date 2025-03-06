import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthorRoutingModule } from './author-routing.module';
import { CreateAuthorComponent } from './components/create-author/create-author.component';
import { ReactiveFormsModule } from '@angular/forms';
import { AuthorService } from './services/author.service';
import { provideHttpClient, withFetch } from '@angular/common/http';
import { FindAllAuthorsComponent } from './components/find-all-authors/find-all-authors.component';
import { ViewAuthorComponent } from './components/view-author/view-author.component';
import { UpdateAuthorComponent } from './components/update-author/update-author.component';
import { AbstractAuthorServices } from './services/abstract-author-services';
import { DeleteAuthorComponent } from './components/delete_author/delete-author.component';


@NgModule({
  declarations: [
    CreateAuthorComponent,
    FindAllAuthorsComponent,
    ViewAuthorComponent,
    UpdateAuthorComponent,
    DeleteAuthorComponent
   
  ],


  imports: [
    CommonModule,
    AuthorRoutingModule, 
    ReactiveFormsModule
  ], 

  providers : [
    {provide : AbstractAuthorServices , useClass : AuthorService},
    provideHttpClient(withFetch()) 
  ]

})
export class AuthorModule { }
