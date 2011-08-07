package cx.ath.jbzdak.sqlbuilder.dialect.peer;

import cx.ath.jbzdak.sqlbuilder.RenderingContext;
import cx.ath.jbzdak.sqlbuilder.booleanExpression.UnaryBooleanExpresson;

/**
 * Created by: Jacek Bzdak
 */
public class UnaryBooleanExpressionPeer extends AbstractPeer<UnaryBooleanExpresson>{

   @Override
   protected void appendToInternal(final RenderingContext renderingContext, StringBuilder stringBuilder) {
      PeerUtils.appendInBrackets(stringBuilder, new PeerUtils.Appender() {
         public void appendTo(StringBuilder stringBuilder) {
            parent.getExpression().appendTo(renderingContext, stringBuilder);
            stringBuilder.append(" ");
            stringBuilder.append(parent.getExpressionType());
         }
      });
   }
}
