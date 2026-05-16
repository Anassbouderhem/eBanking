import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Customer } from '../model/customer.model';
import {  AccountService } from '../services/accountService';
import { Observable } from 'rxjs';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-customer-accounts',
  imports: [CommonModule],
  templateUrl: './customer-accounts.html',
  styleUrl: './customer-accounts.css',
})
export class CustomerAccounts implements OnInit {
  customerId!: string;
  customer!: Customer;
  accounts$!: Observable<Array<any>>;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private accountService: AccountService
  ) {}

  ngOnInit(): void {
    this.customerId = this.route.snapshot.params['id'];
    this.customer = history.state['customer'];
    this.accounts$ = this.accountService.getCustomerAccounts(this.customerId);
  }
}
