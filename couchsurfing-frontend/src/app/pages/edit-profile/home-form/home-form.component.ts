import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { User } from 'src/app/services/models';

@Component({
  selector: 'app-home-form',
  templateUrl: './home-form.component.html',
  styleUrls: ['./home-form.component.css'],
})
export class HomeFormComponent implements OnInit {
  @Input() user!: User;

  gendersSet: string[] = ['MALE', 'FEMALE'];
  guestsAmount: number[] = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

  form = new FormGroup({
    availability: new FormControl(),
    guests: new FormControl(0),
    gender: new FormControl(),
    kids: new FormControl(),
    pets: new FormControl(),
    disabledPeople: new FormControl(),
    smoking: new FormControl(),
    iamSmoker: new FormControl(),
    ihaveKids: new FormControl(),
    ihavePets: new FormControl(),
    otherInfo: new FormControl(),
  });

  ngOnInit(): void {
    this.initFormValues();
  }
  initFormValues() {
    this.form.patchValue({
      availability: this.user.userHome?.isAcceptingGuests ?? undefined,
      guests: this.user.userHome?.maxGuests ?? 0,
      gender: this.user.userHome?.preferredGender ?? undefined,
      kids: this.user.userHome?.kidFriendly ?? undefined,
      pets: this.user.userHome?.petFriendly ?? undefined,
      disabledPeople: this.user.userHome?.wheelchairAllowed ?? undefined,
      smoking: this.user.userHome?.smokingAllowed ?? undefined,
      iamSmoker: this.user.userHome?.iamSmoker ?? undefined,
      ihaveKids: this.user.userHome?.ihaveKids ?? undefined,
      ihavePets: this.user.userHome?.ihavePets ?? undefined,
      otherInfo: this.user.userHome?.otherInfo ?? undefined,
    });
  }
}
