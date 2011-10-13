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
import cx.ath.jbzdak.sqlbuilder.expression.NAryBooleanExpression;
import cx.ath.jbzdak.sqlbuilder.generic.Factory;
import cx.ath.jbzdak.sqlbuilder.xml.expression.XmlExpression;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by: Jacek Bzdak
 */
public class XmlNargBooleanExpression {


   private List<Object> operands =  new ArrayList<Object>();

   @XmlElements({
           @XmlElement(name = "or", type = XmlOr.class),
           @XmlElement(name = "and", type = XmlAnd.class),
           @XmlElement(name = "cond", type = XmlBinaryExpression.class),
           @XmlElement(name = "is-null", type = XmlIsNull.class),
           @XmlElement(name = "expression", type = XmlExpression.class)
   })
   public List<Object> getOperands() {
      return operands;
   }

   public void setOperands(List<Object> operands) {
      this.operands = operands;
   }

   public BooleanExpressionMarker create(String type) {
      NAryBooleanExpression expression = new NAryBooleanExpression(type);
      for (Object o : getOperands()) {
         Factory<BooleanExpressionMarker> f = (Factory<BooleanExpressionMarker>) o;
         expression.addExpression(f.create());
      }
      return expression;

   }
}
