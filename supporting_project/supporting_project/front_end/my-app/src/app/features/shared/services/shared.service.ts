import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Author } from '../../author/model/author';
import { Book } from '../../book/model/book.model';
import { Category } from '../../category/model/category.model';

@Injectable({
  providedIn: 'root'
})
export class SharedService {
  // Holding the selected author inside a behavior subject object
  private selectedAuthor = new BehaviorSubject<Author | null>(null) ; 
  private selectedBook = new BehaviorSubject<Book | null>(null) ;
  private selectedCategory = new BehaviorSubject<Category | null>(null) ; 
 

  constructor() { }

  // expose the selected author as observable 
  public getSelectedAuthor() : Observable<Author | null> { 
    return this.selectedAuthor.asObservable() ; 
  }

  // method for updating the author : 
  public setSelectedAuthor(author : Author | null) { 
    this.selectedAuthor.next(author) ;
  }


    // expose the selected author as observable 
    public getSelectedBook() : Observable<Book | null> { 
      return this.selectedBook.asObservable() ; 
    }
  
    // method for updating the author : 
    public setSelectedBook(book : Book | null) { 
      this.selectedBook.next(book) ;
    }



      // expose the selected author as observable 
      public getSelectedCategory() : Observable<Category | null> { 
        return this.selectedCategory.asObservable() ; 
      }
    
      // method for updating the author : 
      public setSelectedCategory(category : Category | null) { 
        this.selectedCategory.next(category) ;
      }
}
