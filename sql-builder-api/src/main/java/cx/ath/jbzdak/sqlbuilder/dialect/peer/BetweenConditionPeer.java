package cx.ath.jbzdak.sqlbuilder.dialect.peer;

import cx.ath.jbzdak.sqlbuilder.booleanExpression.BetweenCondition;

/**
 * Created by: Jacek Bzdak
 */
public class BetweenConditionPeer extends AbstractPeer<BetweenCondition>{

   public void appendTo(StringBuilder stringBuilder) {
      stringBuilder.append(parent.getExpression());
      stringBuilder.append(" BETWEEN ");
      stringBuilder.append(parent.getRange().getFrom());
      stringBuilder.append(" AND ");
      stringBuilder.append(parent.getRange().getTo());
   }
}
