package cx.ath.jbzdak.sqlbuilder.literal;

import cx.ath.jbzdak.sqlbuilder.SQLLiteral;
import cx.ath.jbzdak.sqlbuilder.SQLLiteralType;
import cx.ath.jbzdak.sqlbuilder.parameter.Parameter;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by: Jacek Bzdak
 */
public class ParameterLiteral extends SQLLiteral<Parameter>{
   public ParameterLiteral() {
   }

   public ParameterLiteral(Parameter literalValue) {
      super(SQLLiteralType.PARAMETER, literalValue);
   }

   @Override
   public Set<String> collectParameterNames() {
      return new HashSet<String>(Arrays.asList(this.getLiteralValue().getName()));
   }
}
