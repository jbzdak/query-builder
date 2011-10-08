
package cx.ath.jbzdak.sqlbuilder;

import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Created by: Jacek Bzdak
 */
public interface QueryCollection {

   BasicSQLFactory getQuery(String name);

   Set<String> getQueryNames();

   void setJdbcUrl(String jdbc);

   void setCredentials(String username, String password);


}
