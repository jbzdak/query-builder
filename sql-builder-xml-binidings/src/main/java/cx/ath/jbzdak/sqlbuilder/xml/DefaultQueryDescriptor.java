package cx.ath.jbzdak.sqlbuilder.xml;

import cx.ath.jbzdak.sqlbuilder.AbstractQueryDescriptor;
import cx.ath.jbzdak.sqlbuilder.ParameterDescriptor;
import cx.ath.jbzdak.sqlbuilder.SQLFactory;
import cx.ath.jbzdak.sqlbuilder.parameter.Parameter;

import java.util.Collection;
import java.util.Map;

/**
 * Created by: Jacek Bzdak
 */
public class DefaultQueryDescriptor extends AbstractQueryDescriptor{
   
   final SQLFactory factory;

   public DefaultQueryDescriptor(Map<String, String> metadata, SQLFactory factory) {
      super(metadata);
      this.factory = factory;
   }

   @Override
   public String getParametrValue(String name) {
      Parameter parameter = factory.getContext().getParameterMap().get(name);
      return parameter.humanReadableForm(factory.getContext().resolveParameter(name));
   }

   @Override
   public Collection<? extends ParameterDescriptor> getParameters() {
      return factory.getContext().getParameters();
   }
}
