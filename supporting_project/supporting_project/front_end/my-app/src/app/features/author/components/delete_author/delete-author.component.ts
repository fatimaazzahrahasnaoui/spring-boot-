import { Component, OnInit } from '@angular/core';
import { Author } from '../../model/author';
import { SharedService } from '../../../shared/services/shared.service';
import { AuthorService } from '../../services/author.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-delete-author',
  standalone: false,
  templateUrl: './delete-author.component.html',
  styleUrl: './delete-author.component.css'
})
export class DeleteAuthorComponent implements OnInit {

  author: Author | null = null;
  message: string = '';

  constructor(
    private sharedService: SharedService, 
    private authorService: AuthorService, 
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadData();
  }

  loadData(): void {
    this.sharedService.getSelectedAuthor().subscribe({
      next: (data) => {
        this.author = data;
      },
      error: (err) => {
        console.error('Error loading author:', err);
        this.message = 'Error loading author details for deletion.';
      }
    });
  }

  deleteAuthor(): void {
    if (this.author) {
      this.authorService.deleteAuthor(this.author.id).subscribe({
        next: () => {
          this.message = `Author '${this.author?.name}' was deleted successfully.`;
          this.router.navigate(['/authors']);  // Redirect to the authors list
        },
        error: (err) => {
          console.error('Error deleting author:', err);
          this.message = 'Error deleting the author.';
        }
      });
    }
  }
}
