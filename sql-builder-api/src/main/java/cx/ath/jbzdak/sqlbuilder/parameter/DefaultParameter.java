package cx.ath.jbzdak.sqlbuilder.parameter;

/**
 * Created by: Jacek Bzdak
 */
public class DefaultParameter extends Parameter{

   public DefaultParameter(String name) {
      super(ParameterType.DEFAULTT_PARAMETER, name);
   }

   @Override
   public Object fromString(String string) {
      return null;  //To change body of implemented methods use File | Settings | File Templates.
   }

   @Override
   public Object fromObject(Object o) {
      return null;  //To change body of implemented methods use File | Settings | File Templates.
   }
}
