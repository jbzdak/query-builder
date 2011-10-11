package cx.ath.jbzdak.sqlbuilder.generic;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Created by: Jacek Bzdak
 */
public class ClassMappingTransformer<T, S> implements Transformer<T, S>{

   private final Map<S, Factory<? extends T>> mapping = new HashMap<S, Factory<? extends T>>();

   private Factory<? extends T> defaultMapping;

   public void addMapping(S key, Factory<T> value) {
      mapping.put(key, value);
   }

   public void addMapping(S key, Class<? extends T> value) {
      mapping.put(key, new NewInstanceFactory<T>(value));
   }

   public void setDefaultMapping(Factory<? extends T> defaultMapping) {
      this.defaultMapping = defaultMapping;
   }


   public void setDefaultMapping(Class<? extends T> defaultMapping) {
      this.defaultMapping = new NewInstanceFactory<T>(defaultMapping);
   }

   @Override
   public T transform(S source) {
      Factory<? extends T> factory = mapping.get(source);
      if(factory == null){
         throw new NoSuchElementException("Cant find mapping for '" + source + "'");
      }
      return factory.create();
   }
}


