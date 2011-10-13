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

package cx.ath.jbzdak.sqlbuilder.xml;

import cx.ath.jbzdak.sqlbuilder.*;
import cx.ath.jbzdak.sqlbuilder.generic.Factory;
import cx.ath.jbzdak.sqlbuilder.xml.boolExp.XmlBooleanCondition;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

/**
 * Created by: Jacek Bzdak
 */
public class XmlJoin implements Factory<IntermediateSQLFactory>{

   private String joinType = JoinType.INNER;

   private XmlTable xmlTable;

   private String table;

   private Object joinCondition;

   public Join create() {

      Join join;

      if (joinCondition instanceof XmlJoinUsing) {
         join = createJoinUsing();
      }else{
         join = createJoinOn();
      }

      join.setJoinType(joinType.toUpperCase());
      if(xmlTable != null){
         join.setTable(xmlTable.create());
      }else{
         if(MiscUtils.isEmpty(table)){
            throw new InvalidQueryXmlException(
                    "Must specify either table attribute or table child element of join condition"
            );
         }
         join.setTable(new Table(table));
      }

      return null;  //To change body of implemented methods use File | Settings | File Templates.
   }

   private JoinOn createJoinOn(){
      JoinOn joinOn = new JoinOn();

      XmlBooleanCondition condition = (XmlBooleanCondition) joinCondition;

      joinOn.setOnCondition(condition.create());
      return joinOn;
   }

   private JoinUsing createJoinUsing(){
      JoinUsing joinUsing = new JoinUsing();

      XmlJoinUsing xmlJoinUsing = new XmlJoinUsing();

      joinUsing.setColumns(xmlJoinUsing.getXmlList());

      return joinUsing;
   }



   @XmlAttribute(name = "type")
   public String getJoinType() {
      if(joinType == null){
         return null;
      }
      return joinType.toLowerCase();
   }

   public void setJoinType(String joinType) {
      this.joinType = joinType;
   }

   @XmlElement(name = "table")
   public XmlTable getXmlTable() {
      return xmlTable;
   }

   public void setXmlTable(XmlTable xmlTable) {
      this.xmlTable = xmlTable;
   }

   @XmlAttribute
   public String getTable() {
      return table;
   }

   public void setTable(String table) {
      this.table = table;
   }

    @XmlElements({
           @XmlElement(name = "on", type = XmlBooleanCondition.class),
           @XmlElement(name = "using", type = XmlJoinUsing.class)
   })
   public Object getJoinCondition() {
      return joinCondition;
   }

   public void setJoinCondition(Object joinCondition) {
      this.joinCondition = joinCondition;
   }
}
