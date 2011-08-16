package cx.ath.jbzdak.sqlbuilder;

import java.util.List;
import java.util.Set;

/**
 * Created by: Jacek Bzdak
 */
public class JoinUsing extends AbstractJoin {

   List<String> columns;

   public JoinUsing() {
   }

   public JoinUsing(String joinType, Table table, List<String> columns) {
      super(joinType, table);
      this.columns = columns;
   }

   public List<String> getColumns() {
      return columns;
   }

    public Set<String> collectParameterNames() {
      return expressionContext.collectParameterNames(collectParameterNamesParant(), columns.toArray());
   }
}
