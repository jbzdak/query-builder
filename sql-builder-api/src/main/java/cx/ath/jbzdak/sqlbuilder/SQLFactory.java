package cx.ath.jbzdak.sqlbuilder;

/**
 * Created by: Jacek Bzdak
 */
public interface SQLFactory extends IntermediateSQLFactory {

   StringBuilder toSQL();

//   void appendTo(StringBuilder stringBuilder);


}
