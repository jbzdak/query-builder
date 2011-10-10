package cx.ath.jbzdak.sqlbuilder;

import cx.ath.jbzdak.sqlbuilder.ExpressionContext;
import cx.ath.jbzdak.sqlbuilder.parameter.Parameter;

/**
 * It's a value that can be passed to query either as a parameter or as a raw value;
 *
 * Created by: Jacek Bzdak
 */
public class ParametrizedValue<T> {

   final String parameterType;

   public ParametrizedValue(String parameterType) {
      this.parameterType = parameterType;
   }

   Parameter<? extends T> parameterValue;

   T value;

   public T getValue(ExpressionContext context){
      if(value != null){
         return value;
      }
      return context.resolveParameter(parameterValue);
   }



   public void setValue(ExpressionContext context, T value){
      this.value = value;
   }

   public void setParameter(ExpressionContext context, Parameter<T> parameter){
      parameterValue = parameter;
      context.addParameter(parameter);
   }

   public void setFromString(ExpressionContext context, String string){
      if(context.isParameter(string)){
         setParameter(context, context.getDialect().createParameter(string, parameterType));
         return;
      }
      setValue(context, convert(context, string));

   }

   public T convert(ExpressionContext context, String value){
      Parameter<T> parameter = context.getDialect().createParameter("", value);
      return parameter.fromString(value);
   }
}
