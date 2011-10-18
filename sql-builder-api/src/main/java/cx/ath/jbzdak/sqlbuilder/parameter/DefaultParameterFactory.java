package cx.ath.jbzdak.sqlbuilder.parameter;

import cx.ath.jbzdak.sqlbuilder.Dialect;

/**
 * Created by: Jacek Bzdak
 */
public class DefaultParameterFactory extends AbstractParameterFactory{

   public DefaultParameterFactory(Dialect dialect) {
      super(dialect);

      addBinding(ParameterType.INTEGER_PARAMETER, IntegerParameter.class);
      addBinding(ParameterType.DEFAULTT_PARAMETER, DefaultParameter.class);
      addBinding(ParameterType.TABLE_PARAMETER, TableParameter.class);
      addBinding(ParameterType.DATE, DateParameter.class);
   }
}
