package cx.ath.jbzdak.sqlbuilder.dialect;

import cx.ath.jbzdak.sqlbuilder.IntermediateSQLFactory;
import cx.ath.jbzdak.sqlbuilder.generic.Factory;
import cx.ath.jbzdak.sqlbuilder.generic.Transformer;
import cx.ath.jbzdak.sqlbuilder.literal.StringLiteral;
import cx.ath.jbzdak.sqlbuilder.parameter.BoundParameter;
import cx.ath.jbzdak.sqlbuilder.parameter.Parameter;

import javax.management.monitor.StringMonitor;
import java.util.Map;

/**
 * Created by: Jacek Bzdak
 */
public class DefaultParameterFactory  implements Transformer<BoundParameter, Parameter> {

   Map<String, Factory<? extends BoundParameter>> typeMap;

   public void registerType(String type, final Class<? extends BoundParameter> boundParameter){
      typeMap.put(type, new Factory<BoundParameter>() {
         public BoundParameter create() {
            try {
               return boundParameter.newInstance();
            } catch (InstantiationException e) {
               throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
               throw new RuntimeException(e);
            }
         }
      });
   }

   public BoundParameter transform(Parameter source) {
      BoundParameter bound = typeMap.get(source.getType()).create();
      bound.setParent(source);
      return bound;
   }
}



