import { Component, OnInit } from '@angular/core';
import { SharedService } from '../../../shared/services/shared.service';
import { CategoryService } from '../../services/category.service';
import { Router } from '@angular/router';
import { Category } from '../../model/category.model';

@Component({
  selector: 'app-delete-category',
  standalone: false,
  templateUrl: './delete-category.component.html',
  styleUrl: './delete-category.component.css'
})
export class DeleteCategoryComponent implements OnInit {
  category: Category | null = null;
  message: string = '';

  constructor(
    private sharedService: SharedService,
    private categoryService: CategoryService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadData();
  }

  loadData(): void {
    this.sharedService.getSelectedCategory().subscribe({
      next: (data) => {
        this.category = data;
      },
      error: (err) => {
        console.error('Error loading category:', err);
        this.message = 'Error loading category details for deletion.';
      }
    });
  }

  deleteCategory(): void {
    if (this.category) {
      this.categoryService.deleteCategory(this.category.id).subscribe({
        next: () => {
          this.message = `Category '${this.category?.label}' was deleted successfully.`;
          this.router.navigate(['/categories']); // Redirect to the categories list
        },
        error: (err) => {
          console.error('Error deleting category:', err);
          this.message = 'Error deleting the category.';
        }
      });
    }
  }
}

