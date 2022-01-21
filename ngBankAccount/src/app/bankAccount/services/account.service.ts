import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Account } from '../model/account';
import { JSONResponse } from 'src/app/shared/model/json-response';
import { OperationForm } from 'src/app/shared/model/operation-form';

@Injectable()
export class AccountService {

  private usersUrl: string;

  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:8080/account';
  }

  public findById(id: number): Observable<Account> {
    let params = new HttpParams().set('id',id);
    return this.http.get<Account>(`${this.usersUrl}`, { params });
  }

  public doWithdraw(operationForm: OperationForm): Observable<JSONResponse> {
    return this.http.post<JSONResponse>(`${this.usersUrl}/operation/withdraw`, operationForm);
  }

  public doDeposit(operationForm: OperationForm): Observable<JSONResponse> {
    return this.http.post<JSONResponse>(`${this.usersUrl}/operation/deposit`, operationForm);
  }

}
