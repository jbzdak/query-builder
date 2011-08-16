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

package cx.ath.jbzdak.sqlbuilder.dialect;

import cx.ath.jbzdak.sqlbuilder.*;
import cx.ath.jbzdak.sqlbuilder.booleanExpression.ConditionType;
import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfig;
import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfigKey;
import cx.ath.jbzdak.sqlbuilder.dialect.config.IdentifierQuotingStrategy;
import cx.ath.jbzdak.sqlbuilder.dialect.config.PrettifySQLLevel;
import org.junit.Test;

import static cx.ath.jbzdak.sqlbuilder.booleanExpression.BooleanFactory.BOOLEAN_FACTORY;

/**
 * Created by: Jacek Bzdak
 */
public class DefaultDialectTests {

   DefaultDialect defaultDialect;

   @org.junit.Before
   public void setUp() throws Exception {

      DialectConfig dialectConfig = new DialectConfig();
      dialectConfig.setConfig(DialectConfigKey.IDENTIFIER_QUOTING_STRATEGY, IdentifierQuotingStrategy.ALWAYS);
      defaultDialect = new DefaultDialect(dialectConfig);

   }

   @Test
   public void testSelect() throws Exception {
      Select select = defaultDialect.select();

//      select.addColumnExpression(new ColumnExpression("DATE"), new ColumnExpression("VALUE"));

      select.addColumnExpression(ColumnExpression.STAR);

      Alias dp = new Alias("DP");

      select.addFrom(new Table("DATA_POINT_DAILY", dp));
      select.setLimit(100);

      defaultDialect.getDialectConfig().setConfig(DialectConfigKey.PRETTIFY_SQL, PrettifySQLLevel.MULTILINE);

      select.setWhere(
              BOOLEAN_FACTORY.and(
                      BOOLEAN_FACTORY.condition(ConditionType.EQUALS, dp.column("POINT_TYPE"), SQLLiteral.create(4)),
                      BOOLEAN_FACTORY.condition(ConditionType.EQUALS, dp.column("DATA_SOURCE"), SQLLiteral.create(1)),
                      BOOLEAN_FACTORY.isNotNull(dp.column("VALUE"))
                      ));
      System.out.println(select.toSQLInternal());

   }
}
