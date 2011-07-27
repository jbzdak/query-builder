package cx.ath.jbzdak.sqlbuilder.dialect.peer;

import cx.ath.jbzdak.sqlbuilder.JoinUsing;

/**
 * Created by: Jacek Bzdak
 */
public class UsingJoinPeer extends AbstractPeer<JoinUsing>{

   public void appendTo(StringBuilder stringBuilder) {
      PeerUtils.appendJoinBegining(stringBuilder, dialect, parent);
      stringBuilder.append(" ON (");
      PeerUtils.join(stringBuilder, ", ", parent.getColumns());
      stringBuilder.append(" )");
   }
}
