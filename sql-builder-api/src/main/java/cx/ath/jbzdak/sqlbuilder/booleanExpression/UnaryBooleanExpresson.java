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

package cx.ath.jbzdak.sqlbuilder.booleanExpression;

import cx.ath.jbzdak.sqlbuilder.*;

import java.security.InvalidParameterException;
import java.util.Set;

/**
 * Created by: Jacek Bzdak
 */
public class UnaryBooleanExpresson extends IntermediateSQLObject implements BooleanExpressionMarker {

   protected IntermediateSQLFactory expression;

   protected String expressionType;

   protected UnaryBooleanExpresson() {
   }

   public UnaryBooleanExpresson(String expressionType) {
      this(expressionType, null);
   }

   public UnaryBooleanExpresson(String expressionType, IntermediateSQLFactory expression) {
      if(!UnaryBooleanExpressionType.values().contains(expressionType)){
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

   public Set<String> collectParameterNames() {
      return getContext().collectParameterNames(expression);
   }
}
