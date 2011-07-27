package cx.ath.jbzdak.sqlbuilder;

/**
 * Created by: Jacek Bzdak
 */
public abstract class AbstractJoin extends SQLObject{

   String joinType;

   Table table;

   public AbstractJoin(String joinType, Table table) {
      this.joinType = joinType;
      this.table = table;
   }

   public String getJoinType() {
      return joinType;
   }

   public Table getTable() {
      return table;
   }
}
