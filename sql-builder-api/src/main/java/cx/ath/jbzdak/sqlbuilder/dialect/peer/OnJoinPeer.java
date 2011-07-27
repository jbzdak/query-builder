package cx.ath.jbzdak.sqlbuilder.dialect.peer;

import cx.ath.jbzdak.sqlbuilder.JoinOn;

/**
 * Created by: Jacek Bzdak
 */
public class OnJoinPeer extends AbstractPeer<JoinOn>{

   public void appendTo(StringBuilder stringBuilder) {
      PeerUtils.appendJoinBegining(stringBuilder, dialect, parent);
      stringBuilder.append(" ON ");
      parent.getOnCondition().appendTo(stringBuilder);
   }

}
