package cx.ath.jbzdak.sqlbuilder.generic;

/**
 * Created by: Jacek Bzdak
 */
public interface Transformer<T, S> {

   T transform(S source);
}
