import { Component, OnInit } from '@angular/core';
import { CaptchaService } from "../services/captcha.service";
import { Captcha, CaptchaValidation } from "../models/captcha";

@Component({
  selector: 'app-captcha',
  templateUrl: './captcha.component.html',
  styleUrls: ['./captcha.component.css']
})
export class CaptchaComponent implements OnInit {

  constructor(private captchaService: CaptchaService) { }

  captcha: Captcha
  image: string
  validation: CaptchaValidation = {}
  message: string
  validationClass: string

  ngOnInit() {
    this.reload()
  }

  reload(){
    this.validation.text = null
    this.captchaService.getCaptcha().subscribe(captcha => {
      this.captcha = captcha
      this.message = null
      this.validationClass=null
      this.image = 'data:image/jpeg;base64,'+captcha.image
      this.validation.captchaSecret = captcha.captchaSecret
      this.validation.captchaToken = captcha.captchaToken
    })
  }

  validate(){
    this.captchaService.validate(this.validation)
      .subscribe(errorMessage => {
        this.message = errorMessage ? errorMessage : "Valid!" 
        this.validationClass = errorMessage ? "failure" : "success"
      })
  }
}
