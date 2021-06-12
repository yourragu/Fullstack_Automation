package javaConcepts;

import java.util.ArrayList;
import java.util.List;

interface GetCardNumber {
	public void visa(int card);
}

public class LamdaLearnig {

	public static void main(String[] args) {
		/*GetCardNumber card = new GetCardNumber() {
			public void visa(int cardNumber) {
				System.out.println("Card Number is :" + cardNumber);
			}
		};		
		card.visa(234234);*/
		
		
		GetCardNumber card = (x)-> {	System.out.println("Card Number is :" + x);  };		
		card.visa(234234);
		
		LamdaLearnig lb = new LamdaLearnig();
		lb.listOfElements();		

	}
	
	
	public void listOfElements()
	{		
		List<String> li = new ArrayList<String>();
		
		li.add("Arun");
		li.add("Vishnu");
		li.add("Vishal");
		li.add("Raju");
		li.add("Ramu");
		li.add("Kishore");
		
		li.forEach((eachName)->{System.out.println(eachName);
		});
		
		
	}

}
