package frontend;
import javax.swing.*;
import java.awt.GridLayout;

/**
 * Represents the interface the user will use when running
 * the application
 * @author clegg
 *
 */
public class Menu {
	public Menu(){
		JFrame jf = initializeJF();
		addButtons(jf);
		jf.setVisible(true);
	}
	
	/**
	 * Creates an empty JFrame with a GridLayout for the Buttons
	 * @return output The JFrame menu to be displayed
	 */
	private JFrame initializeJF(){
		JFrame output = new JFrame("Bank Management System");
		output.setSize(1000,500);
		output.setLayout(new GridLayout(4,4));
		output.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return output;
	}
	
	//TODO: Add action listeners to buttons 
	//      that call backend functions
	/**
	 * Adds buttons for the backend functions to the JFrame
	 * @param jf The JFrame to add the buttons to
	 */
	private void addButtons(JFrame jf){
		JButton belowMinBalance = new JButton("View Savings Below Min Balance");
		jf.add(belowMinBalance);
		
		JButton allBalances = new JButton("View All Balances");
		jf.add(allBalances);
		
		JButton allEmpsWithAccts = new JButton("List Employees with Accoutns");
		jf.add(allEmpsWithAccts);
		
		JButton avgSalary = new JButton("View Avg Salary");
		jf.add(avgSalary);
		
		JButton belowAvgSalary = new JButton("View Below Avg Salary");
		jf.add(belowAvgSalary);
		
		JButton loansWithDebt = new JButton("View Loans that Owe X");
		jf.add(loansWithDebt);
		
		JButton lessThanMinPayment = new JButton("View Payments Below Min");
		jf.add(lessThanMinPayment);
		
		JButton purchaseAboveLimit = new JButton("View Purchaces Above Limit");
		jf.add(purchaseAboveLimit);
		
		JButton loansEndingInYear = new JButton("View Loans Ending Soon");
		jf.add(loansEndingInYear);
		
		JButton profitFromCreditInterest = new JButton("View Credit Card Profit");
		jf.add(profitFromCreditInterest);
		
		JButton savingsInterestExpense = new JButton("View Savings Interest Expense");
		jf.add(savingsInterestExpense);
		
		JButton employeeExpense = new JButton("View Payroll Expense");
		jf.add(employeeExpense);
		
		JButton viewDefaultRisks = new JButton("View Potential Loan Defaults");
		jf.add(viewDefaultRisks);
		
		JButton calcLoanProfit = new JButton("Calculate Loan Profit");
		jf.add(calcLoanProfit);
		
		JButton hire = new JButton("Hire Employee");
		jf.add(hire);
		
		JButton fire = new JButton("Fire Employee");
		jf.add(fire);
	}
}
