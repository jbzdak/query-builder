package cx.ath.jbzdak.sqlbuilder.dialect.peer;

import cx.ath.jbzdak.sqlbuilder.Select;

/**
 * Created by: Jacek Bzdak
 */
public class SelectPeer extends AbstractPeer<Select>{

   public void appendTo(StringBuilder stringBuilder) {
      stringBuilder.append(" SELECT ");
      PeerUtils.joinSqls(stringBuilder, ", ", parent.getColumnExpressions());
      stringBuilder.append(" FROM ");
      PeerUtils.joinSqls(stringBuilder, ", ", parent.getFrom());
      if(parent.getWhere() != null){
         stringBuilder.append(" WHERE ");
         parent.getWhere().appendTo(stringBuilder);
      }
      if(parent.getLimit() != null){
         stringBuilder.append(" LIMIT ");
         stringBuilder.append(parent.getLimit());
      }
   }
}
