package cx.ath.jbzdak.sqlbuilder.dialect;

import cx.ath.jbzdak.sqlbuilder.*;
import cx.ath.jbzdak.sqlbuilder.booleanExpression.BooleanFactory;
import cx.ath.jbzdak.sqlbuilder.booleanExpression.ConditionType;
import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfig;
import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfigKey;
import cx.ath.jbzdak.sqlbuilder.dialect.config.IdentifierQuotingStrategy;
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

      select.setWhere(
              BOOLEAN_FACTORY.and(
                      BOOLEAN_FACTORY.condition(ConditionType.EQUALS, dp.column("POINT_TYPE"), SQLLiteral.create(4)),
                      BOOLEAN_FACTORY.condition(ConditionType.EQUALS, dp.column("DATA_SOURCE"), SQLLiteral.create(1)),
                      BOOLEAN_FACTORY.isNotNull(dp.column("VALUE"))
                      ));
      System.out.println(select.toSQL());

   }
}
