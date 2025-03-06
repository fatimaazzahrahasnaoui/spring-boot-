import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CategoryService } from '../../services/category.service';

@Component({
  selector: 'app-create-category',
  standalone: false,
  
  templateUrl: './create-category.component.html',
  styleUrl: './create-category.component.css'
})
export class CreateCategoryComponent {
  categoryForm: FormGroup;
  message: string = '';

  constructor(private fb: FormBuilder, private categoryService: CategoryService) {
    this.categoryForm = this.fb.group({
      label: ['', Validators.required]
    });
  } 

  onSubmit(): void {
    if (this.categoryForm.invalid) {
      return;
    }

    const newCategory = this.categoryForm.value;
    this.categoryService.createCategory(newCategory).subscribe({
      next: () => {
        this.message = `Category '${newCategory.label}' created successfully.`;
        this.categoryForm.reset();
      },
      error: (err) => {
        console.error('Error creating category:', err);
        this.message = 'Error creating the category.';
      }
    });
  }
}
