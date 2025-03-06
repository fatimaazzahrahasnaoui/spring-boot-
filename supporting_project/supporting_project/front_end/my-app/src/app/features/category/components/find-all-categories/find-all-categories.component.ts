import { Component, OnInit } from '@angular/core';
import { SharedService } from '../../../shared/services/shared.service';
import { Router } from '@angular/router';
import { Category } from '../../model/category.model';
import { AbstractCategoryServices } from '../../services/abstract-category-services.service';

@Component({
  selector: 'app-find-all-categories',
  standalone: false,
  templateUrl: './find-all-categories.component.html',
  styleUrl: './find-all-categories.component.css'
})
export class FindAllCategoriesComponent implements OnInit {

  categories: Category[] = [];
  loading: boolean = true;
  message: string = '';

  constructor(
    private categoryService: AbstractCategoryServices,
    private sharedService: SharedService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadData();
  }

  loadData(): void {
    this.categoryService.findAllCategories().subscribe({
      next: (data) => {
        this.categories = data;
        this.loading = false;
      },
      error: (err) => {
        this.message = 'Error loading data from the server.';
        this.loading = false;
      }
    });
  }

  navigateTo(action: 'view' | 'update' | 'delete', category: Category): void {
    // Pass the selected category to the shared service
    this.sharedService.setSelectedCategory(category);

    // Navigate to the respective component
    const routes = {
      view: '/categories/view',
      update: '/categories/update',
      delete: '/categories/delete',
    };

    const route = routes[action];
    if (route) {
      this.router.navigate([route]);
    }
  }
}
