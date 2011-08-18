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

import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfig;

/**
 * Created by: Jacek Bzdak
 */
public abstract class SQLObject extends IntermediateSQLObject implements SQLFactory {

   ExpressionContext expressionContext;

   public SQLObject() {
   }

   public SQLObject(SQLObject parent){
      expressionContext = parent.expressionContext;
   }

   public SQLObject(ExpressionContext expressionContext) {
      this.expressionContext = expressionContext;
   }

   public ExpressionContext getExpressionContext() {
      return expressionContext;
   }

   public Dialect getDialect() {
      return expressionContext.getDialect();
   }

   public DialectConfig getDialectConfig() {
      return getDialect().getDialectConfig();
   }

   public StringBuilder toSQL() {
      RenderingContext renderingContext = expressionContext.renderingContext();
      String sql = toSQLInternal(renderingContext).toString();
      sql = renderingContext.replaceParams(sql);
      sql = MiscUtils.prettifySQL(sql, expressionContext);
      return new StringBuilder(sql);
   }


   public StringBuilder toSQLInternal(RenderingContext renderingContext) {
      StringBuilder stringBuilder = new StringBuilder();
      appendToInternal(renderingContext, stringBuilder);
      return stringBuilder;
   }

}
