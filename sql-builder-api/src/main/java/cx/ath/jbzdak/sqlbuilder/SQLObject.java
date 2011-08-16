package cx.ath.jbzdak.sqlbuilder;

import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfig;
import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfigKey;
import cx.ath.jbzdak.sqlbuilder.dialect.config.PrettifySQLLevel;

import java.util.Set;

/**
 * Created by: Jacek Bzdak
 */
public abstract class SQLObject extends IntermediateSQLObject implements SQLFactory {

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

   protected String prettifySQL(String sqlString){
      switch ((PrettifySQLLevel) getDialectConfig().getConfig(DialectConfigKey.PRETTIFY_SQL)){
         case MULTILINE:
            sqlString = sqlString.replaceAll("WHERE", "\n\tWHERE");
            sqlString = sqlString.replaceAll("JOIN", "\n\tJOIN");
            sqlString = sqlString.replaceAll("LIMIT", "\n\tLIMIT");
         case PRETTY:
            sqlString = sqlString.replaceAll("\\([ ]+", "(");
            sqlString = sqlString.replaceAll("[ ]+\\)", ")");
         case MINIMAL:
            sqlString = sqlString.replaceAll("[ ]+", " ");
            sqlString = sqlString.replaceAll("^\\s+", "");
            sqlString = sqlString.replaceAll("\\s+$", "");
            sqlString = sqlString.replaceAll("\\([ ]+\\(", "((");
            sqlString = sqlString.replaceAll("\\)[ ]+\\)", "))");
            break;
         case NONE:
            break;
         default:
            throw new IllegalStateException();
      }
      return sqlString;
   }

   public StringBuilder toSQL() {
      RenderingContext renderingContext = expressionContext.renderingContext();
      String sql = toSQLInternal(renderingContext).toString();
      sql = renderingContext.replaceParams(sql);
      sql = prettifySQL(sql);
      return new StringBuilder(sql);
   }


   public StringBuilder toSQLInternal(RenderingContext renderingContext) {
      StringBuilder stringBuilder = new StringBuilder();
      appendToInternal(renderingContext, stringBuilder);
      return stringBuilder;
   }

}
