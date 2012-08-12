package cx.ath.jbzdak.sqlbuilder.dialect.peer;

import cx.ath.jbzdak.sqlbuilder.OrderByDirection;
import cx.ath.jbzdak.sqlbuilder.OrderByExpression;
import cx.ath.jbzdak.sqlbuilder.RenderingContext;

/**
 * Author: Jacek Bzdak <jbzdak@gmail.com>
 */

public class OrderByExpressionPeer extends AbstractPeer<OrderByExpression> {

   @Override
   protected void appendToInternal(RenderingContext renderingContext, StringBuilder stringBuilder) {
      parent.getExpression().appendTo(renderingContext, stringBuilder);
//      stringBuilder.append(' ');
      if(parent.getDirection() == OrderByDirection.ASCENDING){
         stringBuilder.append(" ASC");
      }else{
         stringBuilder.append(" DSC");
      }

   }
}
