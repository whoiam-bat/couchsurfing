import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyTravelsComponent } from './my-travels.component';

describe('MyTravelsComponent', () => {
  let component: MyTravelsComponent;
  let fixture: ComponentFixture<MyTravelsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MyTravelsComponent]
    });
    fixture = TestBed.createComponent(MyTravelsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
