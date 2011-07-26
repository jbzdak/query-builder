package cx.ath.jbzdak.sqlbuilder.dialect.peer;

import cx.ath.jbzdak.sqlbuilder.Dialect;
import cx.ath.jbzdak.sqlbuilder.SQLPeer;

/**
 * Created by: Jacek Bzdak
 */
public abstract class AbstractPeer<T> implements SQLPeer<T>{

   protected T parent;

   protected Dialect dialect;

   public void registerParent(T t) {
      parent = t;
   }

   public void registerDialect(Dialect d) {
      dialect = d;
   }

   public StringBuilder toSQL() {
      StringBuilder stringBuilder = new StringBuilder();
      appendTo(stringBuilder);
      return stringBuilder;
   }
}
