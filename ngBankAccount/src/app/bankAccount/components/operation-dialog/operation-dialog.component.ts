import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog'
import { Subscription } from 'rxjs';
import { OperationType } from '../../model/operationType';


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
  ) { }

  ngOnInit(): void {
  }

  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }

  onSaveOperation():void {
    if(!this.operationForm.invalid) {
      this.dialogRef.close(this.operationForm.value);
    }
  }

}
