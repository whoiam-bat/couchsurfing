import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LookingHostsComponent } from './looking-hosts.component';

describe('LookingHostsComponent', () => {
  let component: LookingHostsComponent;
  let fixture: ComponentFixture<LookingHostsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LookingHostsComponent]
    });
    fixture = TestBed.createComponent(LookingHostsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
