package cx.ath.jbzdak.sqlbuilder.dialect.peer;

import cx.ath.jbzdak.sqlbuilder.*;

/**
 * Created by: Jacek Bzdak
 */
public abstract class AbstractPeer<T extends IntermediateSQLFactory> implements SQLPeer<T>{

   protected T parent;

   public void registerParent(T t) {
      parent = t;
   }

   protected abstract void appendToInternal(RenderingContext renderingContext, StringBuilder stringBuilder);

   public final void appendTo(RenderingContext renderingContext, StringBuilder stringBuilder) {
      renderingContext.push(this.parent);
      try{
         appendToInternal(renderingContext, stringBuilder);
      }finally {
         renderingContext.pop();
      }
   }

}
