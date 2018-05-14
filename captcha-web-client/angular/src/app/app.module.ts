import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { CaptchaService } from "./services/captcha.service";

import { AppComponent } from './app.component';
import { CaptchaComponent } from './captcha/captcha.component';


@NgModule({
  declarations: [
    AppComponent,
    CaptchaComponent
  ],
  imports: [
    BrowserModule, 
    FormsModule, 
    HttpClientModule,
    NgbModule.forRoot()
  ],
  providers: [CaptchaService],
  bootstrap: [AppComponent]
})
export class AppModule { }
