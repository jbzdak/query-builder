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

import cx.ath.jbzdak.sqlbuilder.ColumnExpression;
import cx.ath.jbzdak.sqlbuilder.SQLLiteral;
import cx.ath.jbzdak.sqlbuilder.literal.ParameterLiteral;
import cx.ath.jbzdak.sqlbuilder.literal.StringLiteral;
import cx.ath.jbzdak.sqlbuilder.parameter.DefaultParameter;
import cx.ath.jbzdak.sqlbuilder.parameter.Parameter;

import java.util.Arrays;

/**
 * Created by: Jacek Bzdak
 */
public class DefaultBooleanFactory implements BooleanFactory {


   public BooleanExpressionMarker and(BooleanExpressionMarker... childExpressions){
      return new NAryBooleanExpression(NAryBooleanExpressionType.AND, Arrays.asList(childExpressions));
   }

   public BooleanExpressionMarker or(BooleanExpressionMarker... childExpressios){
      return new NAryBooleanExpression(NAryBooleanExpressionType.OR, Arrays.asList(childExpressios));
   }

   public BooleanExpressionMarker not(BooleanExpressionMarker child){
      return new Not(child);
   }

   public BooleanExpressionMarker isNull(ColumnExpression columnExpression){
      return new UnaryBooleanExpresson(UnaryBooleanExpressionType.IS_NULL, columnExpression);
   }

   public BooleanExpressionMarker isNotNull(ColumnExpression columnExpression){
      return new UnaryBooleanExpresson(UnaryBooleanExpressionType.IS_NON_NULL, columnExpression);
   }

   public BooleanExpressionMarker like(ColumnExpression columnExpression, StringLiteral stringLiteral){
      return new Condition(ConditionType.LIKE, columnExpression, stringLiteral);
   }

   public BooleanExpressionMarker condition(String conditionType, ColumnExpression columnExpression, SQLLiteral<?> stringLiteral){
      return new Condition(conditionType, columnExpression, stringLiteral);
   }

   public BooleanExpressionMarker condition(String conditionType, ColumnExpression columnExpression, Parameter parameterLiteral){
      return new Condition(conditionType, columnExpression, new ParameterLiteral(parameterLiteral));
   }

   public BooleanExpressionMarker condition(String conditionType, ColumnExpression columnExpression, String parameterLiteral){
      return new Condition(conditionType, columnExpression, new ParameterLiteral(new DefaultParameter(parameterLiteral)));
   }

}
