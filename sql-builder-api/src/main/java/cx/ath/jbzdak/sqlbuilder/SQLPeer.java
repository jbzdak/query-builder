package cx.ath.jbzdak.sqlbuilder;

/**
 * Created by: Jacek Bzdak
 */
public interface SQLPeer<T> extends SQLFactory{


   void registerParent(T t);

   void registerDialect(Dialect d);

}
