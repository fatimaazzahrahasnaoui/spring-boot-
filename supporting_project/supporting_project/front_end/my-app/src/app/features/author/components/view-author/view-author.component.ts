import { Component, OnInit } from '@angular/core';
import { Author } from '../../model/author';
import { SharedService } from '../../../shared/services/shared.service';

@Component({
  selector: 'app-view-author',
  standalone: false,
  
  templateUrl: './view-author.component.html',
  styleUrl: './view-author.component.css'
})
export class ViewAuthorComponent implements OnInit {
  
  // storing the author : 
  author : Author | null = null ;  
  message : string = '' ;

  constructor(private sharedService : SharedService) {}

  ngOnInit(): void {
    this.loadData() ; 
  }



  loadData() : void  {
    this.sharedService.getSelectedAuthor().subscribe(
      { 
        next : (data) => { 
          this.author = data ; 
        }, 

        error : (err) => {
          console.error('Error receiving author:', err);
          this.message = 'Error loading author details.';
        }
      }
    )
  }

}
