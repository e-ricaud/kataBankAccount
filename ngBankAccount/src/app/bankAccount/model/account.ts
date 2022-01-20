import { Operation } from "./operation";

export class Account {

  id: number;
  firstName: string;
  lastName: string;
  balance: number;
  operations: Operation[];

  constructor(){
    this.id = 0;
    this.firstName = "";
    this.lastName = "";
    this.balance = 0;
    this.operations = []
  }

}
