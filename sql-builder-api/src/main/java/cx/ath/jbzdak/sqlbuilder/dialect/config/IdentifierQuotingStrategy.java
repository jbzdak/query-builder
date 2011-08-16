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

package cx.ath.jbzdak.sqlbuilder.dialect.config;

import cx.ath.jbzdak.sqlbuilder.Dialect;
import cx.ath.jbzdak.sqlbuilder.ExpressionContext;
import cx.ath.jbzdak.sqlbuilder.RenderingContext;

/**
 * Created by: Jacek Bzdak
 */
public enum IdentifierQuotingStrategy {
   NEVER{
      @Override
      public String quoteIdentifier(Dialect d, String identifier) {
         return identifier;
      }
   },
   WHEN_NEEDED{
      @Override
      public String quoteIdentifier(Dialect d, String identifier) {
         if(identifier == null){
            return null;
         }
         if(d.identifierNeedsQuoting(identifier)){
            return ALWAYS.quoteIdentifier(d, identifier);
         }else{
            return NEVER.quoteIdentifier(d, identifier);
         }
      }
   },
   DEFAULT{
      @Override
      public String quoteIdentifier(Dialect d, String identifier) {
         throw  new AbstractMethodError();
      }
   },
   ALWAYS{
      @Override
      public String quoteIdentifier(Dialect d, String identifier) {
         if(identifier == null){
            return null;
         }
         return d.getIdentifierQuote() + identifier + d.getIdentifierQuote();
      }
   };

   /**
    *
    * @param d
    * @param identifier
    * @return NULL when identifier is null, quoted string otherwise (other code depend on this assumption)
    */
   public abstract String quoteIdentifier(Dialect d, String identifier);

   public String quoteIdentifier(ExpressionContext d, String identifier){
      return quoteIdentifier(d.getDialect(), identifier);
   }

   public String quoteIdentifier(RenderingContext d, String identifier){
      return quoteIdentifier(d.getDialect(), identifier);
   }
}
