package cx.ath.jbzdak.sqlbuilder;

import java.util.Set;

/**
 * Created by: Jacek Bzdak
 */
public abstract class AbstractJoin extends IntermediateSQLObject{

   String joinType;

   Table table;

   protected AbstractJoin() {
   }

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

   protected Set<String> collectParameterNamesParant() {
      return expressionContext.collectParameterNames(joinType, table);
   }
}
