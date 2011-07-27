package cx.ath.jbzdak.sqlbuilder;

/**
 * Created by: Jacek Bzdak
 */
public class Alias extends SQLObject implements SQLFactory, Identifier{

   String alias;

   public String getAlias() {
      return alias;
   }

   public Alias(String alias) {
      this.alias = alias;
   }

   public ColumnExpression column(String column){
      ColumnExpression columnExpression = new ColumnExpression(alias, column);
      columnExpression.registerParent(this);
      return columnExpression;
   }
}
