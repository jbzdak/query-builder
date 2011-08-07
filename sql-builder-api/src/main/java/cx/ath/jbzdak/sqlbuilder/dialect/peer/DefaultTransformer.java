package cx.ath.jbzdak.sqlbuilder.dialect.peer;

import cx.ath.jbzdak.sqlbuilder.IntermediateSQLFactory;
import cx.ath.jbzdak.sqlbuilder.SQLPeer;
import cx.ath.jbzdak.sqlbuilder.generic.Transformer;

/**
 * Created by: Jacek Bzdak
 */
public class DefaultTransformer implements Transformer<SQLPeer, IntermediateSQLFactory>{

   Class<? extends SQLPeer> aClass;

   public DefaultTransformer(Class<? extends SQLPeer> aClass) {
      this.aClass = aClass;
   }

   public SQLPeer transform(IntermediateSQLFactory source) {
      try {
         SQLPeer peer =  aClass.getConstructor().newInstance();
         peer.registerContext;
         return peer;
      } catch (RuntimeException e){
         throw e;
      } catch (Exception e){
         throw  new RuntimeException(e);
      }
   }
}
