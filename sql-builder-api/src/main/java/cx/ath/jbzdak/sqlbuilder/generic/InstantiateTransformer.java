package cx.ath.jbzdak.sqlbuilder.generic;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by: Jacek Bzdak
 */
public class InstantiateTransformer<T, S>  implements Transformer<T, S> {

   public static <T, S> T newInstance(Class<?extends T> targetClass, S source){
      return new InstantiateTransformer<T,S>(targetClass, null).transform(source);
   }

   private final Class<? extends T> clazz;

    Class<? extends S> sourceClass;

   public InstantiateTransformer(Class<? extends T> clazz) {
      this(clazz, null);
   }

   private InstantiateTransformer(Class<? extends T> clazz, Class<? extends S> sourceClass) {
      this.clazz = clazz;
      this.sourceClass = sourceClass;
   }

   @Override
   public T transform(S source) {
      try {
         return clazz.getConstructor(sourceClass!=null?sourceClass:source.getClass()).newInstance(source);
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