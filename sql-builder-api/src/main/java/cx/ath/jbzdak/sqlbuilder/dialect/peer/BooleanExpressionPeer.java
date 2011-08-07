package cx.ath.jbzdak.sqlbuilder.dialect.peer;

import cx.ath.jbzdak.sqlbuilder.RenderingContext;
import cx.ath.jbzdak.sqlbuilder.booleanExpression.AbstractBinaryBooleanExpression;

/**
 * Created by: Jacek Bzdak
 */
public class BooleanExpressionPeer extends AbstractPeer<AbstractBinaryBooleanExpression>{


   @Override
   protected void appendToInternal(RenderingContext renderingContext, StringBuilder stringBuilder) {
      stringBuilder.append(" (");
      parent.getLhs().appendTo(renderingContext, stringBuilder);
      stringBuilder.append(' ');
      stringBuilder.append(parent.getType());
      stringBuilder.append(' ');
      parent.getRhs().appendTo(renderingContext, stringBuilder);
      stringBuilder.append(" )");
   }
}
