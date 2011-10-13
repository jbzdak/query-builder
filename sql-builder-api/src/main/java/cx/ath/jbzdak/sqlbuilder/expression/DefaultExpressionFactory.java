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
import cx.ath.jbzdak.sqlbuilder.literal.ParameterLiteral;
import cx.ath.jbzdak.sqlbuilder.literal.StringLiteral;
import cx.ath.jbzdak.sqlbuilder.parameter.Parameter;

import java.util.Collection;

/**
 * Created by: Jacek Bzdak
 */
public class DefaultExpressionFactory implements ExpressionFactory {

   @Override
   public NAryBooleanExpression and(BooleanExpressionArgument... childExpressions) {
      return new NAryBooleanExpression(NAryExpressionType.AND, childExpressions);
   }

   @Override
   public NAryBooleanExpression or(BooleanExpressionArgument... childExpressions) {
      return new NAryBooleanExpression(NAryExpressionType.OR, childExpressions);
   }

   @Override
   public NAryExpression plus(ExpressionArgument... childExpressions) {
      return new NAryExpression(NAryExpressionType.PLUS, childExpressions);
   }

   @Override
   public NAryExpression times(ExpressionArgument... childExpressions) {
      return new NAryExpression(NAryExpressionType.TIMES, childExpressions);
   }

   @Override
   public NAryBooleanExpression and(Collection<? extends BooleanExpressionArgument> childExpressions) {
      return new NAryBooleanExpression(NAryExpressionType.AND, childExpressions);
   }

   @Override
   public NAryBooleanExpression or(Collection<? extends BooleanExpressionArgument> childExpressions) {
      return new NAryBooleanExpression(NAryExpressionType.OR, childExpressions);
   }

   @Override
   public NAryExpression plus(Collection<? extends ExpressionArgument> childExpressions) {
      return new NAryExpression(NAryExpressionType.PLUS, childExpressions);
   }

   @Override
   public NAryExpression times(Collection<? extends ExpressionArgument> childExpressions) {
      return new NAryExpression(NAryExpressionType.TIMES, childExpressions);
   }

   @Override
   public UnaryBooleanExpression not(BooleanExpressionArgument child) {
      return new UnaryBooleanExpression(UnaryExpressionType.NOT, child);
   }

   @Override
   public UnaryBooleanExpression isNull(ColumnExpression columnExpression) {
      return new UnaryBooleanExpression(UnaryExpressionType.IS_NULL, columnExpression);
   }

   @Override
   public UnaryBooleanExpression isNotNull(ColumnExpression columnExpression) {
      return new UnaryBooleanExpression(UnaryExpressionType.IS_NON_NULL, columnExpression);
   }

   @Override
   public UnaryExpression minus(ExpressionArgument argument) {
      return new UnaryBooleanExpression(UnaryExpressionType.IS_NON_NULL, argument);
   }

   @Override
   public UnaryExpression minus(Parameter<Number> argument) {
      return new UnaryBooleanExpression(UnaryExpressionType.IS_NON_NULL, new ParameterLiteral(argument));
   }

   @Override
   public BinaryExpression expression(String expressionType, ExpressionArgument argument1, ExpressionArgument argument2) {
      return new BinaryExpression(expressionType, argument1, argument2);
   }

   @Override
   public BinaryBooleanExpression condition(String conditionType, BooleanExpressionArgument argument1, BooleanExpressionArgument argument2) {
      return new BinaryBooleanExpression(conditionType, argument1, argument2);
   }

   @Override
    public BinaryExpression expression(String expressionType, ExpressionArgument argument1, Parameter<?> argument2) {
       return new BinaryExpression(expressionType, argument1, new ParameterLiteral(argument2));
    }

    @Override
    public BinaryBooleanExpression condition(String conditionType, BooleanExpressionArgument argument1, Parameter<?>  argument2) {
       return new BinaryBooleanExpression(conditionType, argument1, new ParameterLiteral(argument2));
    }


   @Override
   public BinaryBooleanExpression like(ColumnExpression columnExpression, String pattern) {
      return new BinaryBooleanExpression(BinaryExpressionType.LIKE,columnExpression,  new StringLiteral(pattern));
   }

   @Override
   public BinaryBooleanExpression like(ColumnExpression columnExpression, Parameter<String> stringParameter) {
      return new BinaryBooleanExpression(BinaryExpressionType.LIKE,columnExpression,  new ParameterLiteral(stringParameter));
   }

   @Override
   public BinaryBooleanExpression like(ColumnExpression columnExpression, ExpressionArgument argument) {
      return new BinaryBooleanExpression(BinaryExpressionType.LIKE,columnExpression,  argument);
   }
}
