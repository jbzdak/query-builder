package cx.ath.jbzdak.sqlbuilder;

/**
 * Created by: Jacek Bzdak
 */
public class IntermediateSQLObject {

   private ExpressionContext expressionContext = new ExpressionContext();

   protected SQLPeer sqlPeer;

   protected void maybeRefreshPeer(ExpressionContext expressionContext){

   }



   public void setContext(ExpressionContext expressionContext) {
      sqlPeer = null;
   }

   public StringBuilder toSQL(RenderingContext renderContext) {
      StringBuilder stringBuilder = new StringBuilder();
      appendTo(renderContext, stringBuilder);
      return stringBuilder;
   }


   public void appendTo(RenderingContext renderingContext, StringBuilder stringBuilder) {
      sqlPeer.appendTo(renderingContext, stringBuilder);
   }
}
