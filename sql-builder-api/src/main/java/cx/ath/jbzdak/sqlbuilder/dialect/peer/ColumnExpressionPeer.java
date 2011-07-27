package cx.ath.jbzdak.sqlbuilder.dialect.peer;

import cx.ath.jbzdak.sqlbuilder.ColumnExpression;
import cx.ath.jbzdak.sqlbuilder.SQLPeer;
import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfigKey;
import cx.ath.jbzdak.sqlbuilder.dialect.config.IdentifierQuotingStrategy;

/**
 * Created by: Jacek Bzdak
 */
public class ColumnExpressionPeer extends AbstractPeer<ColumnExpression>{

   public void appendTo(StringBuilder stringBuilder) {
      stringBuilder.append(' ');

      IdentifierQuotingStrategy quotingStrategy =
              (IdentifierQuotingStrategy) dialect.getDialectConfig().getConfig(DialectConfigKey.TABLE_EXPRESSION_QUOTING_STRATEGY);


      if(parent.getSchema() != null && parent.getTable() == null){
         throw new IllegalArgumentException("Shema part of TableExpression is present but table is not present");
      }
      if (parent.getColumn() == null){
         throw new IllegalArgumentException("There is no column specified in column expression");
      }

      PeerUtils.appendIdentifier(stringBuilder, dialect, quotingStrategy, parent.getSchema(), parent.getTable(), parent.getColumn(), parent.getAlias());

   }

}
