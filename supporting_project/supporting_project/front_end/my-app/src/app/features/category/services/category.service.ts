import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Category } from '../model/category.model';
import { AbstractCategoryServices } from './abstract-category-services.service';

@Injectable({
  providedIn: 'root'
})
export class CategoryService extends AbstractCategoryServices {

  constructor(private http: HttpClient) {
    super();
  }

  public override createCategory(category: Category): Observable<Category> {
    return this.http.post<Category>(`${this.baseUrl}`, category);
  }

  public override findCategoryById(id: number): Observable<Category> {
    return this.http.get<Category>(`${this.baseUrl}/${id}`);
  }

  public override findAllCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(`${this.baseUrl}`);
  }

  public override updateCategory(category: Category): Observable<Category> {
    return this.http.put<Category>(`${this.baseUrl}/${category.id}`, category);
  }

  public override deleteCategory(id: number): Observable<Category> {
    return this.http.delete<Category>(`${this.baseUrl}/${id}`);
  }
}
