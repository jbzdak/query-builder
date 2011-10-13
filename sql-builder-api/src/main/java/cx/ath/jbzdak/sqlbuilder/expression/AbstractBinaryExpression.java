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

package cx.ath.jbzdak.sqlbuilder.expression;

import cx.ath.jbzdak.sqlbuilder.*;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by: Jacek Bzdak
 */
public abstract class AbstractBinaryExpression extends PeerIntermediateSQLObject implements ExpressionMarker, BooleanExpressionArgument {

   public static final Pattern CONDITION_PATTERN = prepareConditionPattern();

   private static Pattern prepareConditionPattern(){
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("(.*)\\s*(");
      Iterator<? extends String> iter = BinaryExpressionType.values().iterator();
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

   protected String type;

   protected IntermediateSQLFactory rhs;

   protected IntermediateSQLFactory lhs;

   protected AbstractBinaryExpression() {}


   protected AbstractBinaryExpression(String type) {
      this.type = type;
   }

   protected AbstractBinaryExpression(String type, IntermediateSQLFactory rhs, IntermediateSQLFactory lhs) {
      this.type = type;
      this.rhs = rhs;
      this.lhs = lhs;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public IntermediateSQLFactory getRhs() {
      return rhs;
   }

   public IntermediateSQLFactory getLhs() {
      return lhs;
   }

   protected void setRhs(IntermediateSQLFactory rhs) {
      this.rhs = rhs;
   }

   protected void setLhs(IntermediateSQLFactory lhs) {
      this.lhs = lhs;
   }

   @Override
   public void collectChildren() {
      children.add(this.lhs);
      children.add(this.rhs);
      sqlParts.add(this.type);
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
      IntermediateSQLFactory oldRhs = this.rhs;
      this.rhs = rhs;
      propertyChangeSupport.firePropertyChange("rhs", oldRhs, this.rhs);
   }


   public void setRhs(SQLLiteral rhs) {
      IntermediateSQLFactory oldRhs = this.rhs;
      this.rhs = rhs;
      propertyChangeSupport.firePropertyChange("rhs", oldRhs, this.rhs);
   }

   public void setRhs(Select rhs) {
      IntermediateSQLFactory oldRhs = this.rhs;
      this.rhs = rhs;
      propertyChangeSupport.firePropertyChange("rhs", oldRhs, this.rhs);
   }

   public void setLhs(ColumnExpression lhs) {
      IntermediateSQLFactory oldLhs = this.lhs;
      this.lhs = lhs;
      propertyChangeSupport.firePropertyChange("lhs", oldLhs, this.lhs);
   }

   public void setLhs(SQLLiteral lhs) {
      IntermediateSQLFactory oldLhs = this.lhs;
      this.lhs = lhs;
      propertyChangeSupport.firePropertyChange("lhs", oldLhs, this.lhs);
   }

   public void setLhs(Select lhs) {
      IntermediateSQLFactory oldLhs = this.lhs;
      this.lhs = lhs;
      propertyChangeSupport.firePropertyChange("lhs", oldLhs, this.lhs);
   }
}
