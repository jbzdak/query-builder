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

import cx.ath.jbzdak.sqlbuilder.parameter.AbstractParameter;
import cx.ath.jbzdak.sqlbuilder.parameter.Parameter;

import java.util.Collection;
import java.util.Set;

/**
 * Created by: Jacek Bzdak
 */
public class SimpleQuery extends IntermediateSQLObject implements SQLFactory{

   String sql;

   String name;

   String description;

   public SimpleQuery() {
   }

   public SimpleQuery(String sql) {
      this.sql = sql;
   }

   public Set<? extends ParameterDescriptor> getParameters() {
      collectParameters();
      return getContext().getParameters();
   }

   public String getSql() {
      return sql;
   }

   public void setSql(String sql) {
      this.sql = sql;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public void appendTo(RenderingContext renderingContext, StringBuilder stringBuilder) {
      if(context == null){
         context = renderingContext.getExpressionContext();
      }
      collectChildren();
      updateContext();
      collectParameters();
      stringBuilder.append(sql);
   }

   public String toSQL() {
      return MiscUtils.toSQL(this);
   }

   public Object setParameterValue(String parameterName, Object value) {
      return context.setParameterValue(parameterName, value);
   }

   public void addParameter(Parameter p) {
      context.addParameter(p);
   }

   public void addParameters(Collection<? extends Parameter<?>> p) {
      context.addParameters(p);
   }
}
