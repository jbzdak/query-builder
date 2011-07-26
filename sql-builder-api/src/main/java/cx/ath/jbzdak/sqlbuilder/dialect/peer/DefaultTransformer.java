package cx.ath.jbzdak.sqlbuilder.dialect.peer;

import cx.ath.jbzdak.sqlbuilder.SQLFactory;
import cx.ath.jbzdak.sqlbuilder.SQLPeer;
import cx.ath.jbzdak.sqlbuilder.generic.Transformer;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by: Jacek Bzdak
 */
public class DefaultTransformer implements Transformer<SQLPeer, SQLFactory>{

   Class<? extends SQLPeer> aClass;

   public DefaultTransformer(Class<? extends SQLPeer> aClass) {
      this.aClass = aClass;
   }

   public SQLPeer transform(SQLFactory source) {
      try {
         SQLPeer peer =  aClass.getConstructor().newInstance();
         peer.registerParent(source);
         return peer;
      } catch (RuntimeException e){
         throw e;
      } catch (Exception e){
         throw  new RuntimeException(e);
      }
   }
}
