import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { DistributorService } from '../core/service/distributor.service';
import { DirectorService } from '../core/service/director.service';
import { MovieTypeService } from '../core/service/movie-type.service';
import { MovieSearchRequest } from '../core/model/movie-search-request.model';

@Component({
  selector: 'app-search',
  templateUrl: './search-box.component.html'
})
export class SearchBoxComponent implements OnInit {

  distributors: string[];
  directors: string[];
  types: string[];
  @Output() search = new EventEmitter<MovieSearchRequest>()

  searchForm = new FormGroup({
    title: new FormControl(''),
    distributor: new FormControl(''),
    director: new FormControl(''),
    type: new FormControl(''),
    budgetOperator: new FormControl(''),
    productionBudgetMin: new FormControl(0),
    productionBudgetMax: new FormControl(1)
  });


  constructor(
    private distributorService: DistributorService,
    private directorService: DirectorService,
    private movieTypeService: MovieTypeService
  ) { }

  ngOnInit() {
    this.distributorService.getAllDistributors().subscribe({
      next: (data) => this.distributors = data
    })
    this.directorService.getAllDirectors().subscribe({
      next: (data) => this.directors = data
    })
    this.movieTypeService.getAllMovieTypes().subscribe({
      next: (data) => this.types = data
    })
  }

  onSearch() {
    if(this.hasCriteria()) {
      let data = {
        title: this.searchForm.get('title').value,
        distributor: this.searchForm.get('distributor').value,
        director: this.searchForm.get('director').value,
        type: this.searchForm.get('type').value,
        productionBudgetOp: this.searchForm.get('budgetOperator').value,
        productionBudgetMin: this.searchForm.get('productionBudgetMin').value,
        productionBudgetMax: this.searchForm.get('productionBudgetMax').value
      }
      this.search.emit(data);
    } else {
      this.search.emit();
    } 
  }

  hasCriteria() {
    if(
      this.searchForm.get('title').value === '' &&
      this.searchForm.get('distributor').value === '' &&
      this.searchForm.get('director').value === '' &&
      this.searchForm.get('type').value === '' &&
      this.searchForm.get('budgetOperator').value === ''
      ) {
        return false;
      }
    return true;
  }
}
