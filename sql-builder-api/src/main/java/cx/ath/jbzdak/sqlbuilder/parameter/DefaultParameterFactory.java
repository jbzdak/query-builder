package cx.ath.jbzdak.sqlbuilder.parameter;

/**
 * Created by: Jacek Bzdak
 */
public class DefaultParameterFactory extends AbstractParameterFactory{

   public DefaultParameterFactory() {
      addBinding(ParameterType.INTEGER_PARAMETER, IntegerParameter.class);
      addBinding(ParameterType.DEFAULTT_PARAMETER, DefaultParameter.class);
      addBinding(ParameterType.TABLE_PARAMETER, TableParameter.class);
   }
}
