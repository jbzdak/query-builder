package cx.ath.jbzdak.sqlbuilder.xml;

import cx.ath.jbzdak.sqlbuilder.Dialect;

/**
 * Created by: Jacek Bzdak
 */
public class XmlParsingContext {

   private static final ThreadLocal<AbstractXmlQueryCollection> COLLECTION = new ThreadLocal<AbstractXmlQueryCollection>();

   public static Dialect getDialect(){
      return getCollection().getDialect();
   }

   public static AbstractXmlQueryCollection getCollection() {
      return COLLECTION.get();
   }

   public static void setXmlQueryCollection(AbstractXmlQueryCollection collection){
      COLLECTION.set(collection);
   }

   public static void remove() {
      COLLECTION.remove();
   }
}
