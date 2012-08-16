package org.exoplatform.Selenium.platform.eXoGTN.Account;


import org.exoplatform.Selenium.platform.eXoGTN.EXoGTNTestSuite;
import org.testng.annotations.*;


public class Test_PLT_ACC_010 extends EXoGTNTestSuite{
	
	
	@Test(groups="Account")
	public void testPLT_ACC_010(){
		System.out.println("-- Create new user with lower case--");
        openPortal(true);	
		signInAsRoot();
		pause(3000);
		goToNewStaff();
		pause(3000);
		addNewAccountAtNewStaff("test_prl_01234", "test_prl_0123", "test_prl_0123", "testprl", 
				"testprlExo", "test_prl_0234@localhost.com", "", "English", true);
	
		System.out.println("-- Check Availability function when new account's user name is existing --");
		type(ELEMENT_INPUT_USERNAME, "test_prl_01234", true);
		click(ELEMENT_SEARCH_ICON_REGISTER);
		waitForMessage("This username already exists, please enter another one.");
		closeMessageDialog();

		System.out.println("-- Check Availability function when new account's user name does not exist--");
		type(ELEMENT_INPUT_USERNAME, "test1608", true);
		click(ELEMENT_SEARCH_ICON_REGISTER);
		waitForMessage("This username is available.");
		closeMessageDialog();
		
		System.out.println("-- Check Availability function when new account's user name is the same with deleted account's user name --");
		goToUsersAndGroupsManagement();
		deleteUser("test_prl_01234");
		
		goToNewStaff();
		type(ELEMENT_INPUT_USERNAME, "test_prl_01234", true);
		click(ELEMENT_SEARCH_ICON_REGISTER);
		waitForMessage("This username is available.");
		closeMessageDialog();
		
		System.out.println("-- Check Availability function when new account's user name is blank--");	
		type(ELEMENT_INPUT_USERNAME, "", true);
		click(ELEMENT_SEARCH_ICON_REGISTER);
		//waitForMessage("The field User Name: is required. ");
		closeMessageDialog();
		
		signOut();
		
	}
	

}
