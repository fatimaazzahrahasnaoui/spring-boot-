import { Component, OnInit } from '@angular/core';
import { SharedService } from '../../../shared/services/shared.service';
import { Book } from '../../model/book.model';

@Component({
  selector: 'app-view-book',
  standalone: false,
  
  templateUrl: './view-book.component.html',
  styleUrl: './view-book.component.css'
})
export class ViewBookComponent implements OnInit {
  
  // Storing the book details
  book: Book | null = null;  
  message: string = '';

  constructor(private sharedService: SharedService) {}

  ngOnInit(): void {
    this.loadData(); 
  }

  loadData(): void {
    this.sharedService.getSelectedBook().subscribe(
      { 
        next: (data) => { 
          this.book = data; 
        }, 

        error: (err) => {
          console.error('Error receiving book:', err);
          this.message = 'Error loading book details.';
        }
      }
    );
  }
}
