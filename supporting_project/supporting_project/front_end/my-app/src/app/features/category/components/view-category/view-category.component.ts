import { Component, OnInit } from '@angular/core';
import { Category } from '../../model/category';
import { SharedService } from '../../../shared/services/shared.service';

@Component({
  selector: 'app-view-category',
  standalone: false,
  templateUrl: './view-category.component.html',
  styleUrl: './view-category.component.css'
})
export class ViewCategoryComponent implements OnInit {
  
  // Storing the category
  category: Category | null = null;  
  message: string = '';

  constructor(private sharedService: SharedService) {}

  ngOnInit(): void {
    this.loadData(); 
  }

  loadData(): void {
    this.sharedService.getSelectedCategory().subscribe(
      { 
        next: (data) => { 
          this.category = data; 
        }, 
        error: (err) => {
          console.error('Error receiving category:', err);
          this.message = 'Error loading category details.';
        }
      }
    );
  }
}
