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

package cx.ath.jbzdak.sqlbuilder;

import cx.ath.jbzdak.sqlbuilder.booleanExpression.BooleanExpressionMarker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by: Jacek Bzdak
 */
public class Select extends SQLObject{

   List<IntermediateSQLFactory> columnExpressions = new ArrayList<IntermediateSQLFactory>();

   List<Table> from = new ArrayList<Table>();

   BooleanExpressionMarker where;

   Integer limit;

   public Select() {
   }

   public Select(SQLObject sqlObject) {
      super(sqlObject);
   }

   public Select(ExpressionContext expressionContext) {
      super(expressionContext);
   }

   public void addColumnExpression(ColumnExpression... columnExpression){
      columnExpressions.addAll(Arrays.asList(columnExpression));
   }

   public void addFrom(Table table){
      from.add(table);
   }

   public void addFrom(String table){
      addFrom(new Table(table));
   }


   public Integer getLimit() {
      return limit;
   }

   public void setLimit(Integer limit) {
      this.limit = limit;
   }

   public void setWhere(BooleanExpressionMarker where) {
      BooleanExpressionMarker oldWhere = this.where;
      this.where = where;
      propertyChangeSupport.firePropertyChange("where", oldWhere, this.where);
   }


   public List<IntermediateSQLFactory> getColumnExpressions() {
      return columnExpressions;
   }

   public List<Table> getFrom() {
      return from;
   }

   public BooleanExpressionMarker getWhere() {
      return where;
   }

}
