import { Component } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-search',
  templateUrl: './search-box.component.html'
})
export class SearchBoxComponent {

  searchForm = new FormGroup({
    budgetOperator: new FormControl('')
  });

}
