package cx.ath.jbzdak.sqlbuilder.dialect.peer;

import cx.ath.jbzdak.sqlbuilder.RenderingContext;
import cx.ath.jbzdak.sqlbuilder.booleanExpression.NAryBooleanExpression;

/**
 * Created by: Jacek Bzdak
 */
public class NAryBooleanExpressionPeer extends AbstractPeer<NAryBooleanExpression> {

   public NAryBooleanExpressionPeer() {
   }


   @Override
   protected void appendToInternal(RenderingContext renderingContext, StringBuilder stringBuilder) {
      stringBuilder.append("(");
      PeerUtils.joinSqls(renderingContext, stringBuilder, " " + parent.getType() + " ", parent.getExpressions());
      stringBuilder.append(")");
   }

}
