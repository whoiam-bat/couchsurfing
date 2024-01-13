import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-userinfo',
  templateUrl: './userinfo.component.html',
  styleUrls: ['./userinfo.component.css']
})
export class UserinfoComponent implements OnInit {

  selectedOption: string = "Not Accepting Guests";
  searchOptions: string[] = ["Not Accepting Guests", "Accepting Guests"];

  ngOnInit(): void {
    this.resetDefaultOption();
  }

  doSelectOption(option: string): void {
    this.selectedOption = option;
  }

  resetDefaultOption(): void {
    this.selectedOption = "Not Accepting Guests";
  }

  dropdownList(): void {
    let list = document.getElementById('accommodation-options')
    list!.classList.toggle('open')
  }

}
