package cx.ath.jbzdak.sqlbuilder.parameter;

import cx.ath.jbzdak.sqlbuilder.generic.ConstructorTransformer;
import cx.ath.jbzdak.sqlbuilder.generic.Transformer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by: Jacek Bzdak
 */
public class AbstractParameterFactory implements ParameterFactory{


   final Map<String, Transformer<Parameter, String>> boundParameters = new HashMap<String, Transformer<Parameter, String>>();

   protected void addBinding(String type, Class<? extends Parameter> clazz){
      boundParameters.put(type, new ConstructorTransformer<Parameter, String>(clazz));
   }

   public Parameter createParameter(String name, String type) {
      return boundParameters.get(type).transform(name);
   }
}
