import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FindAllBooksComponent } from './components/find-all-books/find-all-books.component';
import { ViewBookComponent } from './components/view-book/view-book.component';
import { UpdateBookComponent } from './components/update-book/update-book.component';
import { DeleteBookComponent } from './components/delete-book/delete-book.component';

const routes: Routes = [
  { path: '', redirectTo: '/books', pathMatch: 'full' },
  { path: 'books', component: FindAllBooksComponent },
  { path: 'books/view', component: ViewBookComponent },
  { path: 'books/update', component: UpdateBookComponent },
  { path: 'books/delete' , component : DeleteBookComponent}
  
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class BookRoutingModule {}
