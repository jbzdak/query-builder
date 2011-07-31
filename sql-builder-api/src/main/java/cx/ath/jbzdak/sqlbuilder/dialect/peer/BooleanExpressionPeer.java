package cx.ath.jbzdak.sqlbuilder.dialect.peer;

import cx.ath.jbzdak.sqlbuilder.booleanExpression.AbstractBinaryBooleanExpression;

/**
 * Created by: Jacek Bzdak
 */
public class BooleanExpressionPeer extends AbstractPeer<AbstractBinaryBooleanExpression>{

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
