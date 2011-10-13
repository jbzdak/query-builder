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

package cx.ath.jbzdak.sqlbuilder.xml.query;

import cx.ath.jbzdak.sqlbuilder.ExpressionContext;
import cx.ath.jbzdak.sqlbuilder.SQLFactory;
import cx.ath.jbzdak.sqlbuilder.SimpleQuery;
import cx.ath.jbzdak.sqlbuilder.xml.XmlQueryCollection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by: Jacek Bzdak
 */
@XmlType(name = "simpleQuery")
public class XmlSimpleQuery extends AbstractQuery{

   String query;

   public XmlSimpleQuery() {
   }

   public XmlSimpleQuery(String name, String description, String query) {
      super(name, description);
      this.query = query;
   }

   @XmlElement
   public String getQuery() {
      return query;
   }

   public void setQuery(String query) {
      this.query = query;
   }

   public SQLFactory createQuery() {
      SimpleQuery simpleQuery = new SimpleQuery(query);
      ExpressionContext expressionContext = new ExpressionContext(
         xmlExpressionConfig.createConfig()
      );
      simpleQuery.setContext(expressionContext);

      simpleQuery.addParameters(parameters.getParameters());
      simpleQuery.setName(name);
      simpleQuery.setDescription(description);
      return simpleQuery;
   }
}
