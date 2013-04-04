package util;

import org.apache.commons.lang.WordUtils;

import models.AppUser;

public class PrepareModel {
	
	public static void prepareAppUser(AppUser user){
		try{
		user.userFirstName=WordUtils.capitalize(user.userFirstName).trim();
		user.userSurname= WordUtils.capitalize(user.userSurname).trim();
		user.email=user.email.trim();
		}
		catch(NullPointerException ex){
			ex.printStackTrace();
		}
	}

}
