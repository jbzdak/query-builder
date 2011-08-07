package cx.ath.jbzdak.sqlbuilder.booleanExpression;

import cx.ath.jbzdak.sqlbuilder.SQLObject;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by: Jacek Bzdak
 */
public class NAryBooleanExpression extends SQLObject implements BooleanExpressionMarker {


   protected List<BooleanExpressionMarker> expressions = new ArrayList<BooleanExpressionMarker>();

   protected String type;

   public NAryBooleanExpression(String type) {
      this(type, Collections.<BooleanExpressionMarker>emptyList());
   }

   public NAryBooleanExpression(String type, Collection<BooleanExpressionMarker> expressions) {
      if(!NAryBooleanExpressionType.FAKE_ENUM.values().contains(type)){
         throw new InvalidParameterException("Unknown boolean expression type");
      }
      this.expressions.addAll(expressions);
      this.type = type;
   }

   public String getType() {
      return type;
   }

   public List<BooleanExpressionMarker> getExpressions() {
      return Collections.unmodifiableList(expressions);
   }

   public void setExpressions(List<BooleanExpressionMarker> expressions) {
      this.expressions = new ArrayList<BooleanExpressionMarker>(expressions);
   }

   public void setSqlFactories(BooleanExpressionMarker... exressions) {
      this.expressions = new ArrayList<BooleanExpressionMarker>(expressions);
   }



}



