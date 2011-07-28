package cx.ath.jbzdak.sqlbuilder.dialect.peer;

import cx.ath.jbzdak.sqlbuilder.booleanExpression.AbstractBooleanExpression;
import cx.ath.jbzdak.sqlbuilder.booleanExpression.BooleanExpression;

/**
 * Created by: Jacek Bzdak
 */
public class BooleanExpressionPeer extends AbstractPeer<AbstractBooleanExpression>{

   public void appendTo(StringBuilder stringBuilder) {
      stringBuilder.append(" (");
      parent.getLhs().appendTo(stringBuilder);
      stringBuilder.append(' ');
      stringBuilder.append(parent.getType());
      stringBuilder.append(' ');
      parent.getRhs().appendTo(stringBuilder);
      stringBuilder.append(" )");
   }
}
