package cx.ath.jbzdak.sqlbuilder;

/**
 * Created by: Jacek Bzdak
 */
public class AbstractParameterValueDescriptor implements ParameterValueDescriptor{

   final String value;

   final String label;

   public AbstractParameterValueDescriptor(String valued, String label) {
      this.value = valued;
      this.label = label;
   }

   public String getValue() {
      return value;
   }

   public String getLabel() {
      return label;
   }
}
