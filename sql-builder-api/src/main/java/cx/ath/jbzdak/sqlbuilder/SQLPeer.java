package cx.ath.jbzdak.sqlbuilder;

/**
 * Created by: Jacek Bzdak
 */
public interface SQLPeer<T> {

   StringBuilder toSQL();

   void appendTo(StringBuilder stringBuilder);

   void registerParent(T t);

   void registerDialect(Dialect d);

}
