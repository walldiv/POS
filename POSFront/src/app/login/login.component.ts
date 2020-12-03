import { Component, OnInit, ViewChild } from '@angular/core';
import {NgForm} from '@angular/forms';
import {AuthService} from './auth.service';
import {Employee} from '../shared/employee.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})


export class LoginComponent implements OnInit {
  @ViewChild('f') authForm: NgForm;

  constructor(private authSvc: AuthService) { }

  ngOnInit(): void {
  }

  onSubmitForm(form: NgForm) {
    if(!form.valid){return;}
    const value = form.value;
    console.log("SUBMITTED FORM: \n" + value.email + "\n" + value.password);
    form.reset();
    let tmpEmp = new Employee(value.email, value.password);
    this.authSvc.login(tmpEmp).subscribe(
      response => {console.log(response);},
      error => { console.log(error)}
    );
  }

  onClearForm(form: NgForm){
    form.reset();
  }
}
