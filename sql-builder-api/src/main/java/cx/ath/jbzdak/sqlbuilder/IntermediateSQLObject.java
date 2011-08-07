package cx.ath.jbzdak.sqlbuilder;

/**
 * Created by: Jacek Bzdak
 */
public class IntermediateSQLObject implements IntermediateSQLFactory{

   protected SQLPeer sqlPeer;

   private Dialect lastPeerGenerationDialect;

   protected void maybeRefreshPeer(ExpressionContext expressionContext){
      if(!expressionContext.getDialect().equals(lastPeerGenerationDialect)){
         sqlPeer = expressionContext.getDialect().getPeer(this);
         lastPeerGenerationDialect = expressionContext.getDialect();
      }
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
      maybeRefreshPeer(renderingContext.getExpressionContext());
      sqlPeer.appendTo(renderingContext, stringBuilder);
   }
}
