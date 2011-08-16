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

/**
 * Created by: Jacek Bzdak
 */
public class Condition extends AbstractBinaryBooleanExpression {


   public Condition() {
   }

   Condition(String type, ColumnExpression lhs, SQLLiteral rhs) {
       super(type, rhs, lhs);
   }

   Condition(String type, SQLLiteral rhs, ColumnExpression lhs) {
       super(type, rhs, lhs);
   }

   Condition(String type, ColumnExpression rhs, ColumnExpression lhs) {
       super(type, rhs, lhs);
   }

   public void setLhs(ColumnExpression lhs){
      this.lhs = lhs;
   }

   public void setLhs(SQLLiteral lhs){
      this.lhs = lhs;
   }

    public void setRhs(ColumnExpression rhs){
      this.rhs = lhs;
   }

   public void setRhs(SQLLiteral rhs){
      this.rhs = lhs;
   }
}
