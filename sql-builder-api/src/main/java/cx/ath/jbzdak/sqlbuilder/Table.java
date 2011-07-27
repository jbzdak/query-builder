package cx.ath.jbzdak.sqlbuilder;

/**
 * Created by: Jacek Bzdak
 */
public class Table extends SQLObject{

   private String schema;
   private String table;

   private Alias alias;

   public Table(String table) {
      this.table = table;
   }

   public Table(String table, Alias alias) {
      this.table = table;
      this.alias = alias;
      alias.registerParent(this);
   }

   public Table(String schema, String table) {
      this.schema = schema;
      this.table = table;
   }

   public Table(String schema, String table, Alias alias) {
      this.schema = schema;
      this.table = table;
      this.alias = alias;
      alias.registerParent(this);
   }

   public String getSchema() {
      return schema;
   }

   public String getTable() {
      return table;
   }

   public Alias getAlias() {
      return alias;
   }
}
