package cx.ath.jbzdak.sqlbuilder.dialect.peer;

import cx.ath.jbzdak.sqlbuilder.JoinOn;
import cx.ath.jbzdak.sqlbuilder.RenderingContext;

/**
 * Created by: Jacek Bzdak
 */
public class OnJoinPeer extends AbstractPeer<JoinOn>{

   @Override
   protected void appendToInternal(RenderingContext renderingContext, StringBuilder stringBuilder) {
      PeerUtils.appendJoinBegining(stringBuilder, renderingContext, parent);
      stringBuilder.append(" ON ");
      parent.getOnCondition().appendToInternal(renderingContext, stringBuilder);
   }


}
