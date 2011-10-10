package cx.ath.jbzdak.sqlbuilder.generic.config;

/**
 * Created by: Jacek Bzdak
 */
public class ConfigB {

   public static final ConfigurationKey<Void> KEY_1  = new NullConfigurationKey<Void>();

   public static final ConfigurationKey<Void> KEY_2  = new NullConfigurationKey<Void>();

   static{
      ConfigurationKey.registerConfig(ConfigB.class);
   }
}
