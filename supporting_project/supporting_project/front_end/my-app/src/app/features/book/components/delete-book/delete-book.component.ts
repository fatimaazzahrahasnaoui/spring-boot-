import { Component, OnInit } from '@angular/core';
import { SharedService } from '../../../shared/services/shared.service';
import { BookService } from '../../services/book.service';
import { Router } from '@angular/router';
import { Book } from '../../model/book.model';

@Component({
  selector: 'app-delete-book',
  standalone: false,
  templateUrl: './delete-book.component.html',
  styleUrl: './delete-book.component.css'
})
export class DeleteBookComponent implements OnInit {

  book: Book | null = null;
  message: string = '';

  constructor(
    private sharedService: SharedService, 
    private bookService: BookService, 
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadData(); 
  }

  loadData(): void {
    this.sharedService.getSelectedBook().subscribe({
      next: (data) => {
        this.book = data;
      },
      error: (err) => {
        console.error('Error loading book:', err);
        this.message = 'Error loading book details for deletion.';
      }
    });
  }

  deleteBook(): void {
    if (this.book) {
      this.bookService.deleteBook(this.book.id).subscribe({
        next: () => {
          this.message = `Book '${this.book?.title}' was deleted successfully.`;
          this.router.navigate(['/books']);  // Redirect to the books list
        },
        error: (err) => {
          console.error('Error deleting book:', err);
          this.message = 'Error deleting the book.';
        }
      });
    }
  }
}
