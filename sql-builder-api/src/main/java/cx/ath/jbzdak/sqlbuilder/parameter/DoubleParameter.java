package cx.ath.jbzdak.sqlbuilder.parameter;

/**
 * Created by: Jacek Bzdak
 */
public class DoubleParameter extends AbstractParameter {

   public DoubleParameter(String name) {
      super(ParameterType.DOUBLE_PARAMETER, name);
   }

   @Override
   public Object fromObject(Object o) {
      if (o instanceof String){
         return fromString((String)o);
      }
      if (o instanceof Number){
         return ((Number)o).doubleValue();
      }
      throw new ClassCastException("Cant convert " + o + " to double for double paramneter named '" + name + "'");

   }

   @Override
   public Object fromString(String string) {
      return Double.parseDouble(string);
   }
}



