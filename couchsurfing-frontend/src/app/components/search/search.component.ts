import { Component } from '@angular/core';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent {

  selectedOption: string = "Explore";
  searchOptions: string[] = ["Explore", "Find Hosts", "Find Travelers"];



  doSelectOption(option: string): void {
    this.selectedOption = option;
  }

  resetDefaultOption(): void {
    this.selectedOption = "Explore";
  }

  dropdownList():void {
    let list = document.getElementById('options')
    list!.classList.toggle('open')
  }

}
