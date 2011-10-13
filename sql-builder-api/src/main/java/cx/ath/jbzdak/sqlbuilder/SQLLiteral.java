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

import cx.ath.jbzdak.sqlbuilder.expression.BooleanExpressionArgument;
import cx.ath.jbzdak.sqlbuilder.expression.ExpressionArgument;

/**
 * Created by: Jacek Bzdak
 */
public abstract class SQLLiteral<T> extends PeerIntermediateSQLObject implements ExpressionArgument, BooleanExpressionArgument{

   protected String literalType;

   protected T literalValue;

   public SQLLiteral() {
   }

   public SQLLiteral(String literalType, T literalValue) {
      this.literalType = literalType;
      this.literalValue = literalValue;
   }

   public String getLiteralType() {
      return literalType;
   }

   public T getLiteralValue() {
      return literalValue;
   }

   public void setLiteralType(String literalType) {
      this.literalType = literalType;
   }

   public void setLiteralValue(T literalValue) {
      T oldLiteralValue = this.literalValue;
      this.literalValue = literalValue;
      propertyChangeSupport.firePropertyChange("literalValue", oldLiteralValue, this.literalValue);
   }

}
