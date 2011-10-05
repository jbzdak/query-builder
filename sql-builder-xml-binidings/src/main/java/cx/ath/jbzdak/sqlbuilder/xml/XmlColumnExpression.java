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

      package cx.ath.jbzdak.sqlbuilder.xml;

import cx.ath.jbzdak.sqlbuilder.Alias;
import cx.ath.jbzdak.sqlbuilder.ColumnExpression;
import cx.ath.jbzdak.sqlbuilder.IntermediateSQLFactory;
import cx.ath.jbzdak.sqlbuilder.generic.Factory;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/**
 * Created by: Jacek Bzdak
 */
@XmlType(name = "col")
public class XmlColumnExpression implements Factory<IntermediateSQLFactory>{

   public XmlColumnExpression() {
   }

   public XmlColumnExpression(String column) {
      this.column = column;
   }

   public XmlColumnExpression(String column, String table, String schema) {
      this.column = column;
      this.table = table;
      this.schema = schema;
   }

   public XmlColumnExpression(String column, String table, String schema, String as) {
      this.column = column;
      this.table = table;
      this.schema = schema;
      this.as = as;
   }

   @XmlValue()
   public String column;

   @XmlAttribute
   public String table;

   @XmlAttribute
   public String schema;

   @XmlAttribute()
   public String as;


   public ColumnExpression create(){
      return new ColumnExpression(
              new Alias(as),
              column,
              table,
              schema
      );
   }

}
