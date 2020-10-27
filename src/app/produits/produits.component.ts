import { Component, OnInit } from '@angular/core';
import {CatalogueService} from '../catalogue.service';
import {ActivatedRoute, NavigationEnd, Router} from '@angular/router';

@Component({
  selector: 'app-produits',
  templateUrl: './produits.component.html',
  styleUrls: ['./produits.component.css']
})
export class ProduitsComponent implements OnInit {
  products;

  constructor(private catalogueService:CatalogueService,private router:Router,
              private route: ActivatedRoute) {
    router.events.subscribe(event=>{
      if(event instanceof NavigationEnd){
        //console.log(route.snapshot.params.urlProds);
        let url=atob(route.snapshot.params.urlProds);
        //console.log(url);
        this.getProduits(url);
      }
    })

  }

  ngOnInit() {
  }
  getProduits(url){
    this.catalogueService.getRessource(url)
      .subscribe(data=>{
        //console.log('data  :',data._embedded.products[2].name);
        this.products=data;
      },err=>{
        console.log(err);
      })

  }

}
