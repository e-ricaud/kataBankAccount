import { Account } from "src/app/bankAccount/model/account";

export interface OperationForm {
  account: Account;
  amount: number;
}
