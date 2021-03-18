ALTER TABLE bank.account
ADD CONSTRAINT uq_accountnumber
UNIQUE(accountnumber);

ALTER TABLE bank.transactionlog
ADD CONSTRAINT fk_transactionlog_destaccount
   FOREIGN KEY(destinationaccount) 
      REFERENCES bank.account(accountnumber);

      