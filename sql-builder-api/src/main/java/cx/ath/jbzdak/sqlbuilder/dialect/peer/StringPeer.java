package cx.ath.jbzdak.sqlbuilder.dialect.peer;

import cx.ath.jbzdak.sqlbuilder.RenderingContext;
import cx.ath.jbzdak.sqlbuilder.literal.StringLiteral;

/**
 * Created by: Jacek Bzdak
 */
public class StringPeer extends AbstractPeer<StringLiteral>{

   @Override
   protected void appendToInternal(RenderingContext renderingContext, StringBuilder stringBuilder) {
      stringBuilder.append(' ');
      stringBuilder.append(renderingContext.getStringQuote());
      stringBuilder.append(parent.getLiteralValue());
      stringBuilder.append(renderingContext.getStringQuote());
      stringBuilder.append(' ');
   }

}
