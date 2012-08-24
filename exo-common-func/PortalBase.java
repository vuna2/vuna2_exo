package org.exoplatform.selenium.platform.portal;


import java.util.Map;

import org.exoplatform.selenium.platform.PlatformBase;
import org.openqa.selenium.WebElement;


public class PortalBase extends PlatformBase{
	
	/*------------- ---- Data for Portal ------------------------------*/
	public static final String ELEMENT_ADD_NEW_PORTAL_LINK = "//a[text()='Add New Portal']";	
	public static final String ELEMENT_CHECKBOX_SHOW_INFO_BAR_BY_DEFAULT = "//input[@name='showInfobar']";
	public static final String ELEMENT_PORTAL_IN_LIST = "//td[@class='Content']/div[@class='Label' and text()='${portalName}']";
	public static final String ELEMENT_PORTAL_DELETE_ICON = "//td[@class='Content']/div[@class='Label' and text()='${portalName}']/../../td[3]/a[@class='DeleteIcon']";
	public static final String ELEMENT_PORTAL_EDIT_ICON = "//td[@class='Content']/div[@class='Label' and text()='${portalName}']/../../td[3]/a[@class='EditNavIcon'][2]";
	public static final String ELEMENT_EDIT_FIRST_PORTAL_CONFIG = "//div[@id='UISiteManagement']//a[@class='EditNavIcon'][2]";
	public static final String ELEMENT_SWITCH_VIEW_MODE_PORTAL = "//a[text()='Switch View Mode']";
	public static final String ELEMENT_LINK_Portal = "//a[text()='Portal']";
	public static final String ELEMENT_LINK_SITE   = "//a[text()='Sites']";
	
	public static final String ELEMENT_SELECT_LOCALE = "//select[@name='locale']";
	public static final String ELEMENT_SELECT_SKIN 	 = "//select[@name='skin']";
	public static final String ELEMENT_SELECT_SESSION_ALIVE= "//select[@name='sessionAlive']"; 
	public static final String ELEMENT_PROPERTIES_TAB = "//div[text()='Properties' and @class='MiddleTab']";
	public static final String ELEMENT_PERMISSION_SETTING_TAB= "//div[text()='Permission Settings' and @class='MiddleTab']";
	public static final String ELEMENT_CHECKBOX_PUBLIC_MODE = "//input[@name='publicMode']";
	public static final String ELEMENT_LINK_EDIT_PERMISSION = "//a[text()='Edit Permission Settings']";
	
	public static final String ELEMENT_SELECT_ACCESS_MEMBERSHIP_ITEM = "//a[text()='${membership}']";
	public static final String ELEMENT_SELECTED_ACCESS_PERM_GROUP = "//div[@id='PermissionGrid']/table/tbody//div[text()='/${groupId}']";
	public static final String ELEMENT_SELECTED_ACCESS_PERM_MEMBERSHIP = "//div[@id='PermissionGrid']/table/tbody//div[text()='${membership}']";
	public static final String ELEMENT_ADD_PERMISSION_BUTTON = "//a[text()='Add Permission']";
	public static final String ELEMENT_SELECT_EDIT_MEMBERSHIP_ITEM = "//div[@id='UIPermissionSelector']//a[text()='${membership}']";
	public static final String ELEMENT_SELECTED_EDIT_PERM_GROUP = "// div[@class='SelectedPermissionInfo']/div[2]/div[.='/${groupId}']";
	public static final String ELEMENT_SELECTED_EDIT_PERM_MEMBERSHIP = "//div[@class='SelectedPermissionInfo']/div[3]/div[.='${membership}']";
	public static final String ELEMENT_SELECT_PERMISSION_BUTTON = "//a[text()='Select Permission']";
	public static final String ELEMENT_SELECT_ACCESS_GROUP_ITEM = "//a[@title='${group}']";
	public static final String ELEMENT_SELECT_EDIT_GROUP_ITEM = "//div[@id='UIPermissionSelector']//a[text()='${group}']";
	public static final String ELEMENT_SELECT_EDIT_PORTAL_CONFIG = "//div[@id='UISiteManagement']//table//tr/td/div[text()='${portalName}']/../../td[2]//a[@class='EditPortIcon']";
	

	
	
	/*------------- ---- End of Data for Portal ------------------------------*/
	
	
	
	/*------------- Data for Portal/Account -------------------------*/

	public static final String ELEMENT_USER_PROFILE_TAB = "//div[text()='User Profile' and @class='MiddleTab']";
	public static final String ELEMENT_SELECT_USER_LANGUAGE = "//select[@name='user.language']";
	public static final String ELEMENT_SEARCH_ICON_REGISTER = "//img[@class='SearchIcon']";
	public static final String ELEMENT_INPUT_USER_NAME_GIVEN = "//input[@id='user.name.given']";
	public static final String ELEMENT_ACCOUNT_SETTING_TAB = "//div[text()='Account Settings' and @class='MiddleTab']";

