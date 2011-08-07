package cx.ath.jbzdak.sqlbuilder.dialect.peer;

import cx.ath.jbzdak.sqlbuilder.*;

/**
 * Created by: Jacek Bzdak
 */
public abstract class AbstractPeer<T extends IntermediateSQLFactory> implements SQLPeer<T>{

   protected T parent;

   protected ExpressionContext context;

   public void registerParent(T t) {
      parent = t;
   }

   public Dialect getDialect() {
      return context.getDialect();
   }

   protected abstract void appendToInternal(RenderingContext renderingContext, StringBuilder stringBuilder);

   public final void appendTo(StringBuilder stringBuilder) {
      appendTo(context.renderingContext(this.parent), stringBuilder);
   }

   public StringBuilder toSQL() {
      StringBuilder stringBuilder = new StringBuilder();
      appendTo(stringBuilder);
      return stringBuilder;
   }

   public StringBuilder toSQL(RenderingContext renderContext) {
      StringBuilder stringBuilder = new StringBuilder();
      appendTo(renderContext, stringBuilder);
      return stringBuilder;
   }

   public final void appendTo(RenderingContext renderingContext, StringBuilder stringBuilder) {
      renderingContext.push(this.parent);
      try{
         appendToInternal(renderingContext, stringBuilder);
      }finally {
         renderingContext.pop();
      }
   }

}
