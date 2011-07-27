package cx.ath.jbzdak.sqlbuilder;

import java.util.List;

/**
 * Created by: Jacek Bzdak
 */
public class JoinUsing extends AbstractJoin {

   List<String> columns;

   public JoinUsing(String joinType, Table table, List<String> columns) {
      super(joinType, table);
      this.columns = columns;
   }

   public List<String> getColumns() {
      return columns;
   }
}
