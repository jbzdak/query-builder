package cx.ath.jbzdak.sqlbuilder.parameter;

/**
 * Created by: Jacek Bzdak
 */
public interface ParameterFactory {

   Parameter createParameter(String name, String type);

}
