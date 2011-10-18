package cx.ath.jbzdak.sqlbuilder;

import cx.ath.jbzdak.sqlbuilder.parameter.Parameter;

/**
 * It's a value that can be passed to query either as a parameter or as a raw value;
 *
 * Created by: Jacek Bzdak
 */
public class ParametrizedValue<T> {

   final String parameterType;

   final boolean required;

   public ParametrizedValue(String parameterType) {
      this.parameterType = parameterType;
      this.required = true;
   }

   public ParametrizedValue(String parameterType, boolean required) {
      this.parameterType = parameterType;
      this.required = required;
   }

   Boolean valueSet = Boolean.FALSE; //

   Parameter<? extends T> parameterValue;

   T value;


   public T getValue(ExpressionContext context){
      if(valueSet){
         return value;
      }
      if(parameterValue == null){
         return null;
      }
      return context.resolveParameter(parameterValue);
   }



   public void setValue(ExpressionContext context, T value){
      if(value!=null){
         valueSet = true;
         this.value = value;
      }

   }

   public void setParameter(ExpressionContext context, Parameter<T> parameter){
      valueSet = false;
      parameterValue = parameter;
      context.addParameter(parameter, false);
   }

   public void setFromString(ExpressionContext context, String string){
      if(context.isParameter(string)){
         setParameter(context, createParameter(context, string));
         return;
      }
      setValue(context, convert(context, string));

   }

   private Parameter createParameter(ExpressionContext context, String string) {
      Parameter parameter = context.getDialect().createParameter(string, parameterType);
      parameter.setRequired(required);
      return parameter;
   }

   public T convert(ExpressionContext context, String value){
      if(value==null){
         return null;
      }
      Parameter<T> parameter = createParameter(context, value);
      return parameter.fromString(value);
   }


}


