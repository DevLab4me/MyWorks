# MyWorks
#### CashMachine 
- cashmachine simulator with the following options:
1. Info: Showing the information about current balance of the client (all currency accounts);
2. Deposit: choosing the currency, nominal and quantity of nominals (information will be stored during the session);
3. Withdraw: choosing the currency and amount (nominals and quantity of them will be deducted from the account (greedy algorithm is used for summing up the amount requested from available nominals and their quantity in the CashMachine));
4. Exit: there will be shown a confirmation question before the actual session termination; 
   Customer has an option of terminating the CashMachine working session at anytime by typing the word "exit".


#### RelaxTime 
- program that generates a crypted password that was periously set up for a game session access. 
Works through the command line. Options:
1. Start Session - generates a crypted password (from a normal password) that is set up for an account access (only on allowed days) and logs into file - time of the game session start;
2. End Session- logs an end time of a game session and counts a total amount of time spent on current session (command is not executing in case if the session wasn't initialized by "1" option first);
3. Exit - exiting the application.


#### WallOfBricks
- program verifies if a wall of a specified configuration can be constructed from some set of bricks.
Works from both, console input and file data inputStream. The format for input data is as follows:
1. width and height of wall's shape matrix - two positive integers (H and W separated by space on their own line);
2. wall's shape matrix - H strings each of length W, formed of '1' - brick and '0' - gap in the wall (every string on its own line);
3. quantity of types of bricks - the positive integer C;
4. list of bricks - C lines each containing three positive integers separated by space - width of brick, height of brick and count of such bricks in the set.
