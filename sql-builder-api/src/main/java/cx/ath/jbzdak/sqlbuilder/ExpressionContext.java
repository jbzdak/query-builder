package cx.ath.jbzdak.sqlbuilder;

import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfig;

/**
 * Created by: Jacek Bzdak
 */
public class ExpressionContext {

   final Dialect dialect;

   DialectConfig dialectConfig;

   public ExpressionContext() {
      dialect = DialectHolder.getDefaultDialect();
   }

   public ExpressionContext(Dialect dialect) {
      this.dialect = dialect;
   }

   public ExpressionContext(SQLObject sqlObject) {
      this.dialect = sqlObject.getExpressionContext().getDialect();
   }

   public RenderingContext renderingContext(){
      return new RenderingContext(dialect);
   }

   public RenderingContext renderingContext(IntermediateSQLFactory sqlFactory){
      RenderingContext context = new RenderingContext(dialect);
      return context;
   }

   public Dialect getDialect() {
      return dialect;
   }

   public DialectConfig getDialectConfig() {
      if (dialectConfig == null) {
         dialectConfig = new DialectConfig(dialect.getDialectConfig());
      }
      return dialectConfig;
   }


}
