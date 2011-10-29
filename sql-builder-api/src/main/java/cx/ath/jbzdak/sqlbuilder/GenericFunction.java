package cx.ath.jbzdak.sqlbuilder;

import cx.ath.jbzdak.sqlbuilder.expression.BooleanExpressionArgument;
import cx.ath.jbzdak.sqlbuilder.expression.ExpressionArgument;

import java.util.Arrays;
import java.util.List;

/**
 * Created by: Jacek Bzdak
 */
public class GenericFunction extends PeerIntermediateSQLObject implements ExpressionArgument, BooleanExpressionArgument{

   final String functionName;

   final List<IntermediateSQLFactory> arguments;


   public GenericFunction(String functionName, IntermediateSQLFactory... arguments) {
      this.functionName = functionName;
      this.arguments = Arrays.asList(arguments);
   }

   public GenericFunction(String functionName, List<IntermediateSQLFactory> arguments) {
      this.functionName = functionName;
      this.arguments = arguments;
   }

   public String getFunctionName() {
      return functionName;
   }

   public List<IntermediateSQLFactory> getArguments() {
      return arguments;
   }
}
