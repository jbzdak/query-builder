/*
 * Copyright (c) 2011 for Jacek Bzdak
 *
 * This file is part of query builder.
 *
 * Query builder is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Query builder is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Query builder.  If not, see <http://www.gnu.org/licenses/>.
 */

package cx.ath.jbzdak.sqlbuilder.dialect.peer;

import cx.ath.jbzdak.sqlbuilder.*;
import cx.ath.jbzdak.sqlbuilder.dialect.IdentifierQuotingStrategy;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by: Jacek Bzdak
 */
class PeerUtils {

   static void appendIdentifier(StringBuilder stringBuilder, RenderingContext renderingContext, IdentifierQuotingStrategy strategy, String schema,  String table, String column, Alias alias){

      schema = renderingContext.quoteIdentifier(schema, strategy);
      table = renderingContext.quoteIdentifier(table, strategy);
      column =  renderingContext.quoteIdentifier(column, strategy);

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

