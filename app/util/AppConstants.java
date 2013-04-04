package util;

import java.util.Map;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class AppConstants implements HttpSessionListener{
	
	public AppConstants(){};
	
	
	private static Map<String , Object> itemMap;


	public static Map<String, Object> getItemMap() {
		return itemMap;
	}


	public void setItemMap(Map<String, Object> itemMap) {
		AppConstants.itemMap = itemMap;
	}


	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

	
	
}
