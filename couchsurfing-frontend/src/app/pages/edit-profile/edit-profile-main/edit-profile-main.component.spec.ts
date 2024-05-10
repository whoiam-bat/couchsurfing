import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditProfileMainComponent } from './edit-profile-main.component';

describe('EditProfileMainComponent', () => {
  let component: EditProfileMainComponent;
  let fixture: ComponentFixture<EditProfileMainComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditProfileMainComponent]
    });
    fixture = TestBed.createComponent(EditProfileMainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
