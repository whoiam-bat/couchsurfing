import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-completion-level',
  templateUrl: './completion-level.component.html',
  styleUrls: ['./completion-level.component.css']
})
export class CompletionLevelComponent implements OnInit {
  progress!: number;

  ngOnInit(): void {
    this.progress = 55;
  }

}
