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

import cx.ath.jbzdak.sqlbuilder.IntermediateSQLObject;
import cx.ath.jbzdak.sqlbuilder.SQLFactory;
import cx.ath.jbzdak.sqlbuilder.SQLObject;

/**
 * Created by: Jacek Bzdak
 */
public abstract class AbstractBinaryBooleanExpression extends IntermediateSQLObject implements BooleanExpressionMarker {

   protected String type;

   protected IntermediateSQLObject rhs;

   protected IntermediateSQLObject lhs;

   protected AbstractBinaryBooleanExpression() {
   }


   protected AbstractBinaryBooleanExpression(String type) {
      this.type = type;
   }

   protected AbstractBinaryBooleanExpression(String type, IntermediateSQLObject rhs, IntermediateSQLObject lhs) {
      this.type = type;
      this.rhs = rhs;
      this.lhs = lhs;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public IntermediateSQLObject getRhs() {
      return rhs;
   }

   public IntermediateSQLObject getLhs() {
      return lhs;
   }

}
