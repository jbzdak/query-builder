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

import cx.ath.jbzdak.sqlbuilder.IdenitfierPart;
import cx.ath.jbzdak.sqlbuilder.dialect.AbstractQuotingManager;
import cx.ath.jbzdak.sqlbuilder.dialect.IdentifierQuotingStrategy;
import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfig;
import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfigKey;
import cx.ath.jbzdak.sqlbuilder.postgresql.config.PostgreSQLConfig;
import cx.ath.jbzdak.sqlbuilder.postgresql.config.StringQuotingStyle;
import cx.ath.jbzdak.sqlbuilder.postgresql.utils.QuotingUtils;

import java.util.regex.Pattern;

/**
 * Created by: Jacek Bzdak
 */
public class PostgresqlQuotingManager extends AbstractQuotingManager{

   public static final Pattern UPPERCASE = Pattern.compile("\\p{javaUpperCase}");

   final DialectConfig dialectConfig;

   final PostgresStringQuoting postgresStringQuoting;

   public PostgresqlQuotingManager(DialectConfig dialectConfig) {
      this.dialectConfig = dialectConfig;
      postgresStringQuoting = new PostgresStringQuoting(dialectConfig);
   }

   public String quoteIdentifier(CharSequence ident, IdentifierQuotingStrategy strategy, IdenitfierPart idenitfierPart) {
      switch (strategy){
         case DEFAULT:
            IdentifierQuotingStrategy def = (IdentifierQuotingStrategy) dialectConfig.getConfig(DialectConfigKey.IDENTIFIER_QUOTING_STRATEGY);
            if(def==null){
               def = IdentifierQuotingStrategy.WHEN_NEEDED;
            }
            return quoteIdentifier(ident, def, idenitfierPart);
         case NEVER:
            return ident.toString();
         case WHEN_NEEDED:
            if(!identifierNeedsQuoting(ident, idenitfierPart)){
               return ident.toString();
            }
         case ALWAYS:
            String unicodeQuoteChar = dialectConfig.get(PostgreSQLConfig.UNICODE_QUOTE_CHAR);
            return QuotingUtils.quoteUnicode(ident, unicodeQuoteChar, "\"");
         default:
            throw new IllegalStateException();
      }

   }

   public boolean identifierNeedsQuoting(CharSequence ident, IdenitfierPart idenitfierPart) {
      return QuotingUtils.containsNonAsciiChars(ident) || UPPERCASE.matcher(ident).find();
   }

   public String quoteString(CharSequence quote) {
      return postgresStringQuoting.quoteString(quote);
   }

   public String quoteString(CharSequence quote, StringQuotingStyle stringQuotingStyle) {
      return postgresStringQuoting.quoteString(quote, stringQuotingStyle);
   }
}

