package cx.ath.jbzdak.sqlbuilder.generic;

/**
 * Created by: Jacek Bzdak
 */
public class TransformerFactory<T, S> implements Transformer<T, S>{

   private final Factory<T> tFactory;

   public TransformerFactory(Factory<T> tFactory) {
      this.tFactory = tFactory;
   }

   @Override
   public T transform(S source) {
      return tFactory.create();
   }
}
