package cx.ath.jbzdak.sqlbuilder.xml.parameter;

import cx.ath.jbzdak.sqlbuilder.parameter.Parameter;
import cx.ath.jbzdak.sqlbuilder.parameter.ParameterType;
import cx.ath.jbzdak.sqlbuilder.xml.AbstractXmlQueryCollection;
import cx.ath.jbzdak.sqlbuilder.xml.XmlParsingContext;
import cx.ath.jbzdak.sqlbuilder.xml.XmlQueryCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.XmlAttribute;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by: Jacek Bzdak
 */
public class TypedParameter extends XmlParameter{

   private static final Logger LOGGER = LoggerFactory.getLogger(TypedParameter.class);

   String type = ParameterType.DEFAULTT_PARAMETER;

   String description;

   @XmlAttribute
   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   @XmlAttribute(required = false)
   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }
   
   

   @Override
   public Parameter<?> createParameter() {
      AbstractXmlQueryCollection collection = XmlParsingContext.getCollection();
      Parameter p = collection.getDialect().createParameter(name, type);
      if (getDefaultValue() != null && getDefaultValue().trim().length() !=0){
         p.setDefaultValue(p.fromString(getDefaultValue()));
      }
      try{
         Method setter = p.getClass().getMethod("setDescription", String.class);
         setter.invoke(p, description); 
      }catch (NoSuchMethodException e){
         //COnitinue
      } catch (InvocationTargetException e) {
         throw new RuntimeException(e.getCause());
      } catch (IllegalAccessException e) {
         LOGGER.error("Exception while setting parameter descrption", e);
      }


      return p;
   }
}
