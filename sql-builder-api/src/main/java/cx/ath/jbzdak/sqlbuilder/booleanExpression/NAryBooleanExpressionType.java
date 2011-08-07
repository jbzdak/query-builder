package cx.ath.jbzdak.sqlbuilder.booleanExpression;

import fakeEnum.FakeEnum;

/**
 * Created by: Jacek Bzdak
 */
public class NAryBooleanExpressionType {

   public static final String AND = "AND";

   public static final String OR = "OR";

   public static final FakeEnum<String> FAKE_ENUM = new FakeEnum<String>(NAryBooleanExpressionType.class, String.class);

}
