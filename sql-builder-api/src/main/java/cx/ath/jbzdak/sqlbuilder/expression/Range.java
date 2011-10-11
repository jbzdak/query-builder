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

import cx.ath.jbzdak.sqlbuilder.PeerIntermediateSQLObject;
import cx.ath.jbzdak.sqlbuilder.SQLLiteral;
import cx.ath.jbzdak.sqlbuilder.SQLObject;
import cx.ath.jbzdak.sqlbuilder.literal.LiteralFactory;

/**
 * Created by: Jacek Bzdak
 */
public class Range extends PeerIntermediateSQLObject{

   private SQLLiteral from;

   private SQLLiteral to;

   public Range() {
   }

   public Range(SQLObject parent, int from, int to) {
      super(parent);
      LiteralFactory literalFactory = this.getContext().getDialect().getLiteralFactory();
      this.setFrom(literalFactory.create(from));
      this.setTo(literalFactory.create(to));
   }

   public Range(SQLObject parent, float from, float to) {
      super(parent);
      LiteralFactory literalFactory = this.getContext().getDialect().getLiteralFactory(); ;
      this.setFrom(literalFactory.create(from));
      this.setTo(literalFactory.create(to));
   }

   public Range(SQLObject parent, double from, double to) {
      super(parent);
      LiteralFactory literalFactory = this.getContext().getDialect().getLiteralFactory();
      this.setFrom(literalFactory.create(from));
      this.setTo(literalFactory.create(to));
   }

   public Range(SQLLiteral to, SQLLiteral from) {
      this.setTo(to);
      this.setFrom(from);
   }


   public SQLLiteral getFrom() {
      return from;
   }



   public SQLLiteral getTo() {
      return to;
   }

   public void setFrom(SQLLiteral from) {
      SQLLiteral oldFrom = this.from;
      this.from = from;
      propertyChangeSupport.firePropertyChange("from", oldFrom, this.from);
   }

   public void setTo(SQLLiteral to) {
      SQLLiteral oldTo = this.to;
      this.to = to;
      propertyChangeSupport.firePropertyChange("to", oldTo, this.to);
   }


}
