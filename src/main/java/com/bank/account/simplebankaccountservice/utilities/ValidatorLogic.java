package com.bank.account.simplebankaccountservice.utilities;

import com.bank.account.simplebankaccountservice.model.AccountBalanceRequest;
import com.bank.account.simplebankaccountservice.model.AccountCreateRequest;
import com.bank.account.simplebankaccountservice.model.AccountDepositRequest;
import com.bank.account.simplebankaccountservice.model.AccountWithdrawalRequest;

public class ValidatorLogic {
	public static boolean validationAccountCreateRequest(AccountCreateRequest accountCreateRequest) {
		/** AccountCreateRequest validation logic here */
		if (accountCreateRequest.getPersonalNumber() == null || "".equals(accountCreateRequest.getPersonalNumber())) {
			return true;
		}
		/**Need to validate other require fields here*/
		return false;
	}

	public static boolean validationAccountBalanceRequest(AccountBalanceRequest accountBalanceRequest) {
		if (accountBalanceRequest.getAccountNumber() == null || "".equals(accountBalanceRequest.getAccountNumber())) {
			return true;
		}
		if (("" + accountBalanceRequest.getAccountNumber()).length() < 9 || ("" + accountBalanceRequest.getAccountNumber()).length() > 9) {
			return true;
		}
		if ("".equals(accountBalanceRequest.getCurrency())) {
			return true;
		}		
		/**Need to validatt currency code here**/
		
		return false;
	}

	public static boolean validationAccountDepositRequest(AccountDepositRequest accountDepositRequest) {
		if (accountDepositRequest.getAccountNumber() == null || "".equals(accountDepositRequest.getAccountNumber())) {
			return true;
		}
		if (("" + accountDepositRequest.getAccountNumber()).length() < 9 || ("" + accountDepositRequest.getAccountNumber()).length() > 9) {
			return true;
		}
		if (!CommonUtils.getInstance().isNumber(accountDepositRequest.getAmount())) {
			return true;
		}
		return false;
	}

	public static boolean validationAccountWithdrawalRequest(AccountWithdrawalRequest accountWithdrawalRequest) {
		if (accountWithdrawalRequest.getAccountNumber() == null || "".equals(accountWithdrawalRequest.getAccountNumber())) {
			return true;
		}
		if (("" + accountWithdrawalRequest.getAccountNumber()).length() < 9 || ("" + accountWithdrawalRequest.getAccountNumber()).length() > 9) {
			return true;
		}
		if (!CommonUtils.getInstance().isNumber(accountWithdrawalRequest.getAmount())) {
			return true;
		}
		return false;
	}
}
