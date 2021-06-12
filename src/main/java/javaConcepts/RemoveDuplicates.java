package javaConcepts;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RemoveDuplicates {

	public static void main(String[] args) {
		
		String x = "Kongu Engineering College";
		
		Map<Character, Integer> val = new HashMap<Character, Integer>();
		
		char[] charArray = x.toCharArray();		
		System.out.println(val.toString());
		
		for (char c : charArray) {
			if(val.containsValue(c)) {
				System.out.println(val.put(c,val.get(c)+1));
				
			}
			else {
				System.out.println(val.put(c,val.get(c)));
			}
			
		}
		
		Set<Character> keys = val.keySet();
		for(Character ch:keys){
			if(val.get(ch) > 1){
				System.out.println(ch+"--->"+val.get(ch));
			}
		}
		

	}

}
