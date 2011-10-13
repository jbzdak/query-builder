package cx.ath.jbzdak.sqlbuilder.xml;

import cx.ath.jbzdak.sqlbuilder.Dialect;

/**
 * Created by: Jacek Bzdak
 */
public class XmlParsingContext {

   private static final ThreadLocal<XmlQueryCollection> COLLECTION = new ThreadLocal<XmlQueryCollection>();

   public static Dialect getDialect(){
      return getCollection().getDialect();
   }

   public static XmlQueryCollection getCollection() {
      return COLLECTION.get();
   }

   public static void setXmlQueryCollection(XmlQueryCollection collection){
      COLLECTION.set(collection);
   }

   public static void remove() {
      COLLECTION.remove();
   }
}
