package cx.ath.jbzdak.sqlbuilder.dialect.config;

import cx.ath.jbzdak.sqlbuilder.Dialect;

import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by: Jacek Bzdak
 */
public class DialectConfig {

   final Dialect d;

   final Map<DialectConfigKey, Object> config
           = new ConcurrentHashMap<DialectConfigKey, Object>();

   public DialectConfig(Dialect d) {
      this.d = d;
   }

   public Object getConfig(DialectConfigKey dialectConfigKey){
      Object result = config.get(dialectConfigKey);
      if(result != null){
         return result;
      }
      return dialectConfigKey.getDefault(d);
   }

   public void setConfig(DialectConfigKey dialectConfigKey, Object value){
      config.put(dialectConfigKey, value);
   }


}
