package cx.ath.jbzdak.sqlbuilder;

/**
 * Created by: Jacek Bzdak
 */
public class SQLObject extends IntermediateSQLObject implements SQLFactory {

   public SQLObject() {
   }

   public SQLObject(SQLObject parent){
      expressionContext = parent.expressionContext;
   }

   public SQLObject(ExpressionContext expressionContext) {
      this.expressionContext = expressionContext;
   }

   public StringBuilder toSQL() {
      StringBuilder stringBuilder = new StringBuilder();
      appendTo(stringBuilder);
      return stringBuilder;
   }

   public void appendTo(StringBuilder stringBuilder) {
      if(sqlPeer == null){
         sqlPeer = expressionContext.getDialect().getPeer(this);
      }
      sqlPeer.appendTo(stringBuilder);
   }

}
