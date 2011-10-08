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

package cx.ath.jbzdak.sqlbuilder.postgresql;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import com.sun.org.apache.xpath.internal.operations.Quo;
import cx.ath.jbzdak.sqlbuilder.InvalidParameterException;
import cx.ath.jbzdak.sqlbuilder.dialect.DefaultQuotingManager;
import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfig;
import cx.ath.jbzdak.sqlbuilder.postgresql.config.PostgreSQLConfig;
import cx.ath.jbzdak.sqlbuilder.postgresql.config.StringQuotingStyle;
import cx.ath.jbzdak.sqlbuilder.postgresql.utils.QuotingUtils;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by: Jacek Bzdak
 */
public class PostgresStringQuoting {

   public PostgresStringQuoting(DialectConfig dialectConfig) {
      this.dialectConfig = dialectConfig;
   }

   final DialectConfig dialectConfig;

   public String quoteString(CharSequence quote) {
      return quoteString(quote, StringQuotingStyle.DEFAULT);
   }

   public String quoteString(CharSequence quote, StringQuotingStyle stringQuotingStyle) {
      switch (stringQuotingStyle){
         case DEFAULT:
            StringQuotingStyle style = dialectConfig.get(PostgreSQLConfig.STRING_QUOTING_STYLE);
            if(style == StringQuotingStyle.DEFAULT){
               style = StringQuotingStyle.UNICODE_PREFER_SINGLE_QUOTES;
            }
            return quoteString(quote, style);
         case SINGLE_QUOTES:
            return DefaultQuotingManager.quote(quote, "'", "''");
         case UNICODE_PREFER_SINGLE_QUOTES:
            if(!QuotingUtils.containsNonAsciiChars(quote)){
               return DefaultQuotingManager.quote(quote, "'", "''");
            }
         case UNICODE:
            String quoteChar = dialectConfig.get(PostgreSQLConfig.UNICODE_QUOTE_CHAR);
            return QuotingUtils.quoteUnicode(quote, quoteChar, "'");
         default:
            throw  new IllegalStateException();
      }
   }

}
