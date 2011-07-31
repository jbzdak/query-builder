package cx.ath.jbzdak.sqlbuilder.dialect.peer;

import cx.ath.jbzdak.sqlbuilder.booleanExpression.NAryBooleanExpression;

/**
 * Created by: Jacek Bzdak
 */
public class NAryBooleanExpressionPeer extends AbstractPeer<NAryBooleanExpression> {

   public NAryBooleanExpressionPeer() {
   }

   public void appendTo(StringBuilder stringBuilder) {
      stringBuilder.append("(");
      PeerUtils.joinSqls(stringBuilder, " " + parent.getType() + " ", parent.getExpressions());
      stringBuilder.append(")");
   }
}
