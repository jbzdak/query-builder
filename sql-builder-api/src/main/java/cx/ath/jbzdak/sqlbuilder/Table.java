package cx.ath.jbzdak.sqlbuilder;

/**
 * Created by: Jacek Bzdak
 */
public class Table extends SQLObject{

   private String schema;
   private String table;

   public Table(String table) {
      this.table = table;
   }

   public Table(String schema, String table) {
      this.schema = schema;
      this.table = table;
   }

   public String getSchema() {
      return schema;
   }

   public String getTable() {
      return table;
   }
}
