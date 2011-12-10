package cx.ath.jbzdak.sqlbuilder.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/**
 * Created by: Jacek Bzdak
 */
@XmlType
public class QueryDescriptorKey {


   private String key;


   private String pattern;

   @XmlAttribute(name = "name")
   public String getKey() {
      return key;
   }

   public void setKey(String key) {
      this.key = key;
   }

   @XmlValue
   public String getPattern() {
      return pattern;
   }

   public void setPattern(String pattern) {
      this.pattern = pattern;
   }
}
