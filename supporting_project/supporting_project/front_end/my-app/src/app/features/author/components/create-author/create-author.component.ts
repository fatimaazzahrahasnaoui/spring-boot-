import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AbstractAuthorServices } from '../../services/abstract-author-services';
import { Author } from '../../model/author';
import { AuthorService } from '../../services/author.service';


@Component({
  selector: 'app-create-author',
  standalone: false,
  
  templateUrl: './create-author.component.html',
  styleUrl: './create-author.component.css'
})

export class CreateAuthorComponent {

  authorForm : FormGroup ;
  message : string = '' ; 

  constructor(private authorService : AuthorService , private fb : FormBuilder) 
  
  { 
    // defining the form structure :
    this.authorForm = this.fb.group({
      name : ['enter author name' , Validators.required],
      age : ['enter author age' , Validators.required]
    })

  }


  // once the the submit button is clicked, this method will be triggered
  onSubmit() : void { 
    // handle the case when  the form is invalid : 
    if(this.authorForm.invalid) { 
      return ; 
    }

    // create the author object from the form : 
    const author = this.authorForm.value ; 

    // call the author saving service :
    this.createAuthor(author) ; 

  }


  public createAuthor(author : Author) : void { 
    this.authorService.createAuthor(author).subscribe
    (
      {
        next : (data) => {
          this.message = `Author ${data} was saved successfully` ; 
          this.authorForm.reset() ; 
        }, 

        error : (err) => {
          console.log(`Error crearing the author : ${err}`) ; 
          this.message = "Error creating the author."
        }
      }
    )
  }



  


}
