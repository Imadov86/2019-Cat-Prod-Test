import { Component, OnInit } from '@angular/core';
import {CatalogueService} from '../catalogue.service';

@Component({
  selector: 'app-admin-categories',
  templateUrl: './admin-categories.component.html',
  styleUrls: ['./admin-categories.component.css']
})
export class AdminCategoriesComponent implements OnInit {
  categories;
  mode='list';

  constructor(private catalogueService:CatalogueService) { }

  ngOnInit() {
    this.onGetAllCategories();

  }
  onGetAllCategories(){
    this.catalogueService.getAllCategories()
      .subscribe(data=>{
        this.categories=data;
      },error1 => {
        console.log(error1)
      })
  }

  onDeleteCat(cat) {
    let c=confirm("Etez vous sûre")
    if(!c) return;
    this.catalogueService.deleteRessource(cat._links.self.href)
      .subscribe(data=>{
          this.onGetAllCategories();
      },error1 => {
        console.log(error1)
      })

  }

  newCat() {
    this.mode='new-cat';
  }

  onSaveCat(data: any) {
    let url=this.catalogueService.host+"/categories";
    this.catalogueService.postRessource(url,data)
      .subscribe(data=>{
        this.mode='list';
        this.onGetAllCategories();
      },error=>{
      console.log(error);
    })

  }
  currentCategorie;
  onEditCat(cat) {
    this.catalogueService.getRessource(cat._links.self.href)
      .subscribe(data=>{
        this.currentCategorie=data;
        this.mode='edit-cat';
      },error1 => {

      })
    
  }

  onUpdateCat(data: any) {
    this.catalogueService.putRessource(this.currentCategorie._links.self.href,data)
      .subscribe(data=>{
        this.mode='list';
        this.onGetAllCategories();
      },error=>{
        console.log(error);
      })
  }
}
