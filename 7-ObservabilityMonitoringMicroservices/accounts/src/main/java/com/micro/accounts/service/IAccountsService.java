package com.micro.accounts.service;

import com.micro.accounts.dto.CustomerDto;

public interface IAccountsService {
    /**
     * This method creates a new account for a given customer.
     *
     * @param customerDto This parameter holds the customer details to be used
     *                    for creating a new account.
     */
    void createAccount(CustomerDto customerDto);

    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return Accounts Details based on a given mobileNumber
     */
    CustomerDto fetchAccount(String mobileNumber);


    /**
     *
     * @param customerDto - CustomerDto Object
     * @return boolean indicating if the update of Account details is successful or not
     */
    boolean updateAccount(CustomerDto customerDto);

    /**
     * Deletes an account for the given mobile number.
     *
     * @param mobileNumber - mobile number of the account to be deleted
     * @return boolean indicating if the deletion of account is successful or not
     */
    boolean deleteAccount(String mobileNumber);
}
