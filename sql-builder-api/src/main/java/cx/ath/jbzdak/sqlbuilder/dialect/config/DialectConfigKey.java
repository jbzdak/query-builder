package cx.ath.jbzdak.sqlbuilder.dialect.config;

import cx.ath.jbzdak.sqlbuilder.Dialect;

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
   };


   public abstract Object getDefault(Dialect d);



}
