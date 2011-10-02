/*
 * Copyright (c) 2011 for Jacek Bzdak
 *
 * This file is part of query builder.
 *
 * Query builder is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Query builder is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Query builder.  If not, see <http://www.gnu.org/licenses/>.
 */

package cx.ath.jbzdak.sqlbuilder.dialect;

import cx.ath.jbzdak.sqlbuilder.*;
import cx.ath.jbzdak.sqlbuilder.booleanExpression.BooleanFactory;
import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfig;
import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfigKey;
import cx.ath.jbzdak.sqlbuilder.dialect.config.InvalidConfigurationException;
import cx.ath.jbzdak.sqlbuilder.dialect.config.PrettifySQLLevel;
import cx.ath.jbzdak.sqlbuilder.dialect.peer.DefaultTransformer;
import cx.ath.jbzdak.sqlbuilder.expressionConfig.ExpressionConfig;
import cx.ath.jbzdak.sqlbuilder.generic.Factory;
import cx.ath.jbzdak.sqlbuilder.generic.Transformer;
import cx.ath.jbzdak.sqlbuilder.literal.LiteralFactory;
import cx.ath.jbzdak.sqlbuilder.parameter.AbstractParameter;
import cx.ath.jbzdak.sqlbuilder.parameter.BoundParameter;
import cx.ath.jbzdak.sqlbuilder.parameter.Parameter;

import java.security.InvalidParameterException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by: Jacek Bzdak
 */
public abstract class AbstractDialect implements Dialect{

   private volatile Map<Class, Transformer<SQLPeer, IntermediateSQLFactory>> transformerMap;

   protected Transformer<BoundParameter, AbstractParameter<?>> parameterFactory;

   protected QuotingManager quotingManager;

   protected abstract Map<Class, Transformer<SQLPeer, IntermediateSQLFactory>> createTransformerMap();

   private final DialectConfig dialectConfig;

   protected abstract QuotingManager createDefaultQuotingManager();

   private  boolean dialectConfigLocked = false;

   protected AbstractDialect(DialectConfig dialectConfig) {
      this.dialectConfig = dialectConfig;
   }

   /**
    * Hook for subclasses to update from dialectConfig. Will be called only once --- after
    * {@link #lockDialectConfig(cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfig)}  is called
    */
   protected void updateFromDialectConfig(){}

   private void updateFromDialectConfigInternal(){
      dialectConfig.setDialect(this);
      if(dialectConfig.getConfig(DialectConfigKey.PARAMETER_FACTORY) == null){
         parameterFactory = createDefaultParameterFactory();
      }else{
         parameterFactory =  (Transformer<BoundParameter, AbstractParameter<?>>) dialectConfig.getConfig(DialectConfigKey.PARAMETER_FACTORY);
      }
      quotingManager = createDefaultQuotingManager();
   }

   public <T> BoundParameter bindParameter(AbstractParameter<T> source, T value) {
      BoundParameter boundParameter = parameterFactory.transform(source);
      boundParameter.setValue(value);
      return boundParameter;
   }

   /**
    * Creates parameter factory if it was not passed via DialectConfig.
    *
    * Will be called iff {@link DialectConfigKey.PARAMETER_FACTORY} was not set in dialectConfig.
    * @return
    */
   protected abstract Transformer<BoundParameter, AbstractParameter<?>> createDefaultParameterFactory();

   public synchronized SQLPeer getPeer(IntermediateSQLFactory sqlFactory) {
      if(transformerMap == null){
         transformerMap = new ConcurrentHashMap<Class, Transformer<SQLPeer, IntermediateSQLFactory>>(createTransformerMap());
         Map<Class<? extends IntermediateSQLFactory>, Class<? extends SQLPeer>> additional =
                 (Map<Class<? extends IntermediateSQLFactory>, Class<? extends SQLPeer>>) dialectConfig.getConfig(DialectConfigKey.ADDITIONAL_PEERS);
         for (Map.Entry<Class<? extends IntermediateSQLFactory>, Class<? extends SQLPeer>> e : additional.entrySet()) {
            putPeer(transformerMap, e.getKey(), e.getValue());
         }
      }
      return sqlPeer(sqlFactory, sqlFactory.getClass());
   }

   protected static void putPeer(Map<Class, Transformer<SQLPeer, IntermediateSQLFactory>> map,
                                 Class<? extends IntermediateSQLFactory> objectClass, Class<? extends SQLPeer> peerClass){
      map.put(objectClass, new DefaultTransformer(peerClass));
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
      int length = intefraces.length;
      for (int i = 0; i <length; i++) {
         Class intefrace = intefraces[i];
         try {
            return sqlPeer(sqlFactory, intefrace);
         } catch (InvalidParameterException e) {
            //continue
         }
      }
      throw  new InvalidParameterException("Couldnt find peer for " + sqlFactory);
   }

   public DialectConfig getDialectConfig() {
      return dialectConfig;
   }

   public Select select(){
      return new Select(new ExpressionContext(this));
   }

   public ExpressionConfig getDefaultExpressionConfig() {
      return new ExpressionConfig();
   }

   public String quoteIdentifier(CharSequence ident, IdentifierQuotingStrategy strategy) {
      return quotingManager.quoteIdentifier(ident, strategy);
   }

   public String quoteIdentifier(CharSequence ident) {
      return quotingManager.quoteIdentifier(ident);
   }

   public String quoteString(CharSequence quote) {
      return quotingManager.quoteString(quote);
   }
}
