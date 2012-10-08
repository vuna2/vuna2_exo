package org.exoplatform.selenium;

import static org.exoplatform.selenium.TestLogger.debug;
import static org.exoplatform.selenium.TestLogger.info;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class TestBase {
  protected static WebDriver driver;
  protected static Actions actions ;
  protected static String baseUrl;
  protected static int DEFAULT_TIMEOUT = 30000; //milliseconds = 30 seconds
  protected static int WAIT_INTERVAL = 500; //milliseconds  
  public static int loopCount = 0;	
  protected static boolean ieFlag;	 
  protected static boolean chromeFlag;
//  public static final String AJAX_LOADING_MASK = "//div[@id='AjaxLoadingMask']";
  public static final String DEFAULT_BASEURL="http://localhost:8080";

////////////////////////////
/////////////////////////////////////////////
////////////////////////////////////////////////////

  public static void initSeleniumTest(){
	  String browser = System.getProperty("browser");
	  if("chrome".equals(browser)){
		  driver = new ChromeDriver();
		  chromeFlag = true;
	  } else if ("iexplorer".equals(browser)){
		  driver = new InternetExplorerDriver();
		  ieFlag = true;
	  } else {
		  driver = new FirefoxDriver();
	  }
	  baseUrl = System.getProperty("baseUrl");
	  if (baseUrl==null) baseUrl = DEFAULT_BASEURL;
	  
  }
  
  public static WebElement getElement(Object locator) {
	  By by = locator instanceof By ? (By)locator : By.xpath(locator.toString());
	  WebElement elem = null;
	  try {
		  elem = driver.findElement(by);
	  } catch (NoSuchElementException e) {
	  }
	  return elem;
  }
  
  public static boolean isElementPresent(Object locator) {
    return getElement(locator) != null;
  }

  public static boolean isElementNotPresent(Object locator) {
    return !isElementPresent(locator);
  }

  public static void waitForElementPresent(Object locator, int... timeInMillis) {
    WebElement elem = null;
    int timeout = timeInMillis.length>0 ? timeInMillis[0] : DEFAULT_TIMEOUT;
    for (int tick = 0; tick < timeout/WAIT_INTERVAL; tick++) {
      elem = getElement(locator);
      if (null != elem) return;
      pause(WAIT_INTERVAL);
    }
    assert false: ("Timeout after " + timeout + "ms waiting for element present: " + locator);
  }

/*
 * @opPram[0]: timeout
 * @opPram[1]: 0,1
 * 				0: No Asert
 * 				1: Assert
 */
  public static WebElement waitForElementNotPresent(Object locator, int... opParams) {
    WebElement elem = null;
    int timeout = opParams.length > 0 ? opParams[0] : DEFAULT_TIMEOUT;
    int isAssert = opParams.length > 1 ? opParams[1]: 1;
    
    for (int tick = 0; tick < timeout/WAIT_INTERVAL; tick++) {
      elem = getElement(locator);
      if (null == elem) return null;
      pause(WAIT_INTERVAL);
    }
    
   	if (isAssert == 1)
   		assert false: ("Timeout after " + timeout + "ms waiting for element not present: " + locator);
   	return elem;
  }
//
//  public WebElement waitForAndGetElement(Object locator, int... timeInMillis) {
//    WebElement elem = null;
//    int timeout = timeInMillis.length>0 ? timeInMillis[0] : DEFAULT_TIMEOUT;
//    for (int tick = 0; tick < timeout/WAIT_INTERVAL; tick++) {
//      elem = getElement(locator);
//      if (null != elem) return elem;
//      pause(WAIT_INTERVAL);
//    }
//    debug("Timeout after " + timeout + "ms waiting for element " + locator);
//    return elem;
//  }
  
  /*
   * @opPram[0]: timeout
   * @opPram[1]: 0,1
   * 				0: No Asert
   * 				1: Assert
   */
  public static WebElement waitForAndGetElement(Object locator, int... opParams) {
		WebElement elem = null;
		int timeout = opParams.length > 0 ? opParams[0] : DEFAULT_TIMEOUT;
	    int isAssert = opParams.length > 1 ? opParams[1]: 1;
	    
		for (int tick = 0; tick < timeout/WAIT_INTERVAL; tick++) {
			elem = getElement(locator);
			if ((null != elem) && elem.isDisplayed()) return elem;
			pause(WAIT_INTERVAL);
		}
		if (isAssert == 1)		
			assert false: ("Timeout after " + timeout + "ms waiting for element present: " + locator);
		return null;
	}
  
  public static boolean isTextPresent(String text) {
	  pause(500);
	  String allVisibleTexts = getText(By.xpath("//body"));
	  return allVisibleTexts.contains(text);
  }

  public static String getText(Object locator) {
	  WebElement element = null;
	  try {
		  element = waitForAndGetElement(locator);
		  return element.getText();
	  } catch (StaleElementReferenceException e) {
		  debug("StaleElementReferenceException, Retrying... :" + loopCount + "time(s)");
		  checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
		  pause(WAIT_INTERVAL);
		  return getText(locator);
	  } finally {
		  loopCount = 0;
	  }
  }

  public static List<WebElement> getElements(String xpath) {
      try {
          return driver.findElements(By.xpath(xpath));
      } catch (StaleElementReferenceException e) {
    	  checkCycling(e, 5);
          pause(1000);
          return getElements(xpath);
      } finally {
          loopCount = 0;
      }
  }

	//copy and paste node
	  public void copyAndPasteNode(By source, By target){
		  goToNode(source);
		  rightClickOnElement(source);
		  pause(500);
		  click(By.xpath("//a[contains(text(),'Copy')]"));
		  goToNode(target);
		  rightClickOnElement(target);
		  pause(500);
		  click(By.xpath("//a[contains(text(),'Paste')]"));
		  pause(1000);
	  }
  
  public static boolean isTextNotPresent(String text) {
	  return !isTextPresent(text);
  }

  public static String getTextFromAlert() {
	  try {
		  Alert alert = driver.switchTo().alert();
		  return alert.getText();
	  } catch (NoAlertPresentException e) {
		  return "";
	  }
  }

	
  public static void acceptAlert() {
	  try {
		  Alert alert = driver.switchTo().alert();
		  alert.accept();
		  switchToParentWindow();
	  } catch (NoAlertPresentException e) {
	  }
  }

  public static void pause(long timeInMillis) {
	  try {
		  Thread.sleep(timeInMillis);
	  } catch (InterruptedException e) {
		  e.printStackTrace();
	  }
  }

  public static void dragAndDropToObject(Object sourceLocator, Object targetLocator) {
	  info("--Drag and drop to object--");
	  Actions action = new Actions(driver);
	  try {
		  WebElement source = waitForAndGetElement(sourceLocator);
		  WebElement target = waitForAndGetElement(targetLocator);

		  action.dragAndDrop(source, target).build().perform();
	  } catch (StaleElementReferenceException e) {
		  checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
          pause(WAIT_INTERVAL);
          dragAndDropToObject(sourceLocator, targetLocator);
      } finally {
          loopCount = 0;
	  } 
  }

  public static void click(Object locator) {
	  try {
		  WebElement element = waitForAndGetElement(locator);
		  actions.click(element).perform();
	  } catch (StaleElementReferenceException e) {
		  checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
		  pause(WAIT_INTERVAL);
		  click(locator);
	  } catch (ElementNotVisibleException e) {
          checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
          pause(WAIT_INTERVAL);
          click(locator);
	  } finally {
		  loopCount = 0;
	  }
  }

  public static void clearCache(){
	  Actions actionObject = new Actions(driver);
	  try{

		  actionObject.sendKeys(Keys.CONTROL).sendKeys(Keys.F5).build().perform();
	  } catch(WebDriverException e){	
		  debug("Retrying clear cache...");
		  actionObject.sendKeys(Keys.CONTROL).sendKeys(Keys.F5).build().perform();
	  }
  }

  //Use this function to verify if a checkbox is checked (using when creating a portal/publicMode)
  public static void check(Object locator) {
	  try {
		  WebElement element = waitForAndGetElement(locator);

		  if (!element.isSelected()) {
			  actions.click(element).perform();
		  } else {
			  Assert.fail("Element " + locator + " is already checked.");
		  }
	  } catch (StaleElementReferenceException e) {
		  debug("StaleElementReferenceException, Retrying... :" + loopCount + "time(s)");
		  checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
		  pause(WAIT_INTERVAL);
		  check(locator);
	  } finally {
		  loopCount = 0;
	  }
  }

  public static String getValue(Object locator) {
	  try {
		  return waitForAndGetElement(locator).getAttribute("value");
	  } catch (StaleElementReferenceException e) {
		  debug("StaleElementReferenceException, Retrying... :" + loopCount + "time(s)");
		  checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
		  pause(WAIT_INTERVAL);
		  return getValue(locator);
	  } finally {
		  loopCount = 0;
	  }
  }

  public static void mouseOver(Object locator, boolean safeToSERE) {
	  if (safeToSERE) {
		  try {
			  WebElement element = waitForAndGetElement(locator);
			  actions.moveToElement(element).perform();
		  } catch (StaleElementReferenceException e) {
			  debug("StaleElementReferenceException, Retrying... :" + loopCount + "time(s)");
			  checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
			  pause(WAIT_INTERVAL);
			  mouseOver(locator, safeToSERE);
		  } finally {
			  loopCount = 0;
		  }
	  } else {
		  WebElement element = waitForAndGetElement(locator);
		  actions.moveToElement(element).perform();
	  }
  }

  public static void mouseOverAndClick(Object locator) {
	  WebElement element;
	  if (ieFlag) {
		  element = getElement(locator);
	  } else {
		  element = waitForAndGetElement(locator);
	  }
	  actions.moveToElement(element).click(element).build().perform();
  }
  
  public static void waitForTextPresent(String text) {
	  for (int second = 0;; second++) {
		  if (second >= DEFAULT_TIMEOUT/WAIT_INTERVAL) {
			  Assert.fail("Timeout at waitForTextPresent: " + text);
		  }
		  if (isTextPresent(text)) {
			  break;
		  }
		  pause(WAIT_INTERVAL);
	  }
  }

  public static void waitForTextNotPresent(String text) {
	  for (int second = 0;; second++) {
		  if (second >= DEFAULT_TIMEOUT/WAIT_INTERVAL) {
			  Assert.fail("Timeout at waitForTextNotPresent: " + text);
		  }
		  if (isTextNotPresent(text)) {
			  break;
		  }
		  pause(WAIT_INTERVAL);
	  }
  }

  public static void waitForMessage(String message) {
	  //info("--Verify message: " + message);
	  pause(500);
	  waitForTextPresent(message);
  }

  public static void type(Object locator, String value, boolean validate) {
	  try {
		  for (int second = 0;; second++) {
			  if (second >= DEFAULT_TIMEOUT/WAIT_INTERVAL) {
				  Assert.fail("Timeout at type: " + value + " into " + locator);
			  }
			  WebElement element = waitForAndGetElement(locator);
			  if (validate) element.clear();
			  element.click();
			  element.sendKeys(value);
			  if (!validate || value.equals(getValue(locator))) {
				  break;
			  }
			  pause(WAIT_INTERVAL);
		  }
	  } catch (StaleElementReferenceException e) {
		  debug("StaleElementReferenceException, Retrying...");
		  checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
		  pause(WAIT_INTERVAL);
		  type(locator, value, validate);
	  }  catch (ElementNotVisibleException e) {
          debug("ElementNotVisibleException, Retrying...");
          checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
          pause(WAIT_INTERVAL);
          type(locator, value, validate);
	  } finally {
		  loopCount = 0;
	  }
  }

  // Select option from combo box
  public static void select(Object locator, String option) {
	  try {
		  for (int second = 0;; second++) {
			  if (second >= DEFAULT_TIMEOUT/WAIT_INTERVAL) {
				  Assert.fail("Timeout at select: " + option + " into " + locator);
			  }
			  Select select = new Select(waitForAndGetElement(locator));
			  select.selectByVisibleText(option);
			  if (option.equals(select.getFirstSelectedOption().getText())) {
				  break;
			  }
			  pause(WAIT_INTERVAL);
		  }
	  } catch (StaleElementReferenceException e) {
		  debug("StaleElementReferenceException, Retrying... :" + loopCount + "time(s)");
		  checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
		  pause(WAIT_INTERVAL);
		  select(locator, option);
	  } finally {
		  loopCount = 0;
	  }
  }

  //uncheck a checked-box
  public static void uncheck(Object locator) {
	  try {
		  WebElement element = waitForAndGetElement(locator);

		  if (element.isSelected()) {
			  actions.click(element).perform();
		  } else {
			  Assert.fail("Element " + locator + " is already unchecked.");
		  }
	  } catch (StaleElementReferenceException e) {
		  checkCycling(e, 5);
		  pause(1000);
		  uncheck(locator);
	  } finally {
		  loopCount = 0;
	  }
  }
  public static void rightClickOnElement(Object locator) {
	  pause(500);
	  try {
		  WebElement element = waitForAndGetElement(locator);
		  actions.contextClick(element).perform();
	  } catch (StaleElementReferenceException e) {
		  checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
		  pause(WAIT_INTERVAL);
		  rightClickOnElement(locator);
	  } catch (ElementNotVisibleException e) {
		  checkCycling(e, DEFAULT_TIMEOUT/WAIT_INTERVAL);
		  pause(WAIT_INTERVAL);
		  click(locator);
	  } finally {
		  loopCount = 0;
	  }
  }

  //doubleClickOnElement
  public static void doubleClickOnElement(String locator) {
	  try {
		  WebElement element = waitForAndGetElement(locator);
		  actions.doubleClick(element).perform();
	  } catch (StaleElementReferenceException e) {
		  checkCycling(e, 5);
		  pause(1000);
		  doubleClickOnElement(locator);
	  } finally {
		  loopCount = 0;
	  }
  }

  public static void waitForConfirmation(String confirmationText) {
	  String message = getTextFromAlert();

	  //log("confirmation: " + message);

	  if (message.isEmpty()) {
		  if (loopCount > 5) {
			  Assert.fail("Message is empty");
		  }
		  pause(500);
		  loopCount++;
		  waitForConfirmation(confirmationText);
		  return;
	  }

	  for (int second = 0;; second++) {
		  if (second >= DEFAULT_TIMEOUT) {
			  Assert.fail("Timeout at waitForConfirmation: " + confirmationText);
		  }
		  if (message.equals(confirmationText)) {
			  break;
		  }
		  pause(100);
	  }
	  Alert alert = driver.switchTo().alert();
	  alert.accept();
	  pause(500);
  }

  //This function return absolute path from relative path
  public static String getAbsoluteFilePath(String relativeFilePath){
	  String curDir = System.getProperty("user.dir");
	  String absolutePath = curDir + "/src/main/resources/" + relativeFilePath;
	  return absolutePath;
  }
  public static void checkCycling(Exception e, int loopCountAllowed) {
      debug("Exception:" + e.getClass().getName());
      if (loopCount > loopCountAllowed) {
          Assert.fail("Cycled: " + e.getMessage());
      } 
      loopCount++;
  }
  
	/*---- Auxiliary functions ----*/
	public static void captureScreen(String fileName){
		String path;
		String relativeFilePath;
		try {
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			relativeFilePath = "TestData/" + fileName;
			path = getAbsoluteFilePath(relativeFilePath);
			FileUtils.copyFile(scrFile, new File(path));
		} catch (IOException e) {
			path = "Failed to capture screenshot: " + e.getMessage();
		}
	}
	
	//function switch to parent windows
	public static void switchToParentWindow (){
		try
		{
			Set<String> availableWindows = driver.getWindowHandles();
			String WindowIdParent= null;
			int counter = 1;
			for (String windowId : availableWindows) {
				if (counter == 1){
					WindowIdParent = windowId;
				}
				counter++;
			}
			driver.switchTo().window(WindowIdParent);
			pause(1000);
		}
		catch (WebDriverException e)
		{
			e.printStackTrace();
		}
	}
	//Set edit permissions for portal
	public static void setEditPermissions(String groupId, String membership) {
		String membershipToSelect = ELEMENT_SELECT_EDIT_MEMBERSHIP_ITEM.replace("${membership}", membership);
		//String selectedGroup = ELEMENT_SELECTED_EDIT_PERM_GROUP.replace("${groupId}", groupId.replace(" ", "-").toLowerCase());
		String selectedMembership = ELEMENT_SELECTED_EDIT_PERMISSION_MEMBERSHIP.replace("${membership}", membership);

		info("--Setting edit permission to " + groupId + ", " + membership + "--");
		String[] groups = groupId.split("/");
		click(ELEMENT_SELECT_PERMISSION_BUTTON);
		pause(500);
		waitForTextPresent("Permission Selector");
		for (String group : groups) {
			String groupToSelect = ELEMENT_SELECT_EDIT_GROUP_ITEM.replace("${group}", group);
			click(groupToSelect);
		}
		click(membershipToSelect);
		waitForTextNotPresent("Permission Selector");
		//waitForAndGetElement(selectedGroup);
		waitForAndGetElement(selectedMembership);
	}
}
