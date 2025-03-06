import { Author } from "../../author/model/author";
import { Category } from "../../category/model/category.model";

export class Book {
    public id: number;
    public title: string;
    public publishDate: Date;
    public author: Author;
    public category: Category;
  
    constructor(id: number, title: string, publishDate: Date, author: Author, category: Category) {
      this.id = id;
      this.title = title;
      this.publishDate = publishDate;
      this.author = author;
      this.category = category;
    }
  }