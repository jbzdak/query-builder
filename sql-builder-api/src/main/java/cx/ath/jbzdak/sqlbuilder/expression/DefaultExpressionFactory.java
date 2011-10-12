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
import cx.ath.jbzdak.sqlbuilder.SQLLiteral;
import cx.ath.jbzdak.sqlbuilder.dialect.peer.UnaryBooleanExpressionPeer;
import cx.ath.jbzdak.sqlbuilder.literal.ParameterLiteral;
import cx.ath.jbzdak.sqlbuilder.literal.StringLiteral;
import cx.ath.jbzdak.sqlbuilder.parameter.AbstractParameter;
import cx.ath.jbzdak.sqlbuilder.parameter.DefaultParameter;

import java.util.Arrays;

/**
 * Created by: Jacek Bzdak
 */
public class DefaultExpressionFactory implements ExpressionFactory {


   public NAryBooleanExpression and(BooleanExpressionMarker... childExpressions){
      return new NAryBooleanExpression(NAryExpressionType.AND, Arrays.asList(childExpressions));
   }

   public NAryBooleanExpression or(BooleanExpressionMarker... childExpressios){
      return new NAryBooleanExpression(NAryExpressionType.OR, Arrays.asList(childExpressios));
   }

   @Override
   public BinaryBooleanExpression condition(String conditionType, ColumnExpression columnExpression, ColumnExpression columnExpression1) {
      return new BinaryBooleanExpression(conditionType, columnExpression, columnExpression1);
   }

   public BooleanUnaryExpression not(BooleanExpressionMarker child){
      return new Not(child);
   }

   public BooleanUnaryExpression isNull(ColumnExpression columnExpression){
      return new BooleanUnaryExpression(UnaryExpressionType.IS_NULL, columnExpression);
   }

   public BooleanUnaryExpression isNotNull(ColumnExpression columnExpression){
      return new BooleanUnaryExpression(UnaryExpressionType.IS_NON_NULL, columnExpression);
   }

   public BinaryBooleanExpression like(ColumnExpression columnExpression, StringLiteral stringLiteral){
      return new BinaryBooleanExpression(BinaryExpressionType.LIKE, columnExpression, stringLiteral);
   }

   public BinaryBooleanExpression condition(String conditionType, ColumnExpression columnExpression, SQLLiteral<?> stringLiteral){
      return new BinaryBooleanExpression(conditionType, columnExpression, stringLiteral);
   }

   public BinaryBooleanExpression condition(String conditionType, ColumnExpression columnExpression, AbstractParameter parameterLiteral){
      return new BinaryBooleanExpression(conditionType, columnExpression, new ParameterLiteral(parameterLiteral));
   }

   public BinaryBooleanExpression condition(String conditionType, ColumnExpression columnExpression, String parameterLiteral){
      return new BinaryBooleanExpression(conditionType, columnExpression, new ParameterLiteral(new DefaultParameter(parameterLiteral)));
   }

}
