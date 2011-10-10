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

package cx.ath.jbzdak.sqlbuilder.dialect.config;

import cx.ath.jbzdak.sqlbuilder.*;
import cx.ath.jbzdak.sqlbuilder.generic.config.Configuration;
import cx.ath.jbzdak.sqlbuilder.generic.config.ConfigurationKey;
import org.apache.commons.collections.CollectionUtils;

import java.nio.charset.MalformedInputException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by: Jacek Bzdak
 */
public class DialectConfig {

   Dialect d;

   final Map<DialectConfigKey, Object> config
           = new ConcurrentHashMap<DialectConfigKey, Object>();

   final Configuration configuration = new Configuration();

   public DialectConfig() {
   }

   public DialectConfig(DialectConfig dialectConfig) {
      d = dialectConfig.d;
      config.putAll(dialectConfig.config);
   }

   public DialectConfig(Dialect d) {
      this.d = d;
   }

   public void setDialect(Dialect d) {
      this.d = d;
   }

   public <T> T put(ConfigurationKey<T> key, T value) {
      return configuration.put(key, value);
   }

   public <T> T get(ConfigurationKey<T> key) {
      return configuration.get(key);
   }

   public Object getConfig(DialectConfigKey dialectConfigKey){
      Object result = config.get(dialectConfigKey);
      if(result != null){
         return result;
      }
      return dialectConfigKey.getDefault(d);
   }

   public void setConfig(DialectConfigKey dialectConfigKey, Object value){
      config.put(dialectConfigKey, value);
   }

   public void addNewPeerMapping(Class<? extends SQLFactory> clazz, Class<? extends SQLPeer> peerClazz){
      if(!config.containsKey(DialectConfigKey.ADDITIONAL_PEERS)){
          config.put(DialectConfigKey.ADDITIONAL_PEERS, DialectConfigKey.ADDITIONAL_PEERS.getDefault(d));
      }
      Map<Class<? extends IntermediateSQLFactory>, Class<? extends SQLPeer>> additional =  (Map<Class<? extends IntermediateSQLFactory>, Class<? extends SQLPeer>>) config.get(DialectConfigKey.ADDITIONAL_PEERS);
      additional.put(clazz, peerClazz);
   }

   public Date parseDate(String date) throws QueryRenderingException{
      SimpleDateFormat format;
      List<String> config1 = (List<String>) getConfig(DialectConfigKey.DEFAULT_INPUT_DATE_FORMATS);
      for (String stringFormat : config1) {
         format = new SimpleDateFormat(stringFormat);
         try {
            return format.parse(date);
         } catch (ParseException e) {
            continue;
         }
      }
      throw new QueryRenderingException("Couldn't parse date '" + date + "' using paterns " + Arrays.toString(config1.toArray()));
   }


}
