package cx.ath.jbzdak.sqlbuilder.parameter;

import cx.ath.jbzdak.sqlbuilder.Dialect;
import cx.ath.jbzdak.sqlbuilder.DialectAware;
import cx.ath.jbzdak.sqlbuilder.generic.ConstructorTransformer;
import cx.ath.jbzdak.sqlbuilder.generic.Transformer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by: Jacek Bzdak
 */
public class AbstractParameterFactory implements ParameterFactory{


   final Dialect dialect;

   final Map<String, Transformer<Parameter, String>> boundParameters = new HashMap<String, Transformer<Parameter, String>>();

   public AbstractParameterFactory(Dialect dialect) {
      this.dialect = dialect;
   }

   protected void addBinding(String type, Class<? extends Parameter> clazz){
      boundParameters.put(type, new ConstructorTransformer<Parameter, String>(clazz));
   }

   public Parameter createParameter(String name, String type) {
      Parameter parameter = boundParameters.get(type).transform(name);
      if (parameter instanceof DialectAware) {
         DialectAware aware = (DialectAware) parameter;
         aware.setDialect(dialect);
      }
      return parameter;
   }
}
