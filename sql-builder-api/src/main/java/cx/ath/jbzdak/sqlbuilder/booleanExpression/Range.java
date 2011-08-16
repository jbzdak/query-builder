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

import cx.ath.jbzdak.sqlbuilder.SQLLiteral;
import cx.ath.jbzdak.sqlbuilder.SQLObject;
import cx.ath.jbzdak.sqlbuilder.literal.LiteralFactory;

/**
 * Created by: Jacek Bzdak
 */
public class Range extends SQLObject{

   SQLLiteral from;

   SQLLiteral to;

   public Range() {
   }

   public Range(SQLObject parent, int from, int to) {
      super(parent);
      LiteralFactory literalFactory = this.getExpressionContext().getDialect().getLiteralFactory();
      this.from = literalFactory.create(from);
      this.to = literalFactory.create(to);
   }

   public Range(SQLObject parent, float from, float to) {
      super(parent);
      LiteralFactory literalFactory = this.getExpressionContext().getDialect().getLiteralFactory(); ;
      this.from = literalFactory.create(from);
      this.to = literalFactory.create(to);
   }

   public Range(SQLObject parent, double from, double to) {
      super(parent);
      LiteralFactory literalFactory = this.getExpressionContext().getDialect().getLiteralFactory();
      this.from = literalFactory.create(from);
      this.to = literalFactory.create(to);
   }

   public Range(SQLLiteral to, SQLLiteral from) {
      this.to = to;
      this.from = from;
   }


   public SQLLiteral getFrom() {
      return from;
   }

   public void setFrom(SQLLiteral from) {
      this.from = from;
   }

   public SQLLiteral getTo() {
      return to;
   }

   public void setTo(SQLLiteral to) {
      this.to = to;
   }
}
