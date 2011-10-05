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
import cx.ath.jbzdak.sqlbuilder.IntermediateSQLFactory;
import cx.ath.jbzdak.sqlbuilder.Table;
import cx.ath.jbzdak.sqlbuilder.generic.Factory;

import javax.xml.bind.annotation.*;

/**
 * Created by: Jacek Bzdak
 */
@XmlType(name = "from")
public class XmlTable implements Factory<IntermediateSQLFactory> {

   @XmlValue
   public String table;

   @XmlAttribute
   public String schema;

   @XmlAttribute
   public String as;

   public XmlTable() {
   }

   public XmlTable(String table) {
      this.table = table;
   }

   public XmlTable(String table, String schema) {
      this.table = table;
      this.schema = schema;
   }

   public XmlTable(String table, String schema, String as) {
      this.table = table;
      this.schema = schema;
      this.as = as;
   }

   //   @XmlTransient
   public Table create(){
      return new Table(schema, table, new Alias(as));
   }



}
