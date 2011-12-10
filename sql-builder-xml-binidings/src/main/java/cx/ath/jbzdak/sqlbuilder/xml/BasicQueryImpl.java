package cx.ath.jbzdak.sqlbuilder.xml;

import cx.ath.jbzdak.sqlbuilder.BasicSQLFactory;
import cx.ath.jbzdak.sqlbuilder.QueryDescriptor;
import cx.ath.jbzdak.sqlbuilder.SQLFactory;

import java.util.Map;

/**
 * Created by: Jacek Bzdak
 */
public class BasicQueryImpl implements BasicSQLFactory{

   final SQLFactory factory;
   
   final DefaultQueryDescriptor descriptor; 

   public BasicQueryImpl(SQLFactory factory, Map<String, String> metadata) {
      this.factory = factory;
      descriptor = new DefaultQueryDescriptor(metadata, factory);
   }
   

   @Override
   public String getName() {
      return getDescriptor().getName();
   }

   @Override
   public QueryDescriptor getDescriptor() {
      return descriptor;
   }

   @Override
   public String toSQL() {
      return factory.toSQL();
   }

   @Override
   public Object setParameterValue(String parameterName, Object value) {
      return factory.getContext().setParameterValue(parameterName, value);
   }
}
