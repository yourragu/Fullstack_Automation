package utility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryLogic implements IRetryAnalyzer {

	int maxtry = 2;
	int count = 1;

	public boolean retry(ITestResult result) {
		if (!result.isSuccess() && count < maxtry) {
			count++;
			return true;
		}

		return false;
	}

}
