import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SharedService } from '../../../shared/services/shared.service';
import { BookService } from '../../services/book.service';
import { Book } from '../../model/book.model';

@Component({
  selector: 'app-update-book',
  standalone: false,
  
  templateUrl: './update-book.component.html',
  styleUrl: './update-book.component.css'
})
export class UpdateBookComponent implements OnInit {

  selectedBook: Book | null = null;
  form: FormGroup;
  message: string = '';

  constructor(
    private sharedService: SharedService,
    private bookService: BookService,
    private fb: FormBuilder
  ) {
    this.form = this.fb.group({
      title: ['', Validators.required],
      publishDate: ['', Validators.required],
      author: ['', Validators.required],
      category: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.loadData();
  }

  // Load the selected book from the shared service
  loadData(): void {
    this.sharedService.getSelectedBook().subscribe({
      next: (data) => {
        this.selectedBook = data;

        // Initialize the form with the current book data
        if (this.selectedBook) {
          this.form.patchValue({
            title: this.selectedBook.title,
            publishDate: this.selectedBook.publishDate,
            author: this.selectedBook.author?.id, // Assuming the author is selected by ID
            category: this.selectedBook.category?.id, // Assuming the category is selected by ID
          });
        }
      }
    });
  }

  onUpdate(): void {
    // Handle invalid form submission
    if (this.form.invalid) {
      return;
    }

    // Updated book object
    const updatedBook: Book = {
      ...this.selectedBook, // Retain the original book's ID and other properties
      ...this.form.value, // Update with new form values
    };

    console.log('Updated Book:', updatedBook);

    this.bookService.updateBook(updatedBook).subscribe({
      next: () => {
        this.message = `Book "${updatedBook.title}" was updated successfully.`;
      },
      error: (err) => {
        console.error('Error updating book:', err);
        this.message = 'Error updating the book.';
      }
    });
  }
}
