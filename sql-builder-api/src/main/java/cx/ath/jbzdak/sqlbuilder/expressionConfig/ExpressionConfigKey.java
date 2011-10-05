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

package cx.ath.jbzdak.sqlbuilder.expressionConfig;

import cx.ath.jbzdak.sqlbuilder.DialectHolder;

import java.util.regex.Pattern;

/**
 * Created by: Jacek Bzdak
 */
public enum ExpressionConfigKey {

   DIALECT(){
      @Override
      public Object getDefault(ExpressionConfig config) {
         return DialectHolder.getDefaultDialect();
      }
   },
   /**
    * If possible queries to ignore missing parametears by omitting parts of a query that contains such parameters.
    *
    * It is implemented currently in {@link cx.ath.jbzdak.sqlbuilder.booleanExpression.NAryBooleanExpression} ---
    * parts of expression that contain unbound parameters will be omitted.
    *
    *
    */
   IGNORE_EXPRESSIONS_WITH_UNBOUND_PARAMS(){
      @Override
      public Object getDefault(ExpressionConfig config) {
         return Boolean.FALSE;
      }
   },
   PARAMETER_REGEXP_PATTERN(){
      @Override
      public Object getDefault(ExpressionConfig config) {
         return Pattern.compile(":([\\w\\d\\-_]+)");
      }
   },
   ;

   public abstract Object getDefault(ExpressionConfig config);

}