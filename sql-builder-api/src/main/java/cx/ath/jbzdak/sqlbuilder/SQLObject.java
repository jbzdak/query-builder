package cx.ath.jbzdak.sqlbuilder;

import com.sun.org.apache.xpath.internal.operations.Or;
import cx.ath.jbzdak.sqlbuilder.dialect.DefaultDialect;

import java.lang.reflect.Field;

/**
 * Created by: Jacek Bzdak
 */
public class SQLObject implements SQLFactory, DialectAware {

   protected Dialect dialect = DialectHolder.getDefaultDialect();

   protected boolean dialectUpdatedInChildren;

   protected SQLPeer sqlPeer;

   public SQLObject() {
   }

   public SQLObject(SQLObject parent){
      dialect = parent.dialect;
   }

   public SQLObject(Dialect dialect) {
      this.dialect = dialect;
   }

   public void registerParent(SQLObject parent){
      dialect = parent.dialect;
      sqlPeer = null;
      registerParentforChildren();
   }

   protected void registerParentforChildren(){
      for (Field field : getClass().getDeclaredFields()) {
         if(DialectAware.class.isAssignableFrom(field.getType()) || SQLFactory.class.isAssignableFrom(field.getType())){
            boolean accesible = field.isAccessible();
            try {
               field.setAccessible(true);

               Object o = field.get(this);
               if (o instanceof DialectAware) {
                  DialectAware dialectAware = (DialectAware) o;
//                  if(dialectAware != null){
                  dialectAware.registerParent(this);
//                  }
               }
            } catch (IllegalAccessException e) {
               throw new RuntimeException(e); //Wont happen ;)
            }finally {
               field.setAccessible(accesible);
            }
         }
      }

   }

   public StringBuilder toSQL() {
      if(sqlPeer == null){
         if(!dialectUpdatedInChildren){
            registerParentforChildren();
            dialectUpdatedInChildren = true;
         }
         sqlPeer = dialect.getPeer(this);
      }
      return sqlPeer.toSQL();
   }

   public void appendTo(StringBuilder stringBuilder) {
      if(sqlPeer == null){
         sqlPeer = dialect.getPeer(this);
      }
      sqlPeer.appendTo(stringBuilder);
   }
}
