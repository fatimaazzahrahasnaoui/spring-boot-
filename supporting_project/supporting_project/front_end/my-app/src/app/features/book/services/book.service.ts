import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { AbstractBookServices } from './abstract-book-services.service';
import { Book } from '../model/book.model';

@Injectable({
  providedIn: 'root'
})
export class BookService extends AbstractBookServices {

  constructor(private http: HttpClient) {
    super();
  }

  public override createBook(book: Book): Observable<Book> {
    return this.http.post<Book>(`${this.baseUrl}`, book);
  }

  public override findBookById(id: number): Observable<Book> {
    return this.http.get<Book>(`${this.baseUrl}/${id}`);
  }

  public override findAllBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(`${this.baseUrl}`);
  }

  public override updateBook(book: Book): Observable<Book> {
    return this.http.put<Book>(`${this.baseUrl}/${book.id}`, book);
  }

  public override deleteBook(id: number): Observable<Book> {
    return this.http.delete<Book>(`${this.baseUrl}/${id}`);
  }
}
