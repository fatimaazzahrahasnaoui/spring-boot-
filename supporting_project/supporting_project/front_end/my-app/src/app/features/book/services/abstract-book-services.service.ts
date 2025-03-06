import { Observable } from "rxjs";
import { Injectable } from "@angular/core";
import { Book } from "../model/book.model";

@Injectable({
  providedIn: 'root'
})
export abstract class AbstractBookServices {
  
  protected readonly baseUrl: string = 'http://localhost:3000/books';

  public abstract createBook(book: Book): Observable<Book>;
  public abstract findBookById(id: number): Observable<Book>;
  public abstract findAllBooks(): Observable<Book[]>;
  public abstract updateBook(book: Book): Observable<Book>;
  public abstract deleteBook(id: number): Observable<Book>;
}
