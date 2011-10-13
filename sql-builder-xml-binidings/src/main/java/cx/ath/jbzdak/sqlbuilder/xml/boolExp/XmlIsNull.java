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

package cx.ath.jbzdak.sqlbuilder.xml.boolExp;

import cx.ath.jbzdak.sqlbuilder.IntermediateSQLFactory;
import cx.ath.jbzdak.sqlbuilder.expression.BooleanExpressionMarker;
import cx.ath.jbzdak.sqlbuilder.expression.UnaryBooleanExpression;
import cx.ath.jbzdak.sqlbuilder.expression.UnaryExpressionType;
import cx.ath.jbzdak.sqlbuilder.expression.UnaryExpression;
import cx.ath.jbzdak.sqlbuilder.generic.Factory;
import cx.ath.jbzdak.sqlbuilder.xml.XmlColumnExpression;
import cx.ath.jbzdak.sqlbuilder.xml.XmlRaw;

import javax.xml.bind.annotation.*;

/**
 * Created by: Jacek Bzdak
 */
@XmlType(name = "is-null")
public class XmlIsNull implements Factory<BooleanExpressionMarker>{

   private boolean not;

   private Object object;

   public XmlIsNull() {
   }

   public XmlIsNull(boolean not, Object object) {
      this.not = not;
      this.object = object;
   }

   public BooleanExpressionMarker create() {
      Factory<IntermediateSQLFactory> factory  = (Factory<IntermediateSQLFactory>) object;
      String type = UnaryExpressionType.IS_NULL;
      if(not){
         type = UnaryExpressionType.IS_NON_NULL;
      }
      return new UnaryBooleanExpression(type, factory.create());
   }

   @XmlAttribute
   public boolean isNot() {
      return not;
   }

   public void setNot(boolean not) {
      this.not = not;
   }

   @XmlElements({
      @XmlElement(name = "col", type = XmlColumnExpression.class),
      @XmlElement(name = "raw", type = XmlRaw.class)
   })
   public Object getObject() {
      return object;
   }

   public void setObject(Object object) {
      this.object = object;
   }
}
