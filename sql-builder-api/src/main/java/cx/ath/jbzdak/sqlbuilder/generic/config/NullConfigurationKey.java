package cx.ath.jbzdak.sqlbuilder.generic.config;

/**
 * Created by: Jacek Bzdak
 */
public class NullConfigurationKey<T> extends ConfigurationKey<T>{

   public static <T> ConfigurationKey<T> create(){
      return new NullConfigurationKey<T>();
   }

   @Override
   public T getDefault(Configuration context) {
      return null;
   }


}
