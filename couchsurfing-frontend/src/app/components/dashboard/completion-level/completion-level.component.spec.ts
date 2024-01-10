import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompletionLevelComponent } from './completion-level.component';

describe('CompletionLevelComponent', () => {
  let component: CompletionLevelComponent;
  let fixture: ComponentFixture<CompletionLevelComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CompletionLevelComponent]
    });
    fixture = TestBed.createComponent(CompletionLevelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
