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

package cx.ath.jbzdak.sqlbuilder.expression;

import cx.ath.jbzdak.sqlbuilder.ColumnExpression;
import cx.ath.jbzdak.sqlbuilder.PeerIntermediateSQLObject;

/**
 * Created by: Jacek Bzdak
 */
public class BetweenCondition extends PeerIntermediateSQLObject implements BooleanExpressionMarker{

   Range range;

   ColumnExpression expression;

   public BetweenCondition() {
   }

   public BetweenCondition(Range range, ColumnExpression expression) {
      this.range = range;
      this.expression = expression;
   }

   public void setRange(Range range) {
      Range oldRange = this.range;
      this.range = range;
      propertyChangeSupport.firePropertyChange("range", oldRange, this.range);
   }

   public void setExpression(ColumnExpression expression) {
      ColumnExpression oldExpression = this.expression;
      this.expression = expression;
      propertyChangeSupport.firePropertyChange("expression", oldExpression, this.expression);
   }

   public Range getRange() {
      return range;
   }

   public ColumnExpression getExpression() {
      return expression;
   }
}

