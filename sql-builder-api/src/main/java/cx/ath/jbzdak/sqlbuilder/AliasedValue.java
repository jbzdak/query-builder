package cx.ath.jbzdak.sqlbuilder;

/**
 * Created by: Jacek Bzdak
 */
public class AliasedValue extends PeerIntermediateSQLObject{

   final IntermediateSQLFactory factory;

   final Alias alias;

   public AliasedValue(IntermediateSQLFactory factory, Alias alias) {
      this.factory = factory;
      this.alias = alias;
   }

   public IntermediateSQLFactory getFactory() {
      return factory;
   }

   public Alias getAlias() {
      return alias;
   }
}
