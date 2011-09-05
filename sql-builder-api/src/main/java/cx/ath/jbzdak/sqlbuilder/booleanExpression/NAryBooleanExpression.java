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

package cx.ath.jbzdak.sqlbuilder.booleanExpression;

import cx.ath.jbzdak.sqlbuilder.SQLObject;

import java.security.InvalidParameterException;
import java.util.*;

/**
 * Created by: Jacek Bzdak
 */
public class NAryBooleanExpression extends SQLObject implements BooleanExpressionMarker {


   protected List<BooleanExpressionMarker> expressions = new ArrayList<BooleanExpressionMarker>();

   protected String type;

   public NAryBooleanExpression(String type) {
      this(type, Collections.<BooleanExpressionMarker>emptyList());
   }

   public NAryBooleanExpression(String type, Collection<BooleanExpressionMarker> expressions) {
      if(!NAryBooleanExpressionType.FAKE_ENUM.values().contains(type)){
         throw new InvalidParameterException("Unknown boolean expression type");
      }
      this.expressions.addAll(expressions);
      this.type = type;
   }

   public String getType() {
      return type;
   }

   public List<BooleanExpressionMarker> getExpressions() {
      return Collections.unmodifiableList(expressions);
   }

   public void setExpressions(List<BooleanExpressionMarker> expressions) {
      this.expressions = new ArrayList<BooleanExpressionMarker>(expressions);
   }

   public void setSqlFactories(BooleanExpressionMarker... exressions) {
      this.expressions = new ArrayList<BooleanExpressionMarker>(expressions);
   }

   public Set<String> collectParameterNames() {
      return getContext().collectParameterNames(expressions);
   }
}



