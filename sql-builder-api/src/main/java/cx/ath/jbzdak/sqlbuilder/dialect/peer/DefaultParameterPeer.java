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

import cx.ath.jbzdak.sqlbuilder.RenderingContext;
import cx.ath.jbzdak.sqlbuilder.SQLLiteral;
import cx.ath.jbzdak.sqlbuilder.literal.LiteralFactory;
import cx.ath.jbzdak.sqlbuilder.parameter.BoundParameter;
import cx.ath.jbzdak.sqlbuilder.parameter.DefaultParameter;
import cx.ath.jbzdak.sqlbuilder.parameter.bound.BoundDefaultParameter;

import java.util.Date;

/**
 * Created by: Jacek Bzdak
 */
public class DefaultParameterPeer extends AbstractPeer<BoundDefaultParameter>{

   @Override
   protected void appendToInternal(RenderingContext renderingContext, StringBuilder stringBuilder) {
      SQLLiteral sqlLiteral = getLiteral(renderingContext);
      sqlLiteral.setContext(renderingContext.getExpressionContext());
      sqlLiteral.appendTo(renderingContext, stringBuilder);
   }

   private SQLLiteral getLiteral(RenderingContext renderingContext){
      Object value = parent.getValue();
      LiteralFactory literalFactory = renderingContext.getDialect().getLiteralFactory();

      if (value instanceof String) {
         String str = (String) value;
         try {
            return literalFactory.create(Integer.parseInt(str));
         } catch (NumberFormatException e) {
            //pass

         }
         try {
            return literalFactory.create(Double.parseDouble(str));
         } catch (NumberFormatException e) {
            //pass
         }
         return literalFactory.create(str);
      }

      if(value instanceof Date){
         return literalFactory.createDatetime((Date)value);
      }

      if(value instanceof Number){
         Number number = (Number) value;
         if(value instanceof Long || value instanceof Integer){
            return literalFactory.create(number.intValue());
         }
         return literalFactory.create(number);
      }

      return literalFactory.create(value.toString());

   }
}
