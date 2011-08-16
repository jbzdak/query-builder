package cx.ath.jbzdak.sqlbuilder.dialect.peer;

import cx.ath.jbzdak.sqlbuilder.RenderingContext;
import cx.ath.jbzdak.sqlbuilder.booleanExpression.Not;

/**
 * Created by: Jacek Bzdak
 */
public class NotPeer extends AbstractPeer<Not>{

   @Override
   protected void appendToInternal(final RenderingContext renderingContext, StringBuilder stringBuilder) {
      PeerUtils.appendInBrackets(stringBuilder, new PeerUtils.Appender() {
         public void appendTo(StringBuilder stringBuilder) {
            stringBuilder.append("NOT ");
            parent.appendToInternal(renderingContext, stringBuilder);
         }
      });
   }
}
