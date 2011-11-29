package cx.ath.jbzdak.sqlbuilder.xml;

import cx.ath.jbzdak.sqlbuilder.xml.query.AbstractQuery;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by: Jacek Bzdak
 */
@XmlRootElement
public class SingleQuery {

   AbstractQuery abstractQuery;

   public AbstractQuery getAbstractQuery() {
      return abstractQuery;
   }

   public void setAbstractQuery(AbstractQuery abstractQuery) {
      this.abstractQuery = abstractQuery;
   }
}
