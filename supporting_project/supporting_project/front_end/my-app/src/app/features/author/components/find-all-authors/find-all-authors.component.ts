import { Component, OnInit } from '@angular/core';
import { Author } from '../../model/author';
import { SharedService } from '../../../shared/services/shared.service';
import { Router } from '@angular/router';
import { AbstractAuthorServices } from '../../services/abstract-author-services';

@Component({
  selector: 'app-find-all-authors',
  standalone: false,
  templateUrl: './find-all-authors.component.html',
  styleUrl: './find-all-authors.component.css'
})
export class FindAllAuthorsComponent implements OnInit {

  authors : Author[] = [] ; 
  loading : boolean = true ;
  message : string = '' ; 

  constructor(private authorService : AbstractAuthorServices , private sharedService : SharedService , private router : Router) { }


  ngOnInit(): void {
    this.loadData() ; 
  }



  loadData() {
    this.authorService.findAllAuthors().subscribe(
      {
        next : (data) => { 
          this.authors = data ; 
          this.loading = false ; 
        } , 

        error : (err) => {
          this.message = 'error loading data from the server' ; 
          this.loading = false ; 
        } 
      }
    )
  }


  navigateTo(action: 'view' | 'update' | 'delete', author: Author): void {
    // Pass the selected author to the shared service
    this.sharedService.setSelectedAuthor(author);
  
    // Navigate to the respective component
    const routes = {
      view: '/authors/view',
      update: '/authors/update',
      delete: '/authors/delete',
    };
  
    const route = routes[action]; // Now TypeScript knows 'action' is a valid key
    if (route) {
      this.router.navigate([route]);
    }
  }
  

}
