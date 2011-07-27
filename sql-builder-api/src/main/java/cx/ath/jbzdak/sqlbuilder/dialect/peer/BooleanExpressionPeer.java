package cx.ath.jbzdak.sqlbuilder.dialect.peer;

import cx.ath.jbzdak.sqlbuilder.BooleanExpression;

/**
 * Created by: Jacek Bzdak
 */
public class BooleanExpressionPeer extends AbstractPeer<BooleanExpression>{

   public void appendTo(StringBuilder stringBuilder) {
      stringBuilder.append(" (");
      stringBuilder.append(parent.getLhs().toSQL());
      stringBuilder.append(' ');
      stringBuilder.append(parent.getBooleanExpressionType());
      stringBuilder.append(' ');
      stringBuilder.append(parent.getRhs().toSQL());
      stringBuilder.append(" )");
   }
}
