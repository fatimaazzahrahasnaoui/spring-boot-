import { Injectable } from '@angular/core';
import { AbstractAuthorServices } from './abstract-author-services';
import { Observable } from 'rxjs';
import { Author } from '../model/author';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class AuthorService extends AbstractAuthorServices {

  

  constructor(private http : HttpClient) {
    super();
  }


  public override createAuthor(author: Author): Observable<Author> {
    return this.http.post<Author>(`${this.baseUrl}` , author) ; 
  }



  public override findAuthorById(id: number): Observable<Author> {
    return this.http.get<Author>(`${this.baseUrl}/${id}`) ; 
  }


  public override findAllAuthors(): Observable<Author[]> {
    return this.http.get<Author[]>(`${this.baseUrl}`); 
  }



  public override updateAuthor(author: Author): Observable<Author> {
    return this.http.put<Author>(`${this.baseUrl}/${author.id}` , author) ; 
  }



  public override deleteAuthor(id: number): Observable<Author> {
    return this.http.delete<Author>(`${this.baseUrl}/${id}`) ; 
  }



}
