import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FindAllCategoriesComponent } from './components/find-all-categories/find-all-categories.component';
import { CreateCategoryComponent } from './components/create-category/create-category.component';
import { UpdateCategoryComponent } from './components/update-category/update-category.component';
import { ViewCategoryComponent } from './components/view-category/view-category.component';
import { DeleteCategoryComponent } from './components/delete-category/delete-category.component';

const routes: Routes = [
  { path: 'categories', component: FindAllCategoriesComponent },
  { path: 'categories/create', component: CreateCategoryComponent },
  { path: 'categories/update', component: UpdateCategoryComponent },
  { path: 'categories/view', component: ViewCategoryComponent },
  { path: 'categories/delete', component: DeleteCategoryComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CategoryRoutingModule { }
