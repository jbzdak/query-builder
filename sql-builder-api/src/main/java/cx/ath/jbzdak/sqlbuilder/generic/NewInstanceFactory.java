package cx.ath.jbzdak.sqlbuilder.generic;

import java.util.concurrent.RejectedExecutionException;

/**
 * Created by: Jacek Bzdak
 */
public class NewInstanceFactory<T> implements Factory<T>{

   private final Class<? extends T> clazz;

   public NewInstanceFactory(Class<? extends T> clazz) {
      this.clazz = clazz;
   }

   public T create() {
      try {
         return clazz.newInstance();
      } catch (InstantiationException e) {
         throw new RejectedExecutionException(e);
      } catch (IllegalAccessException e) {
         throw new RuntimeException(e);
      }
   }
}
