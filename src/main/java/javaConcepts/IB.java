package javaConcepts;

public class IB extends SBI implements RBI {


	@Override
	public void overDraftAcount() {
		System.out.println("Indian bank overdraft account");
		
	}

	@Override
	public void loanAmount() {
		// TODO Auto-generated method stub
		System.out.println("Indian bank loan account");
	}

	@Override
	public void minimalBalance() {
		// TODO Auto-generated method stub
		System.out.println("Indian bank minimal Balance");
		
	}
	
	@Override
	public void agriloan() {
		System.out.println("SBI agri loan");
		
	}
	
	public static void main(String[] args) {

		IB ib = new IB();
		
		ib.loanAmount();
		ib.minimalBalance();
		ib.overDraftAcount();
		ib.agriloan();
		ib.jewelLoan();

												
		
	}

	

}
