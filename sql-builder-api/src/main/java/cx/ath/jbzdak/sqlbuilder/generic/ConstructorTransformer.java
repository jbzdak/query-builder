package cx.ath.jbzdak.sqlbuilder.generic;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by: Jacek Bzdak
 */
public class ConstructorTransformer<T, S> implements Transformer<T, S>{

   final Class<? extends T> clazz;

   public ConstructorTransformer(Class<? extends T> clazz) {
      this.clazz = clazz;
   }

   public T transform(S source) {

      try {
         return clazz.getConstructor(source.getClass()).newInstance(source);
      } catch (InstantiationException e) {
         throw new RuntimeException(e);
      } catch (IllegalAccessException e) {
         throw new RuntimeException(e);
      } catch (InvocationTargetException e) {
         throw new RuntimeException(e);
      } catch (NoSuchMethodException e) {
         throw new RuntimeException(e);
      }

   }
}
