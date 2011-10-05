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

/**
 * Created by: Jacek Bzdak
 */
public abstract class AbstractJoin extends PeerIntermediateSQLObject {

   String joinType;

   Table table;

   protected AbstractJoin() {
   }

   public AbstractJoin(String joinType, Table table) {
      this.joinType = joinType;
      this.table = table;
   }

   public String getJoinType() {
      return joinType;
   }

   public Table getTable() {
      return table;
   }

   public void setJoinType(String joinType) {
      this.joinType = joinType;
   }

   public void setTable(Table table) {
      this.table = table;
   }
}
