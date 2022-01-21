import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog'
import { Subscription } from 'rxjs';
import { OperationType } from '../../model/operationType';
import { AccountService } from '../../services/account.service';


@Component({
  selector: 'app-operation-dialog',
  templateUrl: './operation-dialog.component.html',
  styleUrls: ['./operation-dialog.component.scss']
})
export class OperationDialogComponent implements OnInit, OnDestroy {

  operationTypes = [OperationType.DEPOSIT, OperationType.WITHDRAWAL];

  public operationForm = new FormGroup({
    operationType: new FormControl(OperationType.DEPOSIT),
    amount: new FormControl('', [Validators.required]),
  });

  private subscriptions: Subscription = new Subscription();

  constructor(
    private dialogRef: MatDialogRef<OperationDialogComponent>,
    private accountService: AccountService
  ) { }

  ngOnInit(): void {
  }

  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }

  onSaveOperation():void {
    if(!this.operationForm.invalid) {
      // const form = this.operationForm.value;
      // form.operationType;
      // form.amount;

      // if(form.operationType === OperationType.DEPOSIT) {
      //   this.accountService.doDeposit(acc)
      // }

      // this.subscriptions.add(
        //Service de lancement d'opération
      // )
      console.log('value : ' + this.operationForm.value);
      this.dialogRef.close(this.operationForm.value);
    }

  }

}
