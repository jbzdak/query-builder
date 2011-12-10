package cx.ath.jbzdak.sqlbuilder.xml;

import cx.ath.jbzdak.sqlbuilder.QueryDescriptor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import java.util.*;

/**
 * Created by: Jacek Bzdak
 */
@XmlType
public class XmlQueryDescriptor{

   private List<QueryDescriptorKey> metaDataKey = new ArrayList<QueryDescriptorKey>();
   
   @XmlElement(name = "key", type = QueryDescriptorKey.class)
   public List<QueryDescriptorKey> getMetaDataKey() {
      return metaDataKey;
   }

   public void setMetaDataKey(List<QueryDescriptorKey> metaDataKey) {
      this.metaDataKey = metaDataKey;
   }


   public Map<String, String> getMetadata(String name, String description){
      Map<String, String> metadata = new HashMap<String, String>();
      metadata.put(QueryDescriptor.NAME_METADATA_KEY, name);
      metadata.put(QueryDescriptor.DESCRIPTION_METADATA_KEY, description);
      for (QueryDescriptorKey key : metaDataKey) {
         metadata.put(key.getKey(), key.getPattern());
      }
      return metadata;
   }
}
