import { Observable } from "rxjs";
import { Author } from "../model/author";
import { Injectable } from "@angular/core";



@Injectable({
  providedIn: 'root'
})


export abstract class AbstractAuthorServices {

    protected readonly baseUrl : string = 'http://localhost:3000/authors' ; 

    public abstract createAuthor(author : Author) : Observable<Author> ; 
    public abstract findAuthorById(id : number) : Observable<Author> ; 
    public abstract findAllAuthors() : Observable<Author[]> ; 
    public abstract updateAuthor(author : Author) : Observable<Author> ; 
    public abstract deleteAuthor(id : number) : Observable<Author> ; 
}
