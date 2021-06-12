package com.analyzer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

public class RetryFailedTestcase implements IRetryAnalyzer {
	int increment = 1;
	int count = 3;

	public boolean retry(ITestResult result) {

		if (increment <= count) {
			increment++;
			return true;
		}
		return false;
	}


}
