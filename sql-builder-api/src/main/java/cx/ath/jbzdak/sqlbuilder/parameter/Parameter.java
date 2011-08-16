package cx.ath.jbzdak.sqlbuilder.parameter;

import com.sun.org.apache.xerces.internal.impl.dv.xs.YearDV;
import cx.ath.jbzdak.sqlbuilder.ExpressionContext;
import cx.ath.jbzdak.sqlbuilder.IntermediateSQLFactory;
import cx.ath.jbzdak.sqlbuilder.SQLObject;

/**
 * Created by: Jacek Bzdak
 */
public abstract class Parameter<T>{

   String name;

   final String type;

   T defaultValue;

   public abstract T fromString(String string);

   public abstract T fromObject(Object o);

   protected Parameter(String type, String name) {
      this.name = name;
      this.type = type;
   }

   public String getName() {
      return name;
   }

   public T getDefaultValue() {
      return defaultValue;
   }

   public void setDefaultValue(T defaultValue) {
      this.defaultValue = defaultValue;
   }

   public void setDefaultValueAsString(String defaultValue) {
      this.defaultValue = fromString(defaultValue);
   }

   public String getType() {
      return type;
   }
}
