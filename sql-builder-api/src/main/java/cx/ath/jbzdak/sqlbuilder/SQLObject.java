package cx.ath.jbzdak.sqlbuilder;

import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfig;
import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfigKey;
import cx.ath.jbzdak.sqlbuilder.dialect.config.PrettifySQLLevel;

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

   protected void postprocessSQL(StringBuilder sql){
      prettifySQL(sql);
   }

   protected void prettifySQL(StringBuilder sql){
      String sqlString = sql.toString();
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
            return;
         default:
            throw new IllegalStateException();
      }
      sql.delete(0, sql.length());
      sql.append(sqlString);
   }

   public StringBuilder toSQL() {
      StringBuilder stringBuilder = new StringBuilder();
      RenderingContext renderingContext = expressionContext.renderingContext();
      appendTo(renderingContext, stringBuilder);
      postprocessSQL(stringBuilder);
      return stringBuilder;
   }

}
