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

import cx.ath.jbzdak.sqlbuilder.expression.BooleanExpressionMarker;
import cx.ath.jbzdak.sqlbuilder.generic.Factory;
import cx.ath.jbzdak.sqlbuilder.xml.expression.XmlExpression;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by: Jacek Bzdak
 */
@XmlType
public class XmlBooleanCondition implements Factory<BooleanExpressionMarker>{

   Object internal;

   public XmlBooleanCondition() {

   }

   public XmlBooleanCondition(Object internal) {
      this.internal = internal;
   }

   public BooleanExpressionMarker create(){
      Factory<BooleanExpressionMarker> f = (Factory<BooleanExpressionMarker>) internal;
      return f.create();
   }


   @XmlElements({
           @XmlElement(name = "or", type = XmlOr.class),
           @XmlElement(name = "and", type = XmlAnd.class),
           @XmlElement(name = "cond", type = XmlBinaryExpression.class),
           @XmlElement(name = "is-null", type = XmlIsNull.class),
           @XmlElement(name = "expression", type = XmlExpression.class)
   })
   public Object getInternal() {
      return internal;
   }

   public void setInternal(Object internal) {
      this.internal = internal;
   }
}
