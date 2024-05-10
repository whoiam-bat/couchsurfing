import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewsTabComponent } from './reviews-tab.component';

describe('ReviewsPageComponent', () => {
  let component: ReviewsTabComponent;
  let fixture: ComponentFixture<ReviewsTabComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ReviewsTabComponent]
    });
    fixture = TestBed.createComponent(ReviewsTabComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