	public static final String ELEMENT_USER_DELETE_ICON ="//div[@id='UIListUsersGird']//div[text()='${username}']/../..//img[@class='DeleteUserIcon']";
	public static final String ELEMENT_SEARCH_ICON_USERS_MANAGEMENT = "//form[@id='UISearchForm']/div[2]/a";
	public static final String ELEMENT_LINK_USERS_MANAGEMENT="//a[contains(text(),'Group and Roles')]";
	public static final String ELEMENT_INPUT_SEARCH_USER_NAME = "//input[@name='searchTerm']"; 
	public static final String ELEMENT_EDIT_USER_INFO =  "//img[@title='Edit User Info']" ;      
	public static final String ELEMENT_SELECT_SEARCH_OPTION = "//select[@name='searchOption']";
	public static final String ELEMENT_USER_EDIT_ICON = "//div[@id='UIListUsersGird']/table//tr/td/div[text()='${username}']/../../td[5]//img[@class='ViewUserInfoIcon']";

	public static final String ELEMENT_GROUP_ADD_NEW_ICON = "//div[@id='UIOrganizationPortlet']//div[@class='TitleBar']/a[@class='TreeActionIcon AddGroupIcon']";
	public static final String ELEMENT_INPUT_GROUP_NAME = "//input[@name='groupName']";
	public static final String ELEMENT_INPUT_LABEL = "//input[@id='label']";
	public static final String ELEMENT_TEXTAREA_DESCRIPTION = "//textarea[@id='description']";
	public static final String ELEMENT_GROUP_REMOVE_ICON = "//div[@id='UIOrganizationPortlet']//div[@class='TitleBar']/a[@class='TreeActionIcon RemoveGroupIcon']";
	public static final String ELEMENT_GROUP_EDIT_ICON = "//div[@id='UIOrganizationPortlet']//div[@class='TitleBar']/a[@class='TreeActionIcon EditGroupIcon']";
	public static final String ELEMENT_TAB_GROUP_MANAGEMENT = "//div[@class='GroupManagementIcon']/..";
	public static final String ELEMENT_GROUP_TO_SELECT_LINK = "//a[contains(@class, 'NodeIcon') and @title='${group}']";
	public static final String ELEMENT_GROUP_SELECTED = "//a[@class='NodeIcon PortalIcon NodeSelected' and @title='${group}']";
	public static final String ELEMENT_GROUP_SEARCH_USER_ICON = "//form[@id='UIGroupMembershipForm']/div[2]/div/table/tbody/tr[1]/td[2]/a";
	public static final String ELEMENT_GROUP_SEARCH_POPUP_ADD_ICON = "//form[@id='UIUserSelector']//div[@class='UIAction']//a[@class='ActionButton LightBlueStyle']";
	public static final String ELEMENT_SELECT_MEMBERSHIP = "//select[@name='membership']";
	public static final String ELEMENT_GROUP_USER_IN_TABLE = "//div[@class='UIUserInGroup']//div[@title='${username}']";
	public static final String ELEMENT_INPUT_NAME = "//input[@id='name']";    
	public static final String ELEMENT_TAB_MEMBERSHIP_MANAGEMENT = "//div[@class='MembershipManagementIcon']/..";
	public static final String ELEMENT_MEMBERSHIP_EDIT_ICON = "//div[@class='UIListMembershipType']//table//tr/td/div[text()='${membership}']/../../td[5]//img[@class='EditMembershipIcon']";
	public static final String ELEMENT_MEMBERSHIP_DELETE_ICON = "//div[@class='UIListMembershipType']//table//tr/td/div[text()='${membership}']/../../td[5]//img[@class='DeleteMembershipIcon']";
	public static final String ELEMENT_NEXT_PAGE_ICON = "//a[@title='Next Page']";
	
	
	/*------------- End of Data for Portal/Account -------------------------*/

	//Go to portal sites
	 public static void goToPortalSites() {
	        System.out.println("--Go to Portal Site Management--");
	        //goToPage(ELEMENT_LINK_SetUp, ELEMENT_LINK_SetUp, ELEMENT_LINK_Portal,ELEMENT_LINK_SITE );
	        mouseOver(ELEMENT_LINK_SetUp, false);
	        pause(2000);
	        mouseOver(ELEMENT_LINK_Portal, false);
	        pause(2000);
	        WebElement element;
	        element = waitForAndGetElement(ELEMENT_LINK_SITE);
	        actions.moveToElement(element).click(element).build().perform();
	        pause(2000);
	        
	 }

