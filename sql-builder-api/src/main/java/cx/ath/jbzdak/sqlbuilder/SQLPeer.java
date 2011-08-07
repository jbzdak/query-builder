package cx.ath.jbzdak.sqlbuilder;

/**
 * Created by: Jacek Bzdak
 */
public interface SQLPeer<T extends IntermediateSQLFactory>{

//   StringBuilder toSQL();

   void appendTo(StringBuilder stringBuilder);

//   StringBuilder toSQL(RenderingContext renderContext);
//
   void appendTo(RenderingContext renderingContext, StringBuilder stringBuilder);

}
