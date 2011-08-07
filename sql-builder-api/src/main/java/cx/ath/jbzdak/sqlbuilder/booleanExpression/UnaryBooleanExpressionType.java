package cx.ath.jbzdak.sqlbuilder.booleanExpression;

import fakeEnum.FakeEnum;

import java.util.Collection;

/**
 * Created by: Jacek Bzdak
 */
public class UnaryBooleanExpressionType {

   public static final String IS_NULL = "IS NULL";
   public static final String IS_NON_NULL = "IS NOT NULL";

//   public static final String NOT = "NOT";

   private static final FakeEnum<String> FAKE_ENUM = new FakeEnum<String>(UnaryBooleanExpressionType.class, String.class);

   public static String nameOf(String value) {
      return FAKE_ENUM.nameOf(value);
   }

   public static Collection<? extends String> values() {
      return FAKE_ENUM.values();
   }

   public static String valueOf(String s) {
      return FAKE_ENUM.valueOf(s);
   }
}
