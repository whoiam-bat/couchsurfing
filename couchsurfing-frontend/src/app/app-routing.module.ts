import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { VerifyComponent } from './pages/verify/verify.component';
import { PublicTripsComponent } from './pages/public-trips/public-trips.component';
import { CreateTripComponent } from './pages/public-trips/create-trip/create-trip.component';
import { authGuard } from './services/guard/auth.guard';
import { EditProfileComponent } from './pages/edit-profile/edit-profile.component';
import { CouchRequestsComponent } from './pages/couch-requests/couch-requests.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'register',
    component: RegisterComponent,
  },
  {
    path: 'dashboard',
    component: DashboardComponent,
    canActivate: [authGuard],
  },
  {
    path: 'dashboard/public-trips',
    component: PublicTripsComponent,
    canActivate: [authGuard],
  },
  {
    path: 'dashboard/public-trips/manage',
    component: CreateTripComponent,
    canActivate: [authGuard],
  },
  {
    path: 'dashboard/couch-requests',
    component: CouchRequestsComponent,
    canActivate: [authGuard],
  },
  {
    path: 'profile',
    component: ProfileComponent,
    canActivate: [authGuard],
  },
  {
    path: 'profile/edit-profile',
    component: EditProfileComponent,
    canActivate: [authGuard],
  },
  {
    path: 'profile/:id',
    component: ProfileComponent,
    canActivate: [authGuard],
  },
  {
    path: 'verify',
    component: VerifyComponent,
    canActivate: [authGuard],
  },
  {
    path: '',
    component: DashboardComponent,
    canActivate: [authGuard],
  },
  {
    path: '**',
    component: PageNotFoundComponent,
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
