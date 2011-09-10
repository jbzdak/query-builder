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
import cx.ath.jbzdak.sqlbuilder.dialect.config.PrettifySQLLevel;
import cx.ath.jbzdak.sqlbuilder.literal.LiteralFactory;
import cx.ath.jbzdak.sqlbuilder.parameter.TableParameter;
import org.junit.Assert;
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
      defaultDialect.getDialectConfig().setConfig(DialectConfigKey.PRETTIFY_SQL, PrettifySQLLevel.MULTILINE);

   }

   @Test
   public void testSelect() throws Exception {
      Select select = defaultDialect.select();

//      select.addColumnExpression(new ColumnExpression("DATE"), new ColumnExpression("VALUE"));

      select.addColumnExpression(ColumnExpression.STAR());

      Alias dp = new Alias("DP");

      select.addFrom(new Table("DATA_POINT_DAILY", dp));
      select.setLimit(100);

      defaultDialect.getDialectConfig().setConfig(DialectConfigKey.PRETTIFY_SQL, PrettifySQLLevel.MULTILINE);

      LiteralFactory factory = defaultDialect.getLiteralFactory();

      select.setWhere(
              BOOLEAN_FACTORY.and(
                      BOOLEAN_FACTORY.condition(ConditionType.EQUALS, dp.column("POINT_TYPE"), factory.create(4)),
                      BOOLEAN_FACTORY.condition(ConditionType.EQUALS, dp.column("DATA_SOURCE"), factory.create(1)),
                      BOOLEAN_FACTORY.isNotNull(dp.column("VALUE"))
                      ));

      Assert.assertEquals("SELECT * FROM \"DATA_POINT_DAILY\" AS \"DP\" \n" +
              "\tWHERE ((\"DP\".\"POINT_TYPE\" = 4) AND (\"DP\".\"DATA_SOURCE\" = 1) AND (\"DP\".\"VALUE\" IS NOT NULL)) \n" +
              "\tLIMIT 100", select.toSQL().toString());
      System.out.println(select.toSQL());
   }


   @Test
   public void testParameterExpansion() throws Exception {
       Select select = defaultDialect.select();

//      select.addColumnExpression(new ColumnExpression("DATE"), new ColumnExpression("VALUE"));

      select.addColumnExpression(ColumnExpression.STAR());

      Alias dp = new Alias("DP");

      select.addFrom(new Table(":table_name", dp));
      select.setLimit(100);

      select.getContext().addParameter(new TableParameter("table_name"));

      LiteralFactory factory = defaultDialect.getLiteralFactory();

      select.setWhere(
              BOOLEAN_FACTORY.and(
                      BOOLEAN_FACTORY.condition(ConditionType.EQUALS, dp.column("POINT_TYPE"), factory.create(4)),
                      BOOLEAN_FACTORY.condition(ConditionType.EQUALS, dp.column("DATA_SOURCE"), factory.create(1)),
                      BOOLEAN_FACTORY.isNotNull(dp.column("VALUE"))
                      ));

      select.getContext().setParameterValue("table_name", "DATA_POINT_DAILY");
      System.out.println(select.toSQL());
      Assert.assertEquals("SELECT * FROM \"DATA_POINT_DAILY\" AS \"DP\" \n" +
              "\tWHERE ((\"DP\".\"POINT_TYPE\" = 4) AND (\"DP\".\"DATA_SOURCE\" = 1) AND (\"DP\".\"VALUE\" IS NOT NULL)) \n" +
              "\tLIMIT 100", select.toSQL().toString());

   }
}


