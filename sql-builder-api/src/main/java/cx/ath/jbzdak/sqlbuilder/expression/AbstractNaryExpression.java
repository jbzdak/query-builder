package cx.ath.jbzdak.sqlbuilder.expression;

import cx.ath.jbzdak.sqlbuilder.IntermediateSQLFactory;
import cx.ath.jbzdak.sqlbuilder.PeerIntermediateSQLObject;

import java.security.InvalidParameterException;
import java.util.*;

/**
 * Created by: Jacek Bzdak
 */
public abstract class AbstractNaryExpression<EM extends IntermediateSQLFactory> extends PeerIntermediateSQLObject {


   protected List<EM> expressions = new ArrayList<EM>();

   protected String type;

   public AbstractNaryExpression(String type) {
      this(type, Collections.<EM>emptyList());
   }

   public AbstractNaryExpression(String type, EM... markers) {
      this(type, Arrays.asList(markers));
   }

   public AbstractNaryExpression(String type, Collection<? extends EM> expressions) {
      if(!NAryExpressionType.FAKE_ENUM.values().contains(type)){
         throw new InvalidParameterException("Unknown boolean expression type");
      }
      this.expressions.addAll(expressions);
      this.type = type;
   }

   public String getType() {
      return type;
   }

   public List<EM> getExpressions() {
      return Collections.unmodifiableList(expressions);
   }

   public List<EM> getExpressionsWithoutUnboundParams(){
      List<EM> result = new ArrayList<EM>();
      for (EM expression : expressions) {
         if(!expression.containsUnboundParams()){
            result.add(expression);
         }
      }
      return result;
   }

   public void setExpressions(List<EM> expressions) {
      this.expressions = new ArrayList<EM>(expressions);
   }

   public boolean addExpression(EM booleanExpressionMarker) {
      return expressions.add(booleanExpressionMarker);
   }
}
