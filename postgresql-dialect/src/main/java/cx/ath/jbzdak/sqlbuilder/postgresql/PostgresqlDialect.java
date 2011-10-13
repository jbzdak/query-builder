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

package cx.ath.jbzdak.sqlbuilder.postgresql;

import cx.ath.jbzdak.sqlbuilder.IntermediateSQLFactory;
import cx.ath.jbzdak.sqlbuilder.SQLPeer;
import cx.ath.jbzdak.sqlbuilder.dialect.DefaultDialect;
import cx.ath.jbzdak.sqlbuilder.dialect.QuotingManager;
import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfig;
import cx.ath.jbzdak.sqlbuilder.generic.Transformer;
import cx.ath.jbzdak.sqlbuilder.literal.DateLiteral;
import cx.ath.jbzdak.sqlbuilder.postgresql.literal.PostgresIntervalLiteral;
import cx.ath.jbzdak.sqlbuilder.postgresql.literal.PostgresqlLiteralFactory;
import cx.ath.jbzdak.sqlbuilder.postgresql.literal.PostgresqlQuotingManager;
import cx.ath.jbzdak.sqlbuilder.postgresql.peer.PostgresDatePeer;
import cx.ath.jbzdak.sqlbuilder.postgresql.peer.PostgresIntervalPeer;

import java.util.Map;

/**
 * Created by: Jacek Bzdak
 */
public class PostgresqlDialect extends DefaultDialect {

   public PostgresqlDialect(DialectConfig dialectConfig) {
      super(dialectConfig);
      this.defaultLiteralFactory = new PostgresqlLiteralFactory();
   }

   @Override
   protected Map<Class, Transformer<SQLPeer, IntermediateSQLFactory>> createTransformerMap() {
      Map<Class, Transformer<SQLPeer, IntermediateSQLFactory>> transformerMap = super.createTransformerMap();
      putPeer(transformerMap, DateLiteral.class, PostgresDatePeer.class);
      putPeer(transformerMap, PostgresIntervalLiteral.class, PostgresIntervalPeer.class);
      return transformerMap;
   }

   @Override
   public String dialectName() {
      return "psql";
   }

   @Override
   protected QuotingManager createDefaultQuotingManager() {
      return new PostgresqlQuotingManager(getDialectConfig());
   }

   @Override
   public PostgresqlQuotingManager getQuotingManager() {
      return (PostgresqlQuotingManager) super.getQuotingManager();
   }

   @Override
   public PostgresqlLiteralFactory getLiteralFactory() {
      return (PostgresqlLiteralFactory) this.defaultLiteralFactory;
   }
}
