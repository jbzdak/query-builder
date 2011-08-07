package cx.ath.jbzdak.sqlbuilder.dialect.peer;

import cx.ath.jbzdak.sqlbuilder.RenderingContext;
import cx.ath.jbzdak.sqlbuilder.SelectAllExpression;
import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfigKey;
import cx.ath.jbzdak.sqlbuilder.dialect.config.IdentifierQuotingStrategy;

/**
 * Created by: Jacek Bzdak
 */
public class SelectAllPeer extends AbstractPeer<SelectAllExpression> {

   @Override
   protected void appendToInternal(RenderingContext renderingContext, StringBuilder stringBuilder) {
       IdentifierQuotingStrategy quotingStrategy =
              (IdentifierQuotingStrategy) renderingContext.getDialect().getDialectConfig().getConfig(DialectConfigKey.TABLE_EXPRESSION_QUOTING_STRATEGY);


      if(parent.getSchema() != null && parent.getTable() == null){
         throw new IllegalArgumentException("Shema part of TableExpression is present but table is not present");
      }

      if(parent.getSchema() != null){
         stringBuilder.append(quotingStrategy.quoteIdentifier(renderingContext.getDialect(), parent.getSchema()));
         stringBuilder.append(".");
      }
      if(parent.getTable() != null){
         stringBuilder.append(quotingStrategy.quoteIdentifier(renderingContext.getDialect(), parent.getSchema()));
         stringBuilder.append(".");
      }
      stringBuilder.append("*");
   }
}
