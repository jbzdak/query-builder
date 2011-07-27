package cx.ath.jbzdak.sqlbuilder;

/**
 * Created by: Jacek Bzdak
 */
public class ColumnExpression extends SQLObject implements SQLFactory{

   private String schema;

   private String table;

   private String column;

   private Alias alias;



   public ColumnExpression(String column) {
      this.column = column;
   }


   public Alias getAlias() {
      return alias;
   }

   public ColumnExpression(String table, String column) {
      this.table = table;
      this.column = column;
   }

   public ColumnExpression(String table, String column, Alias alias) {
      this.table = table;
      this.column = column;
      this.alias = alias;
      alias.registerParent(this);
   }

   public ColumnExpression(String schema, String table, String column) {
      this.schema = schema;
      this.table = table;
      this.column = column;
   }

   public ColumnExpression(Alias alias, String column, String table, String schema) {
      this.alias = alias;
      this.column = column;
      this.table = table;
      this.schema = schema;
   }

   public String getSchema() {
      return schema;
   }

   public String getTable() {
      return table;
   }

   public String getColumn() {
      return column;
   }

}


