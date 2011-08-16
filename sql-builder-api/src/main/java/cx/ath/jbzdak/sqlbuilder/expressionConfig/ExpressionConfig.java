package cx.ath.jbzdak.sqlbuilder.expressionConfig;

import java.util.EnumMap;
import java.util.Map;

/**
 * Created by: Jacek Bzdak
 */
public class ExpressionConfig {

   private final Map<ExpressionConfigKey, Object> values = new EnumMap<ExpressionConfigKey, Object>(ExpressionConfigKey.class);

   public ExpressionConfig() {
   }

   public ExpressionConfig(ExpressionConfig expressionConfig) {
      values.putAll(expressionConfig.values);
   }

   public Object get(ExpressionConfigKey key){
      if(values.containsKey(key)){
         return values.get(key);
      }
      return key.getDefault(this);
   }

   public Object put(ExpressionConfigKey key, Object value) {
      return values.put(key, value);
   }
}
