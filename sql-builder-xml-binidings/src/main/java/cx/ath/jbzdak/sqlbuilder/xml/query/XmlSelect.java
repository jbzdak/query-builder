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
import cx.ath.jbzdak.sqlbuilder.IntermediateSQLFactory;
import cx.ath.jbzdak.sqlbuilder.Select;
import cx.ath.jbzdak.sqlbuilder.UnsafeSelect;
import cx.ath.jbzdak.sqlbuilder.expressionConfig.ExpressionConfig;
import cx.ath.jbzdak.sqlbuilder.generic.Factory;
import cx.ath.jbzdak.sqlbuilder.xml.*;
import cx.ath.jbzdak.sqlbuilder.xml.boolExp.XmlBooleanCondition;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by: Jacek Bzdak
 */
@XmlType(name = "xml-select")
public class XmlSelect extends AbstractQuery{

   List<Object> select = new ArrayList<Object>();

   List<Object> from = new ArrayList<Object>();

   XmlBooleanCondition where;

   public Select createQuery() {

      XmlQueryCollection queryCollection = XmlParsingContext.getCollection();

      ExpressionConfig config = xmlExpressionConfig.createConfig();
      ExpressionContext expressionContext = new ExpressionContext(config);
      UnsafeSelect select = new UnsafeSelect(expressionContext);
      for (Object o: this.select) {
         Factory<IntermediateSQLFactory> f = (Factory<IntermediateSQLFactory>) o;
         select.addColumnExpression(f.create());
      }
      for (Object o : from) {
         Factory<IntermediateSQLFactory> f = (Factory<IntermediateSQLFactory>) o;
         select.addFrom(f.create());
      }
      select.setWhere(where.create());
      select.addParameters(parameters.getParameters());
      return select;
   }

   @XmlElementWrapper(name = "sel")
   @XmlElements({
           @XmlElement(name = "raw", type = XmlRaw.class),
           @XmlElement(name = "col", type = XmlColumnExpression.class)
   })
   public List<Object> getSelect() {
      return select;
   }

   public void setSelect(List<Object> select) {
      this.select = select;
   }

   @XmlElementWrapper(name = "from")
   @XmlElements({
           @XmlElement(name = "table", type = XmlTable.class),
           @XmlElement(name = "raw", type = XmlRaw.class)
   })
   public List<Object> getFrom() {
      return from;
   }

   public void setFrom(List<Object> from) {
      this.from = from;
   }

   @XmlElement(name = "where")
   public XmlBooleanCondition getWhere() {
      return where;
   }


   public void setWhere(XmlBooleanCondition where) {
      this.where = where;
   }
}
