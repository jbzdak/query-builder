package cx.ath.jbzdak.sqlbuilder.dialect.peer;

import cx.ath.jbzdak.sqlbuilder.RenderingContext;
import cx.ath.jbzdak.sqlbuilder.booleanExpression.BetweenCondition;

/**
 * Created by: Jacek Bzdak
 */
public class BetweenConditionPeer extends AbstractPeer<BetweenCondition>{

   @Override
   protected void appendToInternal(RenderingContext renderingContext, StringBuilder stringBuilder) {
      stringBuilder.append(parent.getExpression().toSQL(renderingContext));
      stringBuilder.append(" BETWEEN ");
      stringBuilder.append(parent.getRange().getFrom().toSQL(renderingContext));
      stringBuilder.append(" AND ");
      stringBuilder.append(parent.getRange().getTo().toSQL(renderingContext));
   }

   public void appendToInternal(StringBuilder stringBuilder) {

   }
}
