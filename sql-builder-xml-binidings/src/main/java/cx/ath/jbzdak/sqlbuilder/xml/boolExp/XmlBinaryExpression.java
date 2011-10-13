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

import cx.ath.jbzdak.sqlbuilder.expression.BinaryBooleanExpression;
import cx.ath.jbzdak.sqlbuilder.expression.BooleanExpressionMarker;
import cx.ath.jbzdak.sqlbuilder.generic.Factory;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/**
 * Created by: Jacek Bzdak
 */
@XmlType(name = "expr")
public class XmlBinaryExpression implements Factory<BooleanExpressionMarker>{


   String value;

   public XmlBinaryExpression() {

   }

   public XmlBinaryExpression(String value) {
      this.value = value;
   }

   @XmlValue
   public String getValue() {
      return value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public BooleanExpressionMarker create() {
      BinaryBooleanExpression condition = new BinaryBooleanExpression();
      condition.parseString(value);
      return condition;
   }
}
