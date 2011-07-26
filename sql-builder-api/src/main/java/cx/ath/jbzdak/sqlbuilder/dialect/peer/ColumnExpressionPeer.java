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
      String schema = quotingStrategy.quoteIdentifier(dialect, parent.getSchema());
      String table = quotingStrategy.quoteIdentifier(dialect, parent.getTable());
      String column = quotingStrategy.quoteIdentifier(dialect, parent.getColumn());


      if(schema != null){
         stringBuilder.append(schema);
         stringBuilder.append(".");
      }
      if(column == null){
         if(stringBuilder.length() == 0){
            throw new IllegalArgumentException("Shema part of TableExpression is present but table is not present");
         }
      }
      stringBuilder.

   }

   public StringBuilder toSQL() {
      //TODO do some error checking like if parent.getShema != null thebn parent.getTable != null

   }

}
