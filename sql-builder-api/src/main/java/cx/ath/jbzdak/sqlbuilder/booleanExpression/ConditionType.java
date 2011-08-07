package cx.ath.jbzdak.sqlbuilder.booleanExpression;

import fakeEnum.FakeEnum;

import java.util.Collection;

/**
 * Created by: Jacek Bzdak
 */
public class ConditionType {

   public static final String LIKE = "LIKE";

   public static final String EQUALS = "=";

   public static final String NE = "<>";

   public static final String LT = "<";

   public static final String GT = ">";

   public static final String LTE = "<=";

   public static final String GTE = ">=";

   public static final String IN = "IN";

   private static final FakeEnum<String> FAKE_ENUM = new FakeEnum<String>(ConditionType.class, String.class);

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
