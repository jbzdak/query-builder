package cx.ath.jbzdak.sqlbuilder.dialect.peer;

import cx.ath.jbzdak.sqlbuilder.GenericFunction;
import cx.ath.jbzdak.sqlbuilder.RenderingContext;

/**
 * Created by: Jacek Bzdak
 */
public class FunctionPeer extends AbstractPeer<GenericFunction>{

   public FunctionPeer() {
   }

   @Override
   protected void appendToInternal(RenderingContext renderingContext, StringBuilder stringBuilder) {
      stringBuilder.append(parent.getFunctionName());
      stringBuilder.append("(");
      PeerUtils.joinSqls(renderingContext, stringBuilder, ",", parent.getArguments());
      stringBuilder.append(")");
   }
}
