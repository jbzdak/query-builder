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

/**
 * Created by: Jacek Bzdak
 */
public abstract class SQLObject extends PeerIntermediateSQLObject implements SQLFactory {

   String name;

   String description;

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

   public SQLObject() {
   }

   protected SQLObject(ExpressionContext context) {
      super(context);
   }

   protected SQLObject(IntermediateSQLObject parent) {
      super(parent);
   }

   public Object setParameterValue(String parameterName, Object value) {
      collectParameters();
      return getContext().setParameterValue(parameterName, value);
   }

   public void addParameter(AbstractParameter p) {
      getContext().addParameter(p);
   }

   public void addParameters(Collection<? extends Parameter<?>> p) {
      getContext().addParameters(p);
   }

   //   public Dialect getDialect() {
//      return getContext().getDialect();
//   }
//
//   public DialectConfig getDialectConfig() {
//      return getDialect().getDialectConfig();
//   }

   public String toSQL() {
      return MiscUtils.toSQL(this);
   }


}
