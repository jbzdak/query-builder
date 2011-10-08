package cx.ath.jbzdak.sqlbuilder;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by: Jacek Bzdak
 */
public class CompositeQueryCollection implements QueryCollection{

   List<QueryCollection> internal;

   private final Set<String> queryNames = new HashSet<String>();


   public boolean removeCollection(QueryCollection o) {

      boolean remove = internal.remove(o);
      rebuildQueryNames();
      return remove;
   }

   public boolean addCollection(QueryCollection queryCollection) {
      boolean b = internal.add(queryCollection);
      rebuildQueryNames();
      return b;
   }

   private void rebuildQueryNames(){
      queryNames.clear();
      for (QueryCollection collection : internal) {
         queryNames.addAll(collection.getQueryNames());
      }

   }

   public BasicSQLFactory getQuery(String name) {
      for (QueryCollection queryCollection : internal) {
         BasicSQLFactory factory = queryCollection.getQuery(name);
         if(factory != null){
            return factory;
         }
      }
      return null;
   }

   public Set<String> getQueryNames() {
      return Collections.unmodifiableSet(queryNames);
   }

   public void setJdbcUrl(String jdbc) {
      for (QueryCollection queryCollection : internal) {
         queryCollection.setJdbcUrl(jdbc);
      }
   }

   public void setCredentials(String username, String password) {
      for (QueryCollection queryCollection : internal) {
         queryCollection.setCredentials(username, password);
      }
   }
}
