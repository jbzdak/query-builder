package cx.ath.jbzdak.sqlbuilder.dialect;

import cx.ath.jbzdak.sqlbuilder.*;
import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfig;
import cx.ath.jbzdak.sqlbuilder.generic.Transformer;

import java.security.InvalidParameterException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by: Jacek Bzdak
 */
public abstract class AbstractDialect implements Dialect{

   protected volatile Map<Class, Transformer<SQLPeer, IntermediateSQLFactory>> transformerMap;

   protected abstract Map<Class, Transformer<SQLPeer, IntermediateSQLFactory>> createTransformerMap();



   private final DialectConfig dialectConfig;

   protected AbstractDialect(DialectConfig dialectConfig) {
      this.dialectConfig = dialectConfig;
      dialectConfig.setDialect(this);
   }

   public SQLPeer getPeer(IntermediateSQLFactory sqlFactory) {
      if(transformerMap == null){
         synchronized (this){
            if(transformerMap == null){
               transformerMap = new ConcurrentHashMap<Class, Transformer<SQLPeer, IntermediateSQLFactory>>(createTransformerMap());
            }
         }
      }
      return sqlPeer(sqlFactory, sqlFactory.getClass());
   }

   private SQLPeer sqlPeer(IntermediateSQLFactory sqlFactory, Class<?> clazz){
      Transformer<SQLPeer, IntermediateSQLFactory> transformer = transformerMap.get(clazz);
      if(transformer != null){
         if(!sqlFactory.getClass().equals(clazz)){
            transformerMap.put(sqlFactory.getClass(), transformer);
         }
         SQLPeer peer = transformer.transform(sqlFactory);
         peer.registerParent(sqlFactory);
         return peer;
      }


      Class superclasss = clazz.getSuperclass();
      if(superclasss != null){
         return sqlPeer(sqlFactory, superclasss);
      }

      Class[] intefraces = clazz.getInterfaces();
      for (int i = 0, intefracesLength = intefraces.length; i < intefracesLength; i++) {
         Class intefrace = intefraces[i];
         return sqlPeer(sqlFactory, intefrace);
      }
      throw  new InvalidParameterException("Couldnt find peer for " + sqlFactory);
   }

   public DialectConfig getDialectConfig() {
      return dialectConfig;
   }

   public Select select(){
      return new Select(new ExpressionContext(this));
   }

}
