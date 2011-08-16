package cx.ath.jbzdak.sqlbuilder;

import java.util.Set;

/**
 * Created by: Jacek Bzdak
 */
public class Table extends IntermediateSQLObject{

   private String schema;
   private String table;
   private Alias alias;

   public Table(String table) {
      this.table = table;
   }

   public Table(String table, Alias alias) {
      this.table = table;
      this.alias = alias;
   }

   public Table(String schema, String table) {
      this.schema = schema;
      this.table = table;
   }

   public Table(String schema, String table, Alias alias) {
      this.schema = schema;
      this.table = table;
      this.alias = alias;
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

   public Set<String> collectParameterNames() {
      return null;  //To change body of implemented methods use File | Settings | File Templates.
   }
}
