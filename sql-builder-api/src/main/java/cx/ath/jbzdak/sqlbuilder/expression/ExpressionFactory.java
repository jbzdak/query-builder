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
import cx.ath.jbzdak.sqlbuilder.literal.StringLiteral;
import cx.ath.jbzdak.sqlbuilder.parameter.Parameter;
import org.apache.commons.lang.ObjectUtils;

import java.util.Collection;

/**
 * Created by: Jacek Bzdak
 */
public interface ExpressionFactory {

   public static final DefaultExpressionFactory BOOLEAN_FACTORY = new DefaultExpressionFactory();

   NAryBooleanExpression and(BooleanExpressionArgument... childExpressions);

   NAryBooleanExpression or(BooleanExpressionArgument... childExpressions);

   NAryExpression plus(ExpressionArgument... childExpressions);

   NAryExpression times(ExpressionArgument... childExpressions);

   NAryBooleanExpression and(Collection<? extends BooleanExpressionArgument> childExpressions);

   NAryBooleanExpression or(Collection<? extends BooleanExpressionArgument> childExpressions);

   NAryExpression plus(Collection<? extends ExpressionArgument> childExpressions);

   NAryExpression times(Collection<? extends ExpressionArgument> childExpressions);

   UnaryBooleanExpression not(BooleanExpressionArgument child);

   UnaryBooleanExpression isNull(ColumnExpression columnExpression);

   UnaryBooleanExpression isNotNull(ColumnExpression columnExpression);

   UnaryExpression minus(ExpressionArgument argument);

   UnaryExpression minus(Parameter<Number> argument);



   BinaryExpression expression(String expressionType, ExpressionArgument argument1, ExpressionArgument argument2);

   BinaryBooleanExpression condition(String conditionType, BooleanExpressionArgument argument1, BooleanExpressionArgument argument2);





   BinaryBooleanExpression like(ColumnExpression columnExpression, String pattern);

   BinaryBooleanExpression like(ColumnExpression columnExpression, Parameter<String> stringParameter);

   BinaryBooleanExpression like(ColumnExpression columnExpression, ExpressionArgument argument);




}
