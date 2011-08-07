package cx.ath.jbzdak.sqlbuilder.dialect.peer;

import com.sun.org.apache.bcel.internal.generic.NEW;
import cx.ath.jbzdak.sqlbuilder.*;
import cx.ath.jbzdak.sqlbuilder.dialect.config.IdentifierQuotingStrategy;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by: Jacek Bzdak
 */
class PeerUtils {

   static void appendIdentifier(StringBuilder stringBuilder, RenderingContext renderingContext, IdentifierQuotingStrategy strategy, String schema,  String table, String column, Alias alias){

      schema = strategy.quoteIdentifier(renderingContext.getDialect(), schema);
      table = strategy.quoteIdentifier(renderingContext.getDialect(), table);
      column = strategy.quoteIdentifier(renderingContext.getDialect(), column);

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
         alias.appendTo(renderingContext, stringBuilder);
      }

      stringBuilder.append(' ');

   }

   static void appendJoinBegining(StringBuilder stringBuilder, RenderingContext renderingContext, AbstractJoin abstractJoin){
      stringBuilder.append(abstractJoin.getJoinType());
      stringBuilder.append(" JOIN ");
      abstractJoin.getTable().appendTo(renderingContext, stringBuilder);
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

   static void joinSqls(RenderingContext renderingContext, StringBuilder stringBuilder, String delimiter, Collection<? extends IntermediateSQLFactory> sqls){
      Iterator<? extends IntermediateSQLFactory> iterator = sqls.iterator();
      if(iterator.hasNext()){
         iterator.next().appendTo(renderingContext, stringBuilder);
      }
      while (iterator.hasNext()){
         stringBuilder.append(delimiter);
         iterator.next().appendTo(renderingContext, stringBuilder);
      }
   }

   public static void appendInBrackets(StringBuilder stringBuilder, Appender appender){
      stringBuilder.append(" ( ");
      appender.appendTo(stringBuilder);
      stringBuilder.append(" ) ");
   }

   public static interface Appender{
      public void appendTo(StringBuilder stringBuilder);
   }
}

