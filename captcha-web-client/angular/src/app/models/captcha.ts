export class Captcha{
    image: string
    captchaToken: string
    captchaSecret: string
}

export class CaptchaValidation{
    text?: string
    captchaToken?: string
    captchaSecret?: string
}