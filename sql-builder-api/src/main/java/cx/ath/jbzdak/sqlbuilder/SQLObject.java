package cx.ath.jbzdak.sqlbuilder;

import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfig;

/**
 * Created by: Jacek Bzdak
 */
public class SQLObject extends IntermediateSQLObject implements SQLFactory {


   ExpressionContext expressionContext;



   public SQLObject() {
   }

   public SQLObject(SQLObject parent){
      expressionContext = parent.expressionContext;
   }

   public SQLObject(ExpressionContext expressionContext) {
      this.expressionContext = expressionContext;
   }

   public ExpressionContext getExpressionContext() {
      return expressionContext;
   }

   public Dialect getDialect() {
      return expressionContext.getDialect();
   }

   public DialectConfig getDialectConfig() {
      return getDialect().getDialectConfig();
   }

   public StringBuilder toSQL() {
      StringBuilder stringBuilder = new StringBuilder();
      RenderingContext renderingContext = expressionContext.renderingContext();
      appendTo(renderingContext, stringBuilder);

      return stringBuilder;
   }

}