	//Add new portal
	public void addNewPortal(String portalName, String portalLocale, String portalSkin, String portalSession, 
            boolean publicMode, Map<String, String> permissions, String editGroupId, String editMembership){
		System.out.println("--Create new portal--");
		
		click(ELEMENT_ADD_NEW_PORTAL_LINK);
		waitForTextPresent("Portal Setting");
		type(ELEMENT_INPUT_NAME, portalName, true);
		select(ELEMENT_SELECT_LOCALE, portalLocale);
		select(ELEMENT_SELECT_SKIN, portalSkin);
		click(ELEMENT_PROPERTIES_TAB);
		select(ELEMENT_SELECT_SESSION_ALIVE, portalSession);
		click(ELEMENT_PERMISSION_SETTING_TAB);
		
		if (publicMode) {
            waitForAndGetElement("//a[text()='Add Permission']");
			check(ELEMENT_CHECKBOX_PUBLIC_MODE);
			waitForElementNotPresent("//a[text()='Add Permission']");
		} else {
			for (String key : permissions.keySet()) {
				setViewPermissions(key, permissions.get(key));
			}
		}
		click(ELEMENT_LINK_EDIT_PERMISSION);
		setEditPermissions(editGroupId, editMembership);
		save();
	}
	
	//Edit a portal
	public void editPortal(String portalName, String portalLocale, String portalSkin, String portalSession, 
            boolean publicMode, Map<String, String> permissions, String editGroupId, String editMembership){
		System.out.println("--Create new portal--");
		
		String editIcon = ELEMENT_SELECT_EDIT_PORTAL_CONFIG.replace("${portalName}", portalName);		
		click(editIcon);
		
		waitForTextPresent("Portal Setting");
		
		select(ELEMENT_SELECT_LOCALE, portalLocale);
		select(ELEMENT_SELECT_SKIN, portalSkin);
		click(ELEMENT_PROPERTIES_TAB);
		select(ELEMENT_SELECT_SESSION_ALIVE, portalSession);
		click(ELEMENT_PERMISSION_SETTING_TAB);
		
		click (ELEMENT_CHECKBOX_PUBLIC_MODE);
		
		if (publicMode) {
            waitForAndGetElement("//a[text()='Add Permission']");
			check(ELEMENT_CHECKBOX_PUBLIC_MODE);
			waitForElementNotPresent("//a[text()='Add Permission']");
		} else {
			for (String key : permissions.keySet()) {
				setViewPermissions(key, permissions.get(key));
			}
		}
		click(ELEMENT_LINK_EDIT_PERMISSION);
		setEditPermissions(editGroupId, editMembership);
		save();
	}
	
	//Set view permissions for portal
	public static void setViewPermissions(String groupId, String membership) {
		String membershipToSelect = ELEMENT_SELECT_ACCESS_MEMBERSHIP_ITEM.replace("${membership}", membership);
		String selectedGroup = ELEMENT_SELECTED_ACCESS_PERM_GROUP.replace("${groupId}", groupId.replace(" ", "-").toLowerCase());
		String selectedMembership = ELEMENT_SELECTED_ACCESS_PERM_MEMBERSHIP.replace("${membership}", membership);
		
		System.out.println("--Setting view permission to " + groupId + ", " + membership + "--");
		String[] groups = groupId.split("/");
        pause(500);
		click(ELEMENT_ADD_PERMISSION_BUTTON);
		waitForTextPresent("Browse and select a group");
		for (String group : groups) {
			String groupToSelect = ELEMENT_SELECT_ACCESS_GROUP_ITEM.replace("${group}", group);
			click(groupToSelect);
		}
        pause(500);
		click(membershipToSelect);
        pause(500);
		waitForTextNotPresent("Permission Selector");
		waitForAndGetElement(selectedGroup);
		waitForAndGetElement(selectedMembership);
	}
	
	
	//Set edit permissions for portal
	public static void setEditPermissions(String groupId, String membership) {
		String membershipToSelect = ELEMENT_SELECT_EDIT_MEMBERSHIP_ITEM.replace("${membership}", membership);
		//String selectedGroup = ELEMENT_SELECTED_EDIT_PERM_GROUP.replace("${groupId}", groupId.replace(" ", "-").toLowerCase());
		String selectedMembership = ELEMENT_SELECTED_EDIT_PERM_MEMBERSHIP.replace("${membership}", membership);
		
		System.out.println("--Setting edit permission to " + groupId + ", " + membership + "--");
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

	//verify the existence of portal
	public static void verifyPortalExists(String portalName) {
		String portal = ELEMENT_PORTAL_IN_LIST.replace("${portalName}", portalName);
		
		System.out.println("--Verify portal (" + portalName + ") exist--");
		goToPortalSites();
		waitForAndGetElement(portal);
	}


}


