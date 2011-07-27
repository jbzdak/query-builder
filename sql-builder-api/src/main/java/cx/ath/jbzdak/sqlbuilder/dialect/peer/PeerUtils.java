package cx.ath.jbzdak.sqlbuilder.dialect.peer;

import cx.ath.jbzdak.sqlbuilder.AbstractJoin;
import cx.ath.jbzdak.sqlbuilder.Alias;
import cx.ath.jbzdak.sqlbuilder.Dialect;
import cx.ath.jbzdak.sqlbuilder.SQLFactory;
import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfigKey;
import cx.ath.jbzdak.sqlbuilder.dialect.config.IdentifierQuotingStrategy;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by: Jacek Bzdak
 */
class PeerUtils {

   static void appendIdentifier(StringBuilder stringBuilder, Dialect d, IdentifierQuotingStrategy strategy, String schema,  String table, String column, Alias alias){

      schema = strategy.quoteIdentifier(d, schema);
      table = strategy.quoteIdentifier(d, table);
      column = strategy.quoteIdentifier(d, column);

      stringBuilder.append(' ');

      if(schema != null){
         stringBuilder.append(schema);
         if(table != null){
            stringBuilder.append(".");
         }
      }
      if(table != null){
         stringBuilder.append(table);
         if(column != null){
            stringBuilder.append(".");
         }
      }
      if(column != null){
         stringBuilder.append(column);
      }

      if(alias != null){
         stringBuilder.append(" AS ");
         stringBuilder.append(alias.toSQL());
      }

      stringBuilder.append(' ');

   }

   static void appendJoinBegining(StringBuilder stringBuilder, Dialect d, AbstractJoin abstractJoin){
      stringBuilder.append(abstractJoin.getJoinType());
      stringBuilder.append(" JOIN ");
      abstractJoin.getTable().appendTo(stringBuilder);
      stringBuilder.append(' ');
   }

   static void join(StringBuilder stringBuilder, String delimiter, Collection<String> strings){
      Iterator<String> iterator = strings.iterator();
      if(iterator.hasNext()){
         stringBuilder.append(iterator.next());
      }
      while (iterator.hasNext()){
         stringBuilder.append(delimiter);
         stringBuilder.append(iterator.next());
      }
   }

   static void joinSqls(StringBuilder stringBuilder, String delimiter, Collection<? extends SQLFactory> sqls){
      Iterator<? extends SQLFactory> iterator = sqls.iterator();
      if(iterator.hasNext()){
         iterator.next().appendTo(stringBuilder);
      }
      while (iterator.hasNext()){
         stringBuilder.append(delimiter);
         iterator.next().appendTo(stringBuilder);
      }
   }
}

