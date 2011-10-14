package cx.ath.jbzdak.sqlbuilder.xml.parameter;

import cx.ath.jbzdak.sqlbuilder.parameter.Parameter;
import cx.ath.jbzdak.sqlbuilder.parameter.ParameterType;
import cx.ath.jbzdak.sqlbuilder.xml.AbstractXmlQueryCollection;
import cx.ath.jbzdak.sqlbuilder.xml.XmlParsingContext;
import cx.ath.jbzdak.sqlbuilder.xml.XmlQueryCollection;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by: Jacek Bzdak
 */
public class TypedParameter extends XmlParameter{


   String type = ParameterType.DEFAULTT_PARAMETER;

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
      return collection.getDialect().createParameter(name, type);
   }
}
