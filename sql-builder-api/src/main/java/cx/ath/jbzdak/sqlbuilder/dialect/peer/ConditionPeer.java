package cx.ath.jbzdak.sqlbuilder.dialect.peer;


import cx.ath.jbzdak.sqlbuilder.RenderingContext;
import cx.ath.jbzdak.sqlbuilder.booleanExpression.Condition;

/**
 * Created by: Jacek Bzdak
 */
public class ConditionPeer extends AbstractPeer<Condition>{

   @Override
   protected void appendToInternal(RenderingContext renderingContext, StringBuilder stringBuilder) {
      stringBuilder.append(" (");
      parent.getLhs().appendToInternal(renderingContext, stringBuilder);
      stringBuilder.append(" ");
      stringBuilder.append(parent.getType());
      stringBuilder.append(" ");
      parent.getRhs().appendToInternal(renderingContext, stringBuilder);
      stringBuilder.append(")");
   }
}
