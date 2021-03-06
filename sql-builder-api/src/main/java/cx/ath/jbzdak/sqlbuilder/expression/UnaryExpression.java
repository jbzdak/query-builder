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

import cx.ath.jbzdak.sqlbuilder.*;

import java.security.InvalidParameterException;

/**
 * Created by: Jacek Bzdak
 */
public class UnaryExpression extends PeerIntermediateSQLObject implements ExpressionMarker {

   protected IntermediateSQLFactory expression;

   protected String expressionType;

   protected UnaryExpression() {
   }

   public UnaryExpression(String expressionType) {
      this(expressionType, null);
   }

   public UnaryExpression(String expressionType, IntermediateSQLFactory expression) {
      if(!UnaryExpressionType.values().contains(expressionType)){
         throw new InvalidParameterException("Unknown expression type '" + expression + "'");
      }
      this.expression = expression;
      this.expressionType = expressionType;
   }

   public IntermediateSQLFactory getExpression() {
      return expression;
   }

   public void setExpression(ColumnExpression expression) {
      IntermediateSQLFactory oldExpression = this.expression;
      this.expression = expression;
      propertyChangeSupport.firePropertyChange("expression", oldExpression, this.expression);
   }

   public void setExpression(SQLLiteral expression) {
      IntermediateSQLFactory oldExpression = this.expression;
      this.expression = expression;
      propertyChangeSupport.firePropertyChange("expression", oldExpression, this.expression);
   }

   public void setExpression(Select expression) {
      IntermediateSQLFactory oldExpression = this.expression;
      this.expression = expression;
      propertyChangeSupport.firePropertyChange("expression", oldExpression, this.expression);
   }

   public void setExpression(BooleanExpressionMarker expression) {
      IntermediateSQLFactory oldExpression = this.expression;
      this.expression = expression;
      propertyChangeSupport.firePropertyChange("expression", oldExpression, this.expression);
   }

   public String getExpressionType() {
      return expressionType;
   }

   public void setExpressionType(String expressionType) {
      this.expressionType = expressionType;
   }

}
