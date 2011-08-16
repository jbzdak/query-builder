package cx.ath.jbzdak.sqlbuilder;

/**
 * Created by: Jacek Bzdak
 */
public interface ParameterAware {

   boolean containsParameter();

   void collectParameters(ExpressionContext expressionContext);

}
