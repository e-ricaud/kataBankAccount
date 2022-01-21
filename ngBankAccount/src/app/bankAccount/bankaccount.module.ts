import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BankaccountRoutingModule } from './bankaccount-routing.module';
import { AccountComponent } from './account/account.component';


@NgModule({
  declarations: [
    AccountComponent
  ],
  imports: [
    CommonModule,
    BankaccountRoutingModule
  ]
})
export class BankaccountModule { }
