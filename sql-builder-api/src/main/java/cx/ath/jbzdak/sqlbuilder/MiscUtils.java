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

package cx.ath.jbzdak.sqlbuilder;

import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfig;
import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfigKey;
import cx.ath.jbzdak.sqlbuilder.dialect.config.PrettifySQLLevel;

import java.util.NoSuchElementException;

/**
 * Created by: Jacek Bzdak
 */
public class MiscUtils {

   public static <T> T firstNonNull(T... args){
      for (T t : args) {
         if(t!=null){
            return t;
         }
      }
      throw new NoSuchElementException();
   }

   public static String toSQL(RenderingContext renderingContext, IntermediateSQLFactory intermediateSQLFactory){
      StringBuilder builder = new StringBuilder();
      intermediateSQLFactory.appendTo(renderingContext, builder);
      return builder.toString();
   }

   static String toSQL(IntermediateSQLObject sqlFactory){
      RenderingContext renderingContext = sqlFactory.getContext().renderingContext();
      StringBuilder stringBuilder = new StringBuilder();
      sqlFactory.appendTo(renderingContext, stringBuilder);
      String sql = stringBuilder.toString();
      sql = renderingContext.replaceParams(sql);
      sql = MiscUtils.prettifySQL(sql, sqlFactory.getContext());
      return sql;
   }


   public static String prettifySQL(String sqlString, ExpressionContext expressionContext){
      DialectConfig dialectConfig = expressionContext.getDialect().getDialectConfig();
      Object config = dialectConfig.getConfig(DialectConfigKey.PRETTIFY_SQL);
      switch ((PrettifySQLLevel) config){
         case MULTILINE:
            sqlString = sqlString.replaceAll("WHERE", "\n\tWHERE");
            sqlString = sqlString.replaceAll("JOIN", "\n\tJOIN");
            sqlString = sqlString.replaceAll("((?:INNER)|(?:OUTER)|(?:LEFT)|(?:RIGHT))\\s*JOIN", "\n\t$1 JOIN");
            sqlString = sqlString.replaceAll("LIMIT", "\n\tLIMIT");
         case PRETTY:
            sqlString = sqlString.replaceAll("\\([ ]+", "(");
            sqlString = sqlString.replaceAll("[ ]+\\)", ")");
         case MINIMAL:
            sqlString = sqlString.replaceAll("\\s+,", ",");
            sqlString = sqlString.replaceAll("[ ]+", " ");
            sqlString = sqlString.replaceAll("^\\s+", "");
            sqlString = sqlString.replaceAll("\\s+$", "");
            sqlString = sqlString.replaceAll("\\([ ]+\\(", "((");
            sqlString = sqlString.replaceAll("\\)[ ]+\\)", "))");
            break;
         case NONE:
            break;
         default:
            throw new IllegalStateException();
      }
      return sqlString;
   }

   public static boolean isEmpty(String s){
      return s == null || s.trim().length() == 0;
   }
}
