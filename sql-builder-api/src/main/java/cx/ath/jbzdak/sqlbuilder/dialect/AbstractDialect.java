package cx.ath.jbzdak.sqlbuilder.dialect;

import com.sun.corba.se.spi.ior.IdentifiableFactory;
import com.sun.xml.internal.bind.v2.model.core.ID;
import cx.ath.jbzdak.sqlbuilder.Dialect;
import cx.ath.jbzdak.sqlbuilder.SQLFactory;
import cx.ath.jbzdak.sqlbuilder.SQLPeer;
import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfig;
import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfigKey;
import cx.ath.jbzdak.sqlbuilder.generic.Factory;
import cx.ath.jbzdak.sqlbuilder.generic.Transformer;

import java.security.InvalidParameterException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by: Jacek Bzdak
 */
public abstract class AbstractDialect implements Dialect{

   protected volatile Map<Class, Transformer<SQLPeer, SQLFactory>> transformerMap;

   protected abstract Map<Class, Transformer<SQLPeer, SQLFactory>> createTransformerMap();

   private final DialectConfig dialectConfig;

   protected AbstractDialect(DialectConfig dialectConfig) {
      this.dialectConfig = dialectConfig;
   }

   public SQLPeer getPeer(SQLFactory sqlFactory) {
      if(transformerMap == null){
         synchronized (this){
            if(transformerMap == null){
               transformerMap = new ConcurrentHashMap<Class, Transformer<SQLPeer, SQLFactory>>(createTransformerMap());
            }
         }
      }
      return sqlPeer(sqlFactory, sqlFactory.getClass());
   }

   private SQLPeer sqlPeer(SQLFactory sqlFactory, Class<?> clazz){
      Transformer<SQLPeer, SQLFactory> transformer = transformerMap.get(clazz);
      if(transformer != null){
         if(!sqlFactory.getClass().equals(clazz)){
            transformerMap.put(sqlFactory.getClass(), transformer);
         }
         SQLPeer peer = transformer.transform(sqlFactory);
         peer.registerDialect(this);
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
}
