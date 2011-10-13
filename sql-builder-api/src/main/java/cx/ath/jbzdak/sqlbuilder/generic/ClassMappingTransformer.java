package cx.ath.jbzdak.sqlbuilder.generic;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Created by: Jacek Bzdak
 */
public class ClassMappingTransformer<T, S> implements Transformer<T, S>{

   private final Map<S, Factory<? extends T>> mapping = new HashMap<S, Factory<? extends T>>();

   private Transformer<T, S> defaultMapping;

   boolean defaultMappingNull;

   public void addMapping(S key, Factory<T> value) {
      mapping.put(key, value);
   }

   public void setDefaultMappingToNull(){
      defaultMappingNull = true;
      defaultMapping = null;
   }

   public void addMapping(S key, Class<? extends T> value) {
      mapping.put(key, new NewInstanceFactory<T>(value));
   }

   public void setDefaultMapping(Transformer<T, S> defaultMapping) {
      defaultMappingNull = false;
      this.defaultMapping = defaultMapping;
   }

   public void setDefaultMapping(Class<? extends T> defaultMapping) {
      defaultMappingNull = false;
      this.defaultMapping = new TransformerFactory<T, S>(new NewInstanceFactory<T>(defaultMapping));
   }

   @Override
   public T transform(S source) {
      Factory<? extends T> factory = mapping.get(source);

      if(factory == null){
         if(defaultMapping !=null){
            return defaultMapping.transform(source);
         }
         if(defaultMappingNull){
            return null;
         }
         throw new NoSuchElementException("Cant find mapping for '" + source + "'");
      }
      return factory.create();
   }
}


