import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BookService } from '../../services/book.service';
import { Book } from '../../model/book.model';


@Component({
  selector: 'app-create-book',
  standalone: false,
  
  templateUrl: './create-book.component.html',
  styleUrl: './create-book.component.css'
})
export class CreateBookComponent {

  bookForm: FormGroup;
  message: string = '';

  constructor(private bookService: BookService, private fb: FormBuilder) {
    // Define the form structure
    this.bookForm = this.fb.group({
      title: ['Enter book title', Validators.required],
      publishDate: ['', Validators.required],
      author: ['', Validators.required], // Assuming you will use a dropdown or input for selecting the author
      category: ['', Validators.required] // Similarly for category
    });
  }

  // Method triggered upon form submission
  onSubmit(): void {
    if (this.bookForm.invalid) {
      return;
    }

    // Create the book object from the form values
    const book: Book = this.bookForm.value;

    // Call the book saving service
    this.createBook(book);
  }

  public createBook(book: Book): void {
    this.bookService.createBook(book).subscribe(
      {
        next: (data) => {
          this.message = `Book "${data.title}" was saved successfully`;
          this.bookForm.reset();
        },
        error: (err) => {
          console.error(`Error creating the book: ${err}`);
          this.message = "Error creating the book.";
        }
      }
    );
  }
}
