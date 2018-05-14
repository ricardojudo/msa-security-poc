import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";

import { Observable } from "rxjs/Observable";
import { catchError, tap, map } from "rxjs/operators";
import { of } from "rxjs/observable/of";


import { Captcha, CaptchaValidation } from "../models/captcha";

import { environment } from "../../environments/environment";

const CAPTCHA_URL = environment.captchaUrl

@Injectable()
export class CaptchaService {

  constructor(private httpClient: HttpClient) { }

  getCaptcha(): Observable<Captcha>{
    return this.httpClient.get<Captcha>(CAPTCHA_URL);
  }

  validate(validation: CaptchaValidation): Observable<any>{
    return this.httpClient.post(CAPTCHA_URL, validation).pipe(
      catchError(this.handleCaptchaError())
    )
      //.pipe(catchError(this.handleValidationError()))
  }

  handleCaptchaError(){
    return (error):Observable<string> => {
      var message;
      switch(error.status){        
        case 400:
          message = "Invalid text"
          break;
        default:
        message = "Service unavailable"
        }
      return of(message)
    }
  }

}
