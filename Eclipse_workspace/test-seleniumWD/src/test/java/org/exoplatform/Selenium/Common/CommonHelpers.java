package org.exoplatform.Selenium.Common;

import java.util.ArrayList;
import java.util.List;

import org.exoplatform.Selenium.SetUpEnvironement;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.Alert;
//import org.exoplatform.Selenium.UserManagementHelpers;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class CommonHelpers extends SetUpEnvironement{
	 private static String portalUrl;
	 
	 public static final String ELEMENT_SIGN_IN_LINK = "//b[contains(text(),'Sign in')]";
	 public static final String ELEMENT_INPUT_USERNAME = "//input[@name='username']";
	 public static final String ELEMENT_INPUT_PASSWORD = "//input[@name='password']";
	 public static final String ELEMENT_SIGN_IN_CONFIRM_BUTTON = "//form[@id='UIPortalComponentLogin']//div[@class='UIAction']/*";
	 public static final String ELEMENT_SEARCH_ICON_REGISTER = "//img[@class='SearchIcon']";
	 public static final String ELEMENT_SAVE_BUTTON = "//a[text()='Save']";
	 public static final String ELEMENT_MESSAGE_DIALOG_CLOSE_ICON = "//div[contains(@class, 'UIPopupWindow') and contains(@style, 'visibility: visible')]//span[text()='Messages']/..//a[@class='CloseButton']";
	 
	 public static final String ELEMENT_INPUT_CONFIRM_PASSWORD = "//input[@id='Confirmpassword']";
	 public static final String ELEMENT_INPUT_NEW_PASSWORD = "//input[@id='newPassword']";
	 public static final String ELEMENT_INPUT_NEW_CONFIRM_PASSWORD = "//input[@id='confirmPassword']";
	 public static final String ELEMENT_INPUT_FIRSTNAME = "//input[@id='firstName']";
	 public static final String ELEMENT_INPUT_LASTNAME = "//input[@id='lastName']";
	 public static final String ELEMENT_INPUT_EMAIL = "//input[@id='email']";   
	 public static final String ELEMENT_LINK_SetUp ="//img[@alt='Setup']";
	 public static final String ELEMENT_LINK_Users ="//a[text()='Users']";
	 public static final String ELEMENT_LINK_AddUsers="//a[text()='Add Users']";
	 
	 public static final String ELEMENT_PAGINATOR_PAGE_LINK = "//a[contains(@class, 'Number') and text()='${number}']";
	 public static final String ELEMENT_PAGINATOR_TOTAL_NUMBER = "//a[@class='PagesTotalNumber']";
	 public static final String ELEMENT_PAGINATOR_NEXT_ICON = "//a[@class='Icon NextPageIcon']";
	 public static final String ELEMENT_PAGINATOR_SELECTED_PAGE = "//a[@class='Number PageSelected' and text()='${number}']";
	   
	 public static final String ELEMENT_LINK_PORTAL_TOP_CONTAINER = "//ul[contains (@id, 'PortalNavigationContainer')]/..";
	 public static final String ELEMENT_SIGN_OUT_LINK = "//a[@class='LogoutIcon']";
	 
	 private static int seconds = 0;
	 public static int loopCount = 0;	 
	 public static void checkCycling(Exception e, int loopCountAllowed) {
	        System.err.println(e.getClass());
	        if (loopCount > loopCountAllowed) {
	            Assert.fail("Cycled: " + e.getMessage());
	        }
	        loopCount++;
	    }
	 
	 public static void open(String location) {
	        setUp();

	        System.out.println("--Opening location " + location + " --");
	        driver.get(location);
	    }
	 
	 public static void openPortal(String newPortalPath, boolean publicMode) {
	        setUp();
	        
	        String location = publicMode ? getPortalUrl() : getPortalUrl().concat("/private");

	        if (newPortalPath != null) {
	            location = location.replace(portalPath, newPortalPath);
	        }

	        open(location);
	 }
	 public static void openPortal(boolean publicMode) {
	        openPortal(null, publicMode);
	 }
	 
	 private static void setPortalUrl() {
	        portalUrl = (httpsFlag ? "https" : "http") + "://" + host + ":" + hostPort + portalPath;
	 }

	 public static String getPortalUrl() {
	        setUp();
	        
	        if (portalUrl == null) {
	            setPortalUrl();
	        }
	        return portalUrl;
	 }
	 
	 public static void pause(long timeInMillis) {
	        try {
	            Thread.sleep(timeInMillis);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	 }
	 
	 public static WebElement getElement(String xpath) {
	        pause(500);
	        return driver.findElement(By.xpath(xpath));
	 }
	    
	 public static WebElement waitForAndGetElement(String xpath) {
	        WebElement element = null;
	        for (int second = 0;; second++) {
	            if (second >= timeoutSecInt) {
	                Assert.fail("Timeout at waitForElementPresent: " + xpath);
	            }
	            try {
	                element = driver.findElement(By.xpath(xpath));
	                boolean isLoadingDisplayed = false;
	                try {
	                    WebElement loading = driver.findElement(By.xpath("//div[@id='AjaxLoadingMask']"));
	                    isLoadingDisplayed = loading.isDisplayed();
	                } catch (Exception e) {
	                }
	                if (element.isDisplayed() && !isLoadingDisplayed) {
	                    break;
	                }
	            } catch (Exception e) {
	            }
	            pause(1000);
	        }
	        return element;
	    }

	 	public static void waitForElementNotPresent(String xpath) {
	        for (int second = 0;; second++) {
	            if (second >= timeoutSecInt) {
	                Assert.fail("Timeout at waitForElementNotPresent: " + xpath);
	            }
	            try {
	                driver.findElement(By.xpath(xpath));
	            } catch (NoSuchElementException e) {
	                break;
	            } catch (Exception e) {
	            }
	            pause(1000);
	        }
	 	}
	 
	 	public static String getText(String xpath) {
	        WebElement element = null;
	        try {
	            element = waitForAndGetElement(xpath);
	            return element.getText();
	        } catch (StaleElementReferenceException e) {
	            checkCycling(e, 5);
	            pause(1000);
	            return getText(xpath);
	        } finally {
	            loopCount = 0;
	        }
	    }
	 
	 	public static boolean isTextPresent(String text) {
	        pause(500);
	        String allVisibleTexts = getText("//body");

	        return allVisibleTexts.contains(text);
	    }

	    public static boolean isTextNotPresent(String text) {
	        return !isTextPresent(text);
	    }

	    public static void waitForTextPresent(String text) {
	        for (int second = 0;; second++) {
	            if (second >= timeoutSecInt) {
	                Assert.fail("Timeout at waitForTextPresent: " + text);
	            }
	            if (isTextPresent(text)) {
	                break;
	            }
	            pause(500);
	        }
	    }

	    public static void waitForTextNotPresent(String text) {
	        for (int second = 0;; second++) {
	            if (second >= timeoutSecInt) {
	                Assert.fail("Timeout at waitForTextNotPresent: " + text);
	            }
	            if (isTextNotPresent(text)) {
	                break;
	            }
	            pause(500);
	        }
	    }
	    public static String getValue(String xpath) {
	        try {
	            return waitForAndGetElement(xpath).getAttribute("value");
	        } catch (StaleElementReferenceException e) {
	            checkCycling(e, 5);
	            pause(1000);
	            return getValue(xpath);
	        } finally {
	            loopCount = 0;
	        }
	    }
	    public static void select(String xpath, String option) {
	        try {
	            for (int second = 0;; second++) {
	                if (second >= timeoutSecInt) {
	                    Assert.fail("Timeout at select: " + option + " into " + xpath);
	                }
	                Select select = new Select(waitForAndGetElement(xpath));
	                select.selectByVisibleText(option);
	                if (option.equals(select.getFirstSelectedOption().getText())) {
	                    break;
	                }
	                pause(1000);
	            }
	        } catch (StaleElementReferenceException e) {
	            checkCycling(e, 7);
	            pause(1000);
	            select(xpath, option);
	        } finally {
	            loopCount = 0;
	        }
	    }
	    public static void click(String xpath) {
	        try {
	            WebElement element = waitForAndGetElement(xpath);
	            actions.click(element).perform();
	        } catch (StaleElementReferenceException e) {
	            checkCycling(e, 5);
	            pause(1000);
	            click(xpath);
	        } finally {
	            loopCount = 0;
	        }
	    }

	    public static void type(String xpath, String value, boolean validate) {
	        try {
	            for (int second = 0;; second++) {
	                if (second >= timeoutSecInt) {
	                    Assert.fail("Timeout at type: " + value + " into " + xpath);
	                }
	                WebElement element = waitForAndGetElement(xpath);
	                element.clear();
	                element.click();
	                element.sendKeys(value);
	                if (!validate || value.equals(getValue(xpath))) {
	                    break;
	                }
	                pause(1000);
	            }
	        } catch (StaleElementReferenceException e) {
	            checkCycling(e, 5);
	            pause(1000);
	            type(xpath, value, validate);
	        } finally {
	            loopCount = 0;
	        }
	    }
	    public static void closeMessageDialog() {
	        System.out.println("--Closing message dialog--");
	        /*if (ieFlag) {
	            click(ELEMENT_MESSAGE_DIALOG_CLOSE_ICON_IE);
	        } else {*/
	            click(ELEMENT_MESSAGE_DIALOG_CLOSE_ICON);
	        //}
	    }
	    public static void save() {
	        waitForAndGetElement(ELEMENT_SAVE_BUTTON);
	        click(ELEMENT_SAVE_BUTTON);
	    }
	    public static void waitForMessage(String message) {
	        System.out.println("--Verify message: " + message);
	        pause(500);
	        waitForTextPresent(message);
	    }
	    public static void signInAsRoot() {
	        signIn("root", "gtn");
	    }
	    public static void signIn(String username, String password) {
	        System.out.println("--Signing  in as " + username + "--");
	        click(ELEMENT_SIGN_IN_LINK);
	        type(ELEMENT_INPUT_USERNAME, username, true);
	        type(ELEMENT_INPUT_PASSWORD, password, true);
	        click(ELEMENT_SIGN_IN_CONFIRM_BUTTON);
	        waitForElementNotPresent(ELEMENT_SIGN_IN_CONFIRM_BUTTON);
	    }
	    public static void signOut() {
	        pause(500);
	        goToPage(ELEMENT_SIGN_IN_LINK, ELEMENT_LINK_PORTAL_TOP_CONTAINER, ELEMENT_SIGN_OUT_LINK);
	    }
	    public static void goToNewStaff() {
	        System.out.println("--Go to New Staff--");
	        goToPage(ELEMENT_SEARCH_ICON_REGISTER, ELEMENT_LINK_SetUp, ELEMENT_LINK_Users, ELEMENT_LINK_AddUsers);
	    }
	    public static void goToPage(String verification, String... navigation) {
	        String page = makeLink(navigation[navigation.length - 1]);
	        boolean needToBeVerified = true;

	        List<String> navigationList = new ArrayList<String>();

	        for (int i = 0; i < (navigation.length - 1); i++) {
	            String node = navigation[i];
	            node = makeLink(node);
	            navigationList.add(node);
	        }

	        try {
	            for (String node : navigationList) {
	                if (ieFlag) {
	                    actions.moveToElement(getElement(node));
	                } else {
	                    mouseOver(node, false);
	                }
	            }
	            mouseOverAndClick(page);
	        } catch (StaleElementReferenceException e) {
	            checkCycling(e, 10);
	            goToPage(verification, navigation);
	            needToBeVerified = false;
	        } finally {
	            loopCount = 0;
	        }

	        if (verification != null && needToBeVerified) {
	            pause(500);
	            verifyLocation(verification, navigationList, page);
	        }
	    }

	    private static String makeLink(String node) {
	        if (!node.contains("//")) {
	            String label = node;
	            node = "//a[text()='" + label + "']";
	        }
	        return node;
	    }

	    private static void verifyLocation(String xpath, List<String> navigation, String page) {
	        System.out.println("verifyLocation, element: " + xpath);
	        if (isElementNotPresent(xpath)) {
	            pause(5000);
	        }
	        for (; isElementNotPresent(xpath); seconds++) {
	            if (seconds >= timeoutSecInt) {
	                Assert.fail("Timeout at goToPage");
	            }
	            pause(500);
	            try {
	                for (String node : navigation) {
	                    if (ieFlag) {
	                        actions.moveToElement(getElement(xpath));
	                    } else {
	                        mouseOver(node, false);
	                    }
	                }
	                mouseOverAndClick(page);
	            } catch (StaleElementReferenceException e) {
	                checkCycling(e, 10);
	                verifyLocation(xpath, navigation, page);
	                break;
	            } finally {
	                loopCount = 0;
	            }
	        }
	        seconds = 0;
	    }
	    public static boolean isElementPresent(String xpath) {
	        if (ieFlag) {
	            pause(1000);
	        } else {
	            pause(500);
	        }
	        try {
	            driver.findElement(By.xpath(xpath));
	            return true;
	        } catch (NoSuchElementException e) {
	            return false;
	        }
	    }

	    public static boolean isElementNotPresent(String xpath) {
	        return !isElementPresent(xpath);
	    }
	    
	    public static void mouseOver(String xpath, boolean safeToSERE) {
	        if (safeToSERE) {
	            try {
	                WebElement element = waitForAndGetElement(xpath);
	                actions.moveToElement(element).perform();
	            } catch (StaleElementReferenceException e) {
	                checkCycling(e, 5);
	                pause(1000);
	                mouseOver(xpath, safeToSERE);
	            } finally {
	                loopCount = 0;
	            }
	        } else {
	            WebElement element = waitForAndGetElement(xpath);
	            actions.moveToElement(element).perform();
	        }
	    }
	    
	    public static void mouseOverAndClick(String xpath) {
	        WebElement element;
	       /* if (ieFlag) {
	            element = getElement(xpath);
	        } else {*/
	            element = waitForAndGetElement(xpath);
	        //}
	        actions.moveToElement(element).click(element).build().perform();
	    }
	    public static void usePaginator(String xpath, String exceptionMessage) {
	        String page1 = ELEMENT_PAGINATOR_PAGE_LINK.replace("${number}", "1");

	        click(page1);
	        pause(500);
	        int totalPages = isElementPresent(ELEMENT_PAGINATOR_TOTAL_NUMBER) ? Integer.valueOf(getText(ELEMENT_PAGINATOR_TOTAL_NUMBER)) : 1;
	        int i = 1;
	        while (isElementNotPresent(xpath)) {
	            if (i == totalPages) {
	                Assert.fail(exceptionMessage);
	            }
	            click(ELEMENT_PAGINATOR_NEXT_ICON);
	            waitForAndGetElement(ELEMENT_PAGINATOR_SELECTED_PAGE.replace("${number}", String.valueOf((++i))));
	            pause(500);
	        }
	    }
	    public static String getTextFromAlert() {
	        try {
	            Alert alert = driver.switchTo().alert();
	            return alert.getText();
	        } catch (NoAlertPresentException e) {
	            return "";
	        }
	    }
	    public static void waitForConfirmation(String confirmationText) {
	        String message = getTextFromAlert();

	        System.out.println("confirmation: " + message);

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
	            if (second >= timeoutSecInt) {
	                Assert.fail("Timeout at waitForConfirmation: " + confirmationText);
	            }
	            if (message.equals(confirmationText)) {
	                break;
	            }
	            pause(1000);
	        }
	        Alert alert = driver.switchTo().alert();
	        alert.accept();
	        pause(500);
	    }

}
