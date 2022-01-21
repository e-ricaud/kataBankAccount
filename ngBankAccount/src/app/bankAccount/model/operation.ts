import { OperationType } from "./operationType";

export class Operation {

  id: number;
  operationType: OperationType;
  operationAmount: number;
  dateOperation: Date;

  constructor(){
    this.id = 0;
    this.operationType = OperationType.DEPOSIT;
    this.operationAmount = 0;
    this.dateOperation = new Date();
  }

}
