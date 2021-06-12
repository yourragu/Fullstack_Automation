package javaConcepts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx {

	public void patternCompile() {
		String email = "your.ragu@gmail.com";
		String pat = "[a-zA-Z.]+[a-zA-Z]+@[a-zA-Z.]+{2,3}";
		Pattern compile = Pattern.compile(pat);
		Matcher matcher = compile.matcher(email);
		System.out.println(matcher.matches());

	}

	public void extractText() {
		String text = "Cognizant is having more than 3L employees and 2500 projects are running actively in cognizant";
		String[] pat = text.split("\\s");

		for (String x : pat) {

			System.out.println(x);

		}

	}

	public static void main(String[] args) {

		RegEx exp = new RegEx();
		exp.patternCompile();
		System.out.println("************************************************************");
		exp.extractText();

	}

}
