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

package cx.ath.jbzdak.sqlbuilder.dialect;

import cx.ath.jbzdak.sqlbuilder.IdenitfierPart;
import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfig;
import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfigKey;
import cx.ath.jbzdak.sqlbuilder.dialect.config.InvalidConfigurationException;

import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CodingErrorAction;
import java.util.regex.Pattern;

/**
 * Created by: Jacek Bzdak
 */
public class DefaultQuotingManager extends AbstractQuotingManager {

   final DialectConfig dialectConfig;

   final String identifierQuote;

   final String identifierQuoteReplace;

   final String stringQuote;

   final String stringQuoteReplace;

   public String quoteIdentifier(CharSequence ident, IdentifierQuotingStrategy strategy, IdenitfierPart idenitfierPart) {
      if(strategy == null){
         throw  new NullPointerException("strategy parameter of Dialect.quoteIdentifier must not be null  (and is null)");
      }
      if(ident == null){
         return null;
      }

      switch (strategy){
         case NEVER:
            return ident.toString();
         case WHEN_NEEDED:
            if(!identifierNeedsQuoting(ident.toString())){
               return ident.toString();
            }
         case ALWAYS:
            return quote(ident, identifierQuote, identifierQuoteReplace);
         case DEFAULT:
            strategy =
                    (IdentifierQuotingStrategy) dialectConfig.getConfig(DialectConfigKey.IDENTIFIER_QUOTING_STRATEGY);
            if(strategy == null){
               throw new InvalidConfigurationException("Default IdentifierQuotingStrategy is null in DialectConfig");
            }
            if(strategy == IdentifierQuotingStrategy.DEFAULT){
               strategy = IdentifierQuotingStrategy.WHEN_NEEDED;
            }
            return quoteIdentifier(ident, strategy, idenitfierPart);
         default:
            throw new IllegalStateException();
      }
   }



   /**
    *
    * @param dialectConfig
    * @param identifierQuote Character that is used to quote identifiers
    * @param identifierQuoteReplace if identifier contains {@code identifierQuote} it will be treplaces with {@code identifierQuoteReplace}
    * @param stringQuote     Character that is used to quote strings
    * @param stringQuoteReplace      if identifier strings {@code stringQuote} it will be treplaces with {@code stringQuoteReplace}
    */
   public DefaultQuotingManager(DialectConfig dialectConfig, String identifierQuote, String identifierQuoteReplace, String stringQuote, String stringQuoteReplace) {
      this.dialectConfig = dialectConfig;
      this.identifierQuote = identifierQuote;
      this.identifierQuoteReplace = identifierQuoteReplace;
      this.stringQuote = stringQuote;
      this.stringQuoteReplace = stringQuoteReplace;
   }


   private String quote(CharSequence ident, String quote, String quoteReplace){
      String id = ident.toString();
      id = id.replaceAll(Pattern.quote(quote), quoteReplace);
      return quote + id + quote;
   }

   public boolean identifierNeedsQuoting(CharSequence ident, IdenitfierPart idenitfierPart) {
      try {
         Charset.forName("ASCII").newEncoder().onMalformedInput(CodingErrorAction.REPORT).onUnmappableCharacter(CodingErrorAction.REPORT).encode(CharBuffer.wrap(ident));
         return false;
      } catch (CharacterCodingException e) {
         return true;
      }
   }


   public String quoteString(CharSequence quote) {
      return quote(quote, stringQuote, stringQuoteReplace);
   }
}
