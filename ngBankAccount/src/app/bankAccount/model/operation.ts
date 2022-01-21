import { OperationType } from "./operationType";

export class Operation {

  idOp: number;
  operationType: OperationType;
  operationAmount: number;
  dateOperation: Date;

  constructor(){
    this.idOp = 0;
    this.operationType = OperationType.DEPOSIT;
    this.operationAmount = 0;
    this.dateOperation = new Date();
  }

}
