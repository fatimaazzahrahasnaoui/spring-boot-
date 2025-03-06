import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BookRoutingModule } from './book-routing.module';
import { CreateBookComponent } from './components/create-book/create-book.component';
import { ReactiveFormsModule } from '@angular/forms';
import { BookService } from './services/book.service';
import { provideHttpClient, withFetch } from '@angular/common/http';
import { FindAllBooksComponent } from './components/find-all-books/find-all-books.component';
import { ViewBookComponent } from './components/view-book/view-book.component';
import { UpdateBookComponent } from './components/update-book/update-book.component';
import { AbstractBookServices } from './services/abstract-book-services.service';
import { DeleteBookComponent } from './components/delete-book/delete-book.component';

@NgModule({
  declarations: [
    CreateBookComponent,
    FindAllBooksComponent,
    ViewBookComponent,
    UpdateBookComponent,
    DeleteBookComponent
  ],
  imports: [
    CommonModule,
    BookRoutingModule,
    ReactiveFormsModule,
  ],
  providers: [
    { provide: AbstractBookServices, useClass: BookService },
    provideHttpClient(withFetch()),
  ],
})
export class BookModule {}
