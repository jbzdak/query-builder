package cx.ath.jbzdak.sqlbuilder;

/**
 * Created by: Jacek Bzdak
 */
public interface SQLPeer<T extends IntermediateSQLFactory>{

   void appendTo(RenderingContext renderingContext, StringBuilder stringBuilder);

   void registerParent(T t);
}
