package org.exoplatform.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class BaseTestSuite {
  protected WebDriver driver;
  protected String baseUrl;
  protected StringBuffer verificationErrors = new StringBuffer();
  
  public static void log(String msg) {
    //System.out.println(String.format("%-80s%s", "@Test-" + this.getClass().getName(), msg).replace(' ', '.'));
    StackTraceElement callerClass = Thread.currentThread().getStackTrace()[2];
    System.out.println(String.format("%-100s%s", "[" + callerClass.getClassName() + "." + callerClass.getMethodName() + "]", msg).replaceAll("  ", ".."));
  }

  public boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  public void waitForElementPresent(By by) throws Exception {
    for (int second = 0;; second++) {
      if (second >= 60) Assert.fail("timeout");
      try { if (isElementPresent(by)) break; } catch (Exception e) {}
      Thread.sleep(1000);
    }
  }
    
}
