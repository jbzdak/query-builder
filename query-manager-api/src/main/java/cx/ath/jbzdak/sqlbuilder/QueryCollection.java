
package cx.ath.jbzdak.sqlbuilder;

import java.util.List;
import java.util.Set;

/**
 * Created by: Jacek Bzdak
 */
public interface QueryCollection {

   BasicSQLFactory getQuery(String name);

   Set<String> getQueryNames();

   void setJdbcUrl(String jdbc);


}
