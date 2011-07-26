package cx.ath.jbzdak.sqlbuilder;

/**
 * Created by: Jacek Bzdak
 */
public class SQLObject implements SQLFactory {

   Dialect dialect = DialectHolder.getDefaultDialect();

   SQLPeer sqlPeer;

   public SQLObject() {
   }

   public SQLObject(SQLObject sqlObject){
      dialect = sqlObject.dialect;
   }

   public SQLObject(Dialect dialect) {
      this.dialect = dialect;
   }

   public void registerParent(SQLObject parent){
      dialect = parent.dialect;
      sqlPeer = null;
   }

   public StringBuilder toSQL() {
      if(sqlPeer == null){
         sqlPeer = dialect.getPeer(this);
      }
      return sqlPeer.toSQL();
   }
}
