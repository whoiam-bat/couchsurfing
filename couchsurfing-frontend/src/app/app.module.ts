import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {RouterModule, RouterOutlet, Routes} from "@angular/router";
import {HeaderComponent} from './components/header-content/header/header.component';
import {NgOptimizedImage} from "@angular/common";
import {SearchComponent} from './components/header-content/search/search.component';
import {NavbarComponent} from './components/header-content/navbar/navbar.component';
import {FooterComponent} from './components/footer/footer.component';
import {DashboardComponent} from './components/dashboard/dashboard.component';
import {UserinfoComponent} from './components/dashboard/userinfo/userinfo.component';
import { CompletionLevelComponent } from './components/dashboard/completion-level/completion-level.component';
import { ToDoComponent } from './components/dashboard/to-do/to-do.component';
import { MyTravelsComponent } from './components/dashboard/my-travels/my-travels.component';
import { LookingHostsComponent } from './components/dashboard/looking-hosts/looking-hosts.component';


const routes: Routes = [
    {path: 'dashboard', component: DashboardComponent},
    {path: '', redirectTo: '/dashboard', pathMatch: 'full'},
    {path: '**', redirectTo: '/dashboard', pathMatch: 'full'}
];

@NgModule({
    declarations: [
        AppComponent,
        HeaderComponent,
        SearchComponent,
        NavbarComponent,
        FooterComponent,
        DashboardComponent,
        UserinfoComponent,
        CompletionLevelComponent,
        ToDoComponent,
        MyTravelsComponent,
        LookingHostsComponent,
    ],
    imports: [
        RouterModule.forRoot(routes),
        BrowserModule,
        RouterOutlet,
        NgOptimizedImage
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
