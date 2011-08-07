package cx.ath.jbzdak.sqlbuilder.dialect.peer;

import cx.ath.jbzdak.sqlbuilder.RenderingContext;
import cx.ath.jbzdak.sqlbuilder.Select;

/**
 * Created by: Jacek Bzdak
 */
public class SelectPeer extends AbstractPeer<Select>{

   @Override
   protected void appendToInternal(RenderingContext renderingContext, StringBuilder stringBuilder) {
      stringBuilder.append(" SELECT ");
      PeerUtils.joinSqls(renderingContext, stringBuilder, ", ", parent.getColumnExpressions());
      stringBuilder.append(" FROM ");
      PeerUtils.joinSqls(renderingContext, stringBuilder, ", ", parent.getFrom());
      if(parent.getWhere() != null){
         stringBuilder.append(" WHERE ");
         parent.getWhere().appendTo(renderingContext, stringBuilder);
      }
      if(parent.getLimit() != null){
         stringBuilder.append(" LIMIT ");
         stringBuilder.append(parent.getLimit());
      }
   }

}
