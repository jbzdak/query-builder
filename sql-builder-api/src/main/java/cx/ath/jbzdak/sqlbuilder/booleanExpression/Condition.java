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

import cx.ath.jbzdak.sqlbuilder.*;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by: Jacek Bzdak
 */
public class Condition extends AbstractBinaryBooleanExpression {

   public static final Pattern CONDITION_PATTERN = prepareConditionPattern();

   public static Pattern prepareConditionPattern(){
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("(.*)\\s*(");
      Iterator<? extends String> iter = ConditionType.values().iterator();
      while (iter.hasNext()){
         String s = iter.next();
         stringBuilder.append("(?:");
         stringBuilder.append(s);
         stringBuilder.append(")");
         if(iter.hasNext()){
            stringBuilder.append("|");
         }
      }
      stringBuilder.append(")\\s*(.*)");
      return Pattern.compile(stringBuilder.toString());
   }

   public Condition() {
   }

   Condition(String type, ColumnExpression lhs, SQLLiteral rhs) {
      super(type, rhs, lhs);
   }

   Condition(String type, SQLLiteral rhs, ColumnExpression lhs) {
      super(type, rhs, lhs);
   }

   Condition(String type, ColumnExpression rhs, ColumnExpression lhs) {

      super(type, rhs, lhs);
   }

   public void parseString(String s){
      Matcher m = CONDITION_PATTERN.matcher(s);
      if(!m.matches()){
         throw new CantMatchConditionException("Cant match condition '" + s + "'");
      }
      lhs = new RawString(m.group(1));
      rhs = new RawString(m.group(3));
      type = m.group(2);
   }

   public void setRhs(ColumnExpression rhs) {
      IntermediateSQLObject oldRhs = this.rhs;
      this.rhs = rhs;
      propertyChangeSupport.firePropertyChange("rhs", oldRhs, this.rhs);
   }


   public void setRhs(SQLLiteral rhs) {
      IntermediateSQLObject oldRhs = this.rhs;
      this.rhs = rhs;
      propertyChangeSupport.firePropertyChange("rhs", oldRhs, this.rhs);
   }

   public void setRhs(Select rhs) {
      IntermediateSQLObject oldRhs = this.rhs;
      this.rhs = rhs;
      propertyChangeSupport.firePropertyChange("rhs", oldRhs, this.rhs);
   }

   public void setLhs(ColumnExpression lhs) {
      IntermediateSQLObject oldLhs = this.lhs;
      this.lhs = lhs;
      propertyChangeSupport.firePropertyChange("lhs", oldLhs, this.lhs);
   }

   public void setLhs(SQLLiteral lhs) {
      IntermediateSQLObject oldLhs = this.lhs;
      this.lhs = lhs;
      propertyChangeSupport.firePropertyChange("lhs", oldLhs, this.lhs);
   }

   public void setLhs(Select lhs) {
      IntermediateSQLObject oldLhs = this.lhs;
      this.lhs = lhs;
      propertyChangeSupport.firePropertyChange("lhs", oldLhs, this.lhs);
   }
}
