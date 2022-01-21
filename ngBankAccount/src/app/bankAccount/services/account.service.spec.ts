import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { AccountService } from './account.service';
import { Observable } from 'rxjs';
import { HttpParams } from '@angular/common/http';
import { OperationForm } from 'src/app/shared/model/operation-form';

describe('AccountService', () => {
  let accountService: AccountService;
  let httpTestingController: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule
      ],
      providers: [AccountService]
    });
    accountService = TestBed.inject(AccountService);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(accountService).toBeTruthy();
  });

  describe('findAllAccount', () => {

    it('should return an Observable', () => {
      expect(accountService.findAllAccount()).toEqual(jasmine.any(Observable));
    });

    it('request should be a GET', () => {
      accountService.findAllAccount().subscribe();
      const req = httpTestingController.expectOne(`http://localhost:8080/account/all`);
      expect(req.request.method).toBe('GET');
    });

  });

  describe('findById', () => {
    it('should return an Observable', () => {
      expect(accountService.findById(1)).toEqual(jasmine.any(Observable));
    });

    it('request should be a GET', () => {
      accountService.findById(1).subscribe();
      const req = httpTestingController.expectOne(`http://localhost:8080/account?id=1`);
      expect(req.request.method).toBe('GET');
    });

  });

  describe('doWithdraw', () => {

    let operationForm: OperationForm = {
      amount: 100.00,
      account: {
        id: 2,
        balance: 1000.00,
        firstName: 'Test',
        lastName: 'Test',
        operations: []
      }
    }

    it('should return an Observable', () => {
      expect(accountService.doWithdraw(operationForm)).toEqual(jasmine.any(Observable));
    });

    it('request should be a GET', () => {
      accountService.doWithdraw(operationForm).subscribe();
      const req = httpTestingController.expectOne(`http://localhost:8080/account/operation/withdraw`);
      expect(req.request.method).toBe('POST');
    });

  });

  describe('doDeposit', () => {

    let operationForm: OperationForm = {
      amount: 100.00,
      account: {
        id: 2,
        balance: 1000.00,
        firstName: 'Test',
        lastName: 'Test',
        operations: []
      }
    }

    it('should return an Observable', () => {
      expect(accountService.doDeposit(operationForm)).toEqual(jasmine.any(Observable));
    });

    it('request should be a GET', () => {
      accountService.doDeposit(operationForm).subscribe();
      const req = httpTestingController.expectOne(`http://localhost:8080/account/operation/deposit`);
      expect(req.request.method).toBe('POST');
    });

  });

});
