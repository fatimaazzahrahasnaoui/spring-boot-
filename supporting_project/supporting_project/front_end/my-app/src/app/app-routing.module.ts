import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateAuthorComponent } from './features/author/components/create-author/create-author.component';
import { FindAllAuthorsComponent } from './features/author/components/find-all-authors/find-all-authors.component';

const routes: Routes = [
  { path: 'author', loadChildren: () => import('./features/author/author.module').then(m => m.AuthorModule) },
  { path: 'book', loadChildren: () => import('./features/book/book.module').then(m => m.BookModule) },
  { path: 'category', loadChildren: () => import('./features/category/category.module').then(m => m.CategoryModule) }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
