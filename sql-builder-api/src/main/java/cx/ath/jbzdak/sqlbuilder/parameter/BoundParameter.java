package cx.ath.jbzdak.sqlbuilder.parameter;

import cx.ath.jbzdak.sqlbuilder.*;
import cx.ath.jbzdak.sqlbuilder.literal.LiteralFactory;

import java.util.Collections;
import java.util.Set;

/**
 * Created by: Jacek Bzdak
 */
public class BoundParameter<T> extends IntermediateSQLObject{

   private Parameter<T> parent;

   private T value;

   public void setParent(Parameter<T> parent) {
      this.parent = parent;
   }

   public T getValue() {
      if(value == null){
         return parent.getDefaultValue();
      }
      return value;
   }

   public void setValue(T value) {
      this.value = value;
   }

   public void setValueFromObject(String value) {
      this.value = parent.fromObject(value);
   }

   public boolean isBound(){
      return getValue() !=null;
   }

   @Override
   public void appendToInternal(RenderingContext renderingContext, StringBuilder stringBuilder) {
      if(!isBound()){
         throw new IllegalStateException("Parameter " + parent.getName() + "is nod bound but expression tries to render it") ;
      }

      super.appendTo(renderingContext, stringBuilder);
   }

   public Set<String> collectParameterNames() {
      return Collections.emptySet();
   }

}
