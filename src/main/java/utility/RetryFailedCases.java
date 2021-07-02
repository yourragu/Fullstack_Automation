package utility;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;



public class RetryFailedCases implements IAnnotationTransformer{
	
	  public void transform(
		      ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		  System.err.println("Retrying...."+testMethod.getName());
		  annotation.setRetryAnalyzer(utility.RetryLogic.class);
		 
		  }


}
