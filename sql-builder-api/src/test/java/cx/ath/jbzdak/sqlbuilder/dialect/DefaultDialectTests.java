package cx.ath.jbzdak.sqlbuilder.dialect;

import cx.ath.jbzdak.sqlbuilder.*;
import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfig;
import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfigKey;
import cx.ath.jbzdak.sqlbuilder.dialect.config.IdentifierQuotingStrategy;
import org.junit.Test;

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

      select.addColumnExpression(new ColumnExpression("DATE"), new ColumnExpression("VALUE"));

      Alias dp = new Alias("DP");

      select.addFrom(new Table("DATA_POINT_DAILY", dp));
      select.setLimit(100);

      BooleanExpression booleanExpression = new BooleanExpression();
      booleanExpression.setBooleanExpressionType(BooleanExpressionType.EQUALS);
      booleanExpression.setLhs(dp.column("POINT_TYPE"));
      booleanExpression.setRhs(new RawString("4"));
      select.setWhere(booleanExpression);
      System.out.println(select.toSQL());

   }
}
