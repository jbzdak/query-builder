package cx.ath.jbzdak.sqlbuilder.dialect.peer;

import cx.ath.jbzdak.sqlbuilder.Table;
import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfigKey;
import cx.ath.jbzdak.sqlbuilder.dialect.config.IdentifierQuotingStrategy;

import java.security.InvalidParameterException;

/**
 * Created by: Jacek Bzdak
 */
public class TablePeer extends AbstractPeer<Table>{

   public void appendTo(StringBuilder stringBuilder) {
      if(parent.getTable() == null){
         throw new InvalidParameterException();
      }

      PeerUtils.appendIdentifier(stringBuilder, dialect, (IdentifierQuotingStrategy) dialect.getDialectConfig().getConfig(DialectConfigKey.IDENTIFIER_QUOTING_STRATEGY),
              parent.getSchema(), parent.getTable(), null, parent.getAlias());
   }
}
