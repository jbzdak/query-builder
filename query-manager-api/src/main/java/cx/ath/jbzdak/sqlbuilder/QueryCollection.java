
package cx.ath.jbzdak.sqlbuilder;

import java.util.Set;

/**
 * Created by: Jacek Bzdak
 */
public interface QueryCollection {

   /**
    *
    * @param name
    * @return Null if cant find query with this name
    */
   BasicSQLFactory getQuery(String name);

   Set<String> getQueryNames();

   void setJdbcUrl(String jdbc);

   void setCredentials(String username, String password);


}
