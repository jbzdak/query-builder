package cx.ath.jbzdak.sqlbuilder.dialect.config;

import com.sun.org.apache.bcel.internal.util.ClassSet;
import cx.ath.jbzdak.sqlbuilder.Dialect;
import cx.ath.jbzdak.sqlbuilder.IntermediateSQLFactory;
import cx.ath.jbzdak.sqlbuilder.SQLFactory;
import cx.ath.jbzdak.sqlbuilder.SQLPeer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by: Jacek Bzdak
 */
public enum DialectConfigKey {
   IDENTIFIER_QUOTING_STRATEGY{
      @Override
      public Object getDefault(Dialect d) {
         return IdentifierQuotingStrategy.DEFAULT;
      }
   },
   ALIAS_QUOTING_STRATEGY{
      @Override
      public Object getDefault(Dialect d) {
         return d.getDialectConfig().getConfig(IDENTIFIER_QUOTING_STRATEGY);
      }
   },
   TABLE_EXPRESSION_QUOTING_STRATEGY{
      @Override
      public Object getDefault(Dialect d) {
         return d.getDialectConfig().getConfig(IDENTIFIER_QUOTING_STRATEGY);
      }
   },
   PRETTIFY_SQL{
      @Override
      public Object getDefault(Dialect d) {
         return PrettifySQLLevel.MINIMAL;
      }
   },
   /**
    * This key contains an instance of {@code Map<Class<? extends SQLFactory>, Class<? extends SQLPeer>>}
    */
   ADDITIONAL_PEERS{
      @Override
      public Object getDefault(Dialect d) {
         return new HashMap<Class<? extends IntermediateSQLFactory>, Class<? extends SQLPeer>>();
      }
   },
   PARAMETER_FACTORY{
      @Override
      public Object getDefault(Dialect d) {
         return null;
      }
   };

   public abstract Object getDefault(Dialect d);

}
