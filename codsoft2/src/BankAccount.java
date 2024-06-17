public class BankAccount {
        private double balance;

        public BankAccount(double initialBalance) {
            if (initialBalance >= 0) {
                this.balance = initialBalance;
            } else {
                this.balance = 0;
                System.out.println("Initial balance invalid. Setting balance to 0.");
            }
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            if (balance >= 0) {
                this.balance = balance;
            } else {
                System.out.println("Balance cannot be negative. Operation ignored.");
            }
        }


}
