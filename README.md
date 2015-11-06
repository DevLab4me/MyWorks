# MyWorks
CashMachine - cashmachine simulator with the following options:
1) Info: Showing the information about current balance of the client (all currency accounts);
2) Deposit: choosing the currency, nominal and quantity of nominals (information will be stored during the session);
3) Withdraw: choosing the currency and amount (nominals and quantity of them will be deducted from the account (greedy algorithm is used for summing up the amount requested from available nominals and their quantity in the CashMachine));
4) Exit: there will be shown a confirmation question before the actual session termination; 
5) Customer has an option of terminating the CashMachine working session at anytime by typing the word "exit".

RelaxTime - program that generates a crypted password(that was periously set up) for a game session access (only on allowed days):
1) works through the command line with two args (-p - play/-e - end; uncrypted password);
2) logs password for the game (after ending game session (-e) line with password is deleted);
3) logs date of a game session start/end and the time spent on playing;
