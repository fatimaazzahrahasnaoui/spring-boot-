import { Component, OnInit } from '@angular/core';
import { SharedService } from '../../../shared/services/shared.service';
import { Router } from '@angular/router';
import { AbstractBookServices } from '../../services/abstract-book-services.service';
import { Book } from '../../model/book.model';

@Component({
  selector: 'app-find-all-books',
  standalone: false,
  templateUrl: './find-all-books.component.html',
  styleUrl: './find-all-books.component.css'
})
export class FindAllBooksComponent implements OnInit {

  books: Book[] = [];
  loading: boolean = true;
  message: string = '';

  constructor(private bookService: AbstractBookServices, private sharedService: SharedService, private router: Router) { }

  ngOnInit(): void {
    this.loadData();
  }

  loadData() {
    this.bookService.findAllBooks().subscribe(
      {
        next: (data) => {
          this.books = data;
          this.loading = false;
        },
        error: (err) => {
          this.message = 'Error loading data from the server';
          this.loading = false;
        }
      }
    );
  }

  navigateTo(action: 'view' | 'update' | 'delete', book: Book): void {
    // Pass the selected book to the shared service
    this.sharedService.setSelectedBook(book);

    // Navigate to the respective component
    const routes = {
      view: '/books/view',
      update: '/books/update',
      delete: '/books/delete',
    };

    const route = routes[action];
    if (route) {
      this.router.navigate([route]);
    }
  }
}
