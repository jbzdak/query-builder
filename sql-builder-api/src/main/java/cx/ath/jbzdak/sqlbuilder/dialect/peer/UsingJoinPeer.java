package cx.ath.jbzdak.sqlbuilder.dialect.peer;

import cx.ath.jbzdak.sqlbuilder.JoinUsing;
import cx.ath.jbzdak.sqlbuilder.RenderingContext;

/**
 * Created by: Jacek Bzdak
 */
public class UsingJoinPeer extends AbstractPeer<JoinUsing>{

   @Override
   protected void appendToInternal(RenderingContext renderingContext, StringBuilder stringBuilder) {
      PeerUtils.appendJoinBegining(stringBuilder, renderingContext, parent);
      stringBuilder.append(" ON (");
      PeerUtils.join(stringBuilder, ", ", parent.getColumns());
      stringBuilder.append(" )");
   }
}
