import { Component, OnInit } from '@angular/core';
import { Account } from '../../model/account';
import { AccountService } from '../../services/account.service';
import { MatDialog } from '@angular/material/dialog'
import { OperationDialogComponent } from '../operation-dialog/operation-dialog.component';
import { Subscription } from 'rxjs';
import { OperationType } from '../../model/operationType';
import { OperationForm } from 'src/app/shared/model/operation-form';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html'
  // styleUrls: ['./account.component.scss']
})
export class AccountComponent implements OnInit {

  accounts: Account[] = [];

  private subscriptions: Subscription = new Subscription();

  constructor(
    private dialog: MatDialog,
    private accountService: AccountService) {
  }

  ngOnInit() {
    this.findAccountById(1)
  }

  findAccountById(id:number) {
    this.subscriptions.add(
      this.accountService.findById(id).subscribe(data => {
        this.accounts = [data];
      })
    )
  }

  onOperation(): void {
    console.log('on opération');
    const dialogRef = this.dialog.open(OperationDialogComponent, {width: '600px', disableClose: true})

    // this.subscriptions.add(

    // )
    dialogRef.afterClosed().subscribe(form => {
      console.log('operationType : ' + form.operationType )
      let accountOp = this.accounts[0];
      accountOp.operations = [];
      const operationForm : OperationForm = {
        account: accountOp,
        amount: form.amount
      }
      if(form.operationType === OperationType.DEPOSIT) {
        this.accountService.doDeposit(operationForm).subscribe(
          response  => {
            if(response.code === 'ok') {
              this.findAccountById(1);
            }
          }
        )
      } else if (form.operationType === OperationType.WITHDRAWAL) {

        this.accountService.doWithdraw(operationForm).subscribe(
          response  => {
            if(response.code === 'ok') {
              this.findAccountById(1);
            }
          }
        )
      }
    });
  }

}
