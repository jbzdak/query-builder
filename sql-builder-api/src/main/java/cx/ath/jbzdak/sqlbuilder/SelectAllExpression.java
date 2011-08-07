package cx.ath.jbzdak.sqlbuilder;

/**
 * Created by: Jacek Bzdak
 */
public class SelectAllExpression extends ColumnExpression{

   public static final SelectAllExpression STAR = new SelectAllExpression();

   public SelectAllExpression() {
      super("*");
   }

   public SelectAllExpression(String table) {
      super(table, "*", (Alias) null);
   }

   public SelectAllExpression(String schema, String table) {
      super(schema, table, "*");
   }

}
