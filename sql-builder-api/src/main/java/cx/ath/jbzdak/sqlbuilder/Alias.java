package cx.ath.jbzdak.sqlbuilder;

import java.util.Collections;
import java.util.Set;

/**
 * Created by: Jacek Bzdak
 */
public class Alias extends IntermediateSQLObject implements Identifier{

   String alias;

   public String getAlias() {
      return alias;
   }

   public Alias(String alias) {
      this.alias = alias;
   }

   public ColumnExpression column(String column){
      ColumnExpression columnExpression = new ColumnExpression(alias, column);
      return columnExpression;
   }

   public Set<String> collectParameterNames() {
      return Collections.emptySet();
   }
}
