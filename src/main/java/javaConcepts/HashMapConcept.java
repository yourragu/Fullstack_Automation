package javaConcepts;

import java.util.HashMap;
import java.util.Map;

import org.apache.tools.ant.types.CommandlineJava.SysProperties;

public class HashMapConcept {

	public static void main(String[] args) {

		HashMap<Character, Integer> mapValue = new HashMap<Character, Integer>();

		String val = "sardhar vallabhai patel";
		char[] charArr = val.toCharArray();
		
		int x = charArr.length;
		for(int i=0;i<charArr.length;i++)
		{
			System.out.println(charArr[i]);
		}

		for (char c : charArr) {
			if (mapValue.containsKey(c)) {

				mapValue.put(c, mapValue.get(c) + 1);
			} else {
				mapValue.put(c, 1);
			}

		}

		for (Map.Entry entry : mapValue.entrySet()) {
			System.out.println(entry.getKey() + " => " + entry.getValue());
		}

	}

}
