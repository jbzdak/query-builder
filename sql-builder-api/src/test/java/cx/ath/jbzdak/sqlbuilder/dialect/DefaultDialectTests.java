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

import cx.ath.jbzdak.sqlbuilder.Alias;
import cx.ath.jbzdak.sqlbuilder.ColumnExpression;
import cx.ath.jbzdak.sqlbuilder.Select;
import cx.ath.jbzdak.sqlbuilder.Table;
import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfig;
import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfigKey;
import cx.ath.jbzdak.sqlbuilder.dialect.config.PrettifySQLLevel;
import cx.ath.jbzdak.sqlbuilder.expression.BinaryExpressionType;
import cx.ath.jbzdak.sqlbuilder.literal.LiteralFactory;
import cx.ath.jbzdak.sqlbuilder.parameter.DefaultParameter;
import cx.ath.jbzdak.sqlbuilder.parameter.TableParameter;
import org.junit.Test;

import static cx.ath.jbzdak.sqlbuilder.expression.ExpressionFactory.BOOLEAN_FACTORY;

/**
 * Created by: Jacek Bzdak
 */
public class DefaultDialectTests {

   DefaultDialect defaultDialect;

   LiteralFactory literalFactory;

   Select select;

   Alias dp;

   @org.junit.Before
   public void setUp() throws Exception {

      DialectConfig dialectConfig = new DialectConfig();
      dialectConfig.setConfig(DialectConfigKey.IDENTIFIER_QUOTING_STRATEGY, IdentifierQuotingStrategy.ALWAYS);
      defaultDialect = new DefaultDialect(dialectConfig);
      defaultDialect.getDialectConfig().setConfig(DialectConfigKey.PRETTIFY_SQL, PrettifySQLLevel.MULTILINE);
      literalFactory = defaultDialect.getLiteralFactory();

      select = defaultDialect.select();
      dp = new Alias("DP");
   }

   public void updateDefaultSelectProperties(){
       select.addColumnExpression(ColumnExpression.STAR());
       select.setLimit(100);
   }

   public void updateDefaultFrom(){
      select.addFrom(new Table("DATA_POINT_DAILY", dp));
   }

   public void updateWhere(){
      select.setWhere(
              BOOLEAN_FACTORY.and(
                      BOOLEAN_FACTORY.condition(BinaryExpressionType.EQUALS, dp.column("POINT_TYPE"), literalFactory.create(4)),
                      BOOLEAN_FACTORY.condition(BinaryExpressionType.EQUALS, dp.column("DATA_SOURCE"), literalFactory.create(1)),
                      BOOLEAN_FACTORY.isNotNull(dp.column("VALUE"))
                      ));
   }


   public void validate(){
       junit.framework.Assert.assertEquals("SELECT * FROM \"DATA_POINT_DAILY\" AS \"DP\" \n" +
               "\tWHERE ((\"DP\".\"POINT_TYPE\" = 4) AND (\"DP\".\"DATA_SOURCE\" = 1) AND (\"DP\".\"VALUE\" IS NOT NULL)) \n" +
               "\tLIMIT 100", select.toSQL().toString());

   }

   @Test
   public void testSelect() throws Exception {
      updateDefaultSelectProperties();
      updateWhere();
      updateDefaultFrom();

      validate();
   }


   @Test
   public void testTableParameter() throws Exception {
      updateDefaultSelectProperties();
      updateWhere();

      select.addFrom(new TableParameter("table_name", dp));

      select.setParameterValue("table_name", "DATA_POINT_DAILY");

      validate();
   }

   @Test
   public void testTableParameterAsString() throws Exception {
      updateDefaultSelectProperties();

      updateWhere();

      select.addFrom(new Table(":table_name", dp));

      select.addParameter(new TableParameter("table_name"));

      select.setParameterValue("table_name", "DATA_POINT_DAILY");

      validate();

   }

   @Test
   public void testDefaultParameter() throws Exception {

       updateDefaultSelectProperties();

      updateDefaultFrom();

      select.setWhere(
              BOOLEAN_FACTORY.and(
                      BOOLEAN_FACTORY.condition(BinaryExpressionType.EQUALS, dp.column("POINT_TYPE"), new DefaultParameter(":point_type")),
                      BOOLEAN_FACTORY.condition(BinaryExpressionType.EQUALS, dp.column("DATA_SOURCE"), new DefaultParameter("data_source")),
                      BOOLEAN_FACTORY.isNotNull(dp.column("VALUE"))
                      ));

      select.setParameterValue("point_type", "4");
      select.setParameterValue("data_source", 1);

      validate();
   }

   @Test
   public void testFoo() throws Exception {
      DialectConfig dialectConfig = new DialectConfig();

      System.out.println(dialectConfig.parseDate("2010-12-12"));

   }
}


