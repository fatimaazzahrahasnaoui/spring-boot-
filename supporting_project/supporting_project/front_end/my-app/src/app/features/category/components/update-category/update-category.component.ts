import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SharedService } from '../../../shared/services/shared.service';
import { CategoryService } from '../../services/category.service';
import { Category } from '../../model/category.model';

@Component({
  selector: 'app-update-category',
  standalone: false,
  templateUrl: './update-category.component.html',
  styleUrl: './update-category.component.css'
})
export class UpdateCategoryComponent implements OnInit {

  selectedCategory: Category | null = null;
  form: FormGroup;
  message: string = '';

  constructor(private sharedService: SharedService, private categoryService: CategoryService, private fb: FormBuilder) {
    this.form = this.fb.group({
      label: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.loadData();
  }

  // Load the selected category from the shared service
  loadData(): void {
    this.sharedService.getSelectedCategory().subscribe(
      {
        next: (data) => {
          this.selectedCategory = data;

          // Initialize the form with the current category data
          if (this.selectedCategory) {
            this.form.patchValue({
              label: this.selectedCategory.label
            });
          }
        },
        error: (err) => {
          console.error('Error loading category:', err);
          this.message = 'Error loading category details.';
        }
      }
    );
  }

  onUpdate(): void {
    // Handle invalid form submission
    if (this.form.invalid) {
      return;
    }

    // Updated category object
    const updatedCategory: Category = {
      ...this.selectedCategory, // Retain the original category's ID and other properties
      ...this.form.value // Update with new form values
    };

    console.log('Updated Category:', updatedCategory);
    this.message = `Category '${updatedCategory.label}' was updated successfully.`;

    this.categoryService.updateCategory(updatedCategory).subscribe(
      {
        next: () => {
          console.log('Category updated successfully.');
        },
        error: (err) => {
          console.error('Error updating category:', err);
          this.message = 'Error updating category.';
        }
      }
    );
  }
}
