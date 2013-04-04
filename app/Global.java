import play.*;
import play.libs.*;
import com.avaje.ebean.Ebean;
import models.*;

import java.util.*;

public class Global extends GlobalSettings {
    @Override
    public void onStart(Application app) {
        // Check if the database is empty
        if (AppUser.finder.findRowCount() == 0) {
        	Map<String, List<Object>> all = (Map<String, List<Object>>)Yaml.load("dummy-data.yml");       	
            Ebean.save(all.get("users"));
        }
    }
}
