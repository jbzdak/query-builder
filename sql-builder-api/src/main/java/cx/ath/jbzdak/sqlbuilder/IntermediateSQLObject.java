package cx.ath.jbzdak.sqlbuilder;

import java.util.Set;

/**
 * Created by: Jacek Bzdak
 */
public abstract class IntermediateSQLObject implements IntermediateSQLFactory{

   protected SQLPeer sqlPeer;

   private Dialect lastPeerGenerationDialect;

   protected ExpressionContext expressionContext;

   protected void maybeRefreshPeer(ExpressionContext expressionContext){
      if(!expressionContext.getDialect().equals(lastPeerGenerationDialect)){
         sqlPeer = expressionContext.getDialect().getPeer(this);
         lastPeerGenerationDialect = expressionContext.getDialect();
      }
   }

   public void setContext(ExpressionContext expressionContext) {
      sqlPeer = null;
      this.expressionContext = expressionContext;
   }

   public StringBuilder toSQL(RenderingContext renderContext) {
      StringBuilder stringBuilder = new StringBuilder();
      appendToInternal(renderContext, stringBuilder);
      return stringBuilder;
   }


   public void appendTo(RenderingContext renderingContext, StringBuilder stringBuilder) {
      appendToInternal(renderingContext, stringBuilder);
   }

   public void appendToInternal(RenderingContext renderingContext, StringBuilder stringBuilder) {
      maybeRefreshPeer(renderingContext.getExpressionContext());
      sqlPeer.appendTo(renderingContext, stringBuilder);
   }
}
