import { Observable } from "rxjs";
import { Injectable } from "@angular/core";
import { Category } from "../model/category.model";

@Injectable({
  providedIn: 'root'
})
export abstract class AbstractCategoryServices {

  protected readonly baseUrl: string = 'http://localhost:3000/categories';

  public abstract createCategory(category: Category): Observable<Category>;
  public abstract findCategoryById(id: number): Observable<Category>;
  public abstract findAllCategories(): Observable<Category[]>;
  public abstract updateCategory(category: Category): Observable<Category>;
  public abstract deleteCategory(id: number): Observable<Category>;
}
