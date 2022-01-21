import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BankaccountRoutingModule } from './bankaccount-routing.module';
import { AccountComponent } from './components/account/account.component';
import { OperationDialogComponent } from './components/operation-dialog/operation-dialog.component';
import { ReactiveFormsModule } from '@angular/forms';
import { AngularMaterialModule } from '../angular-material.module';
import { AccountHistoryComponent } from './components/account-history/account-history.component';


@NgModule({
  declarations: [
    AccountComponent,
    OperationDialogComponent,
    AccountHistoryComponent
  ],
  imports: [
    CommonModule,
    BankaccountRoutingModule,
    ReactiveFormsModule,
    AngularMaterialModule
  ]
})
export class BankaccountModule { }
