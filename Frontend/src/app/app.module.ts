import { AuthGuardService } from './auth-guard.service';
import { AuthenticationModule } from './modules/authentication/authentication.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppComponent } from './app.component';
import { MovieModule } from './modules/movie/movie.module';
import { SharedModule } from './modules/shared/shared.module';


const appRoutes: Routes = 
[
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  }
]

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    MovieModule,
    BrowserAnimationsModule,
    SharedModule,
    AuthenticationModule,
    RouterModule.forRoot(appRoutes),
  ],
  providers: [AuthGuardService],
  bootstrap: [AppComponent]
})
export class AppModule { }
