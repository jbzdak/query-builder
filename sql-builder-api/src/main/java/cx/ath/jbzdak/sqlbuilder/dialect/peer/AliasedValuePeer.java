package cx.ath.jbzdak.sqlbuilder.dialect.peer;

import cx.ath.jbzdak.sqlbuilder.AliasedValue;
import cx.ath.jbzdak.sqlbuilder.RenderingContext;

/**
 * Created by: Jacek Bzdak
 */
public class AliasedValuePeer extends AbstractPeer<AliasedValue>{
   @Override
   protected void appendToInternal(RenderingContext renderingContext, StringBuilder stringBuilder) {
      parent.getFactory().appendTo(renderingContext, stringBuilder);
      stringBuilder.append(" AS ");
      parent.getAlias().appendTo(renderingContext, stringBuilder);
   }
}
