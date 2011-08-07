package cx.ath.jbzdak.sqlbuilder.dialect.peer;

import com.sun.org.apache.xalan.internal.extensions.ExpressionContext;
import cx.ath.jbzdak.sqlbuilder.Alias;
import cx.ath.jbzdak.sqlbuilder.RenderingContext;
import cx.ath.jbzdak.sqlbuilder.SQLPeer;
import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfigKey;
import cx.ath.jbzdak.sqlbuilder.dialect.config.IdentifierQuotingStrategy;

import java.awt.image.renderable.RenderContext;

/**
 * Created by: Jacek Bzdak
 */
public class AliasPeer extends AbstractPeer<Alias> {


   @Override
   protected void appendToInternal(RenderingContext renderingContext, StringBuilder stringBuilder) {
       stringBuilder.append(' ');
      IdentifierQuotingStrategy quotingStrategy =
              (IdentifierQuotingStrategy) renderingContext.getDialect().getDialectConfig().getConfig(DialectConfigKey.ALIAS_QUOTING_STRATEGY);
      stringBuilder.append(quotingStrategy.quoteIdentifier(renderingContext.getDialect(), parent.getAlias()));
      stringBuilder.append(' ');
   }

   public void appendToInternal(StringBuilder stringBuilder) {

   }

}
