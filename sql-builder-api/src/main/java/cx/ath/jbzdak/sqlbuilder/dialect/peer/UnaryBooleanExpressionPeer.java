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
import cx.ath.jbzdak.sqlbuilder.expression.UnaryExpression;
import cx.ath.jbzdak.sqlbuilder.expression.UnaryExpressionType;

/**
 * Created by: Jacek Bzdak
 */
public class UnaryBooleanExpressionPeer extends AbstractPeer<UnaryExpression>{

   @Override
   protected void appendToInternal(final RenderingContext renderingContext, StringBuilder stringBuilder) {
      PeerUtils.appendInBrackets(stringBuilder, new PeerUtils.Appender() {
         public void appendTo(StringBuilder stringBuilder) {
            if(UnaryExpressionType.MINUS.equals(parent.getExpressionType())){
               stringBuilder.append(parent.getExpressionType());
               stringBuilder.append(" ");
               parent.getExpression().appendTo(renderingContext, stringBuilder);
            }  else{
               parent.getExpression().appendTo(renderingContext, stringBuilder);
               stringBuilder.append(" ");
               stringBuilder.append(parent.getExpressionType());
            }

         }
      });
   }
}
