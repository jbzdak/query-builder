package cx.ath.jbzdak.sqlbuilder.dialect.peer;

import cx.ath.jbzdak.sqlbuilder.Alias;
import cx.ath.jbzdak.sqlbuilder.SQLPeer;
import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfigKey;
import cx.ath.jbzdak.sqlbuilder.dialect.config.IdentifierQuotingStrategy;

/**
 * Created by: Jacek Bzdak
 */
public class AliasPeer extends AbstractPeer<Alias> {


   public void appendTo(StringBuilder stringBuilder) {
      stringBuilder.append(' ');
      IdentifierQuotingStrategy quotingStrategy =
              (IdentifierQuotingStrategy) dialect.getDialectConfig().getConfig(DialectConfigKey.ALIAS_QUOTING_STRATEGY);
      stringBuilder.append(quotingStrategy.quoteIdentifier(dialect, parent.getAlias()));
      stringBuilder.append(' ');
   }
}
