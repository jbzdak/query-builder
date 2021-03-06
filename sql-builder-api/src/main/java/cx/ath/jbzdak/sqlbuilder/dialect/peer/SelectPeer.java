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

import cx.ath.jbzdak.sqlbuilder.Join;
import cx.ath.jbzdak.sqlbuilder.RenderingContext;
import cx.ath.jbzdak.sqlbuilder.Select;
import cx.ath.jbzdak.sqlbuilder.expression.BooleanExpressionMarker;
import cx.ath.jbzdak.sqlbuilder.expression.NAryBooleanExpression;
import org.apache.commons.collections.CollectionUtils;

/**
 * Created by: Jacek Bzdak
 */
public class SelectPeer extends AbstractPeer<Select>{

   @Override
   protected void appendToInternal(RenderingContext renderingContext, StringBuilder stringBuilder) {
      stringBuilder.append(" SELECT ");
      PeerUtils.joinSqls(renderingContext, stringBuilder, ", ", parent.getColumnExpressions());
      stringBuilder.append(" FROM ");
      PeerUtils.joinSqls(renderingContext, stringBuilder, ", ", parent.getFrom());
      for (Join join : parent.getJoins()) {
         join.appendTo(renderingContext, stringBuilder);
      }

      BooleanExpressionMarker where = parent.getWhere();
      boolean renderWhere = where != null;
      if(renderWhere){
         if (where instanceof NAryBooleanExpression) {
            NAryBooleanExpression expression = (NAryBooleanExpression) where;
            if(!expression.hasExpressions()){
               renderWhere = false;
            }
         }
      }

      if(renderWhere){
         stringBuilder.append(" WHERE ");
         where.appendTo(renderingContext, stringBuilder);
      }

      if(CollectionUtils.isNotEmpty(parent.getOrderByExpressions())){
         stringBuilder.append(" ORDER BY ");
         PeerUtils.joinSqls(renderingContext, stringBuilder, ", ", parent.getOrderByExpressions());
      }

      if(parent.getLimit() != null){
         stringBuilder.append(" LIMIT ");
         stringBuilder.append(parent.getLimit());
      }


   }

}
