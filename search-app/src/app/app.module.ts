import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SearchBoxComponent } from './search-box/search-box.component';
import { MovieCardComponent } from './movie-card/movie-card.component';
import { MovieCardLoadingComponent } from './movie-card-loading/movie-card-loading.component';
import { EmptySearchComponent } from './empty-search/empty-search.component';

@NgModule({
  declarations: [
    AppComponent,
    SearchBoxComponent,
    MovieCardComponent,
    MovieCardLoadingComponent,
    EmptySearchComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
