import { Component, OnInit } from '@angular/core';
import { Author } from '../../model/author';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SharedService } from '../../../shared/services/shared.service';
import { AuthorService } from '../../services/author.service';

@Component({
  selector: 'app-update-author',
  standalone: false,
  
  templateUrl: './update-author.component.html',
  styleUrl: './update-author.component.css'
})
export class UpdateAuthorComponent implements OnInit {

  selectedAuthor : Author | null = null ; 
  form : FormGroup ; 
  message : string = '' ; 


  constructor(private sharedService : SharedService, private authorService : AuthorService , private fb : FormBuilder) { 
    this.form = this.fb.group({
      name : ['' , Validators.required], 
      age : ['' , Validators.required] 
    })
  }
  



  ngOnInit(): void {
    this.loadData() ; 
  }


  // load the selected author from the shared service
  loadData() : void {
    this.sharedService.getSelectedAuthor().subscribe(

      {
        next : (data) => { 
          this.selectedAuthor = data ; 
          
          // initialize the form with the current author data : 
          if(this.selectedAuthor) { 
            this.form.patchValue({
              name : this.selectedAuthor.name ,
              age : this.selectedAuthor.age 
            })
          }
        }
      }
    )
  }




  onUpdate(): void {
    // Handle invalid form submission
    if (this.form.invalid) {
      return;
    }

    // Updated author object
    const updatedAuthor: Author = {
      ...this.selectedAuthor, // Retain the original author's ID and other properties
      ...this.form.value, // Update with new form values
    };

    console.log('Updated Author:', updatedAuthor);
    this.message = `Author ${updatedAuthor.name} was updated successfully.`;

    this.authorService.updateAuthor(updatedAuthor) ;
  }

}
