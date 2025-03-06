import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CategoryRoutingModule } from './category-routing.module';
import { CreateCategoryComponent } from './components/create-category/create-category.component';
import { UpdateCategoryComponent } from './components/update-category/update-category.component';
import { ViewCategoryComponent } from './components/view-category/view-category.component';
import { FindAllCategoriesComponent } from './components/find-all-categories/find-all-categories.component';
import { DeleteCategoryComponent } from './components/delete-category/delete-category.component';
import { AbstractCategoryServices } from './services/abstract-category-services.service';
import { CategoryService } from './services/category.service';
import { provideHttpClient, withFetch } from '@angular/common/http';


@NgModule({
  declarations: [
    CreateCategoryComponent,
    UpdateCategoryComponent,
    ViewCategoryComponent,
    FindAllCategoriesComponent,
    DeleteCategoryComponent
  ],
  imports: [
    CommonModule,
    CategoryRoutingModule
  ], 

  providers: [
      { provide: AbstractCategoryServices, useClass: CategoryService },
      provideHttpClient(withFetch()),
    ],
})
export class CategoryModule { }
 