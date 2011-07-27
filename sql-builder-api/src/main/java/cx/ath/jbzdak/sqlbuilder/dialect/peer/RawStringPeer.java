package cx.ath.jbzdak.sqlbuilder.dialect.peer;

import cx.ath.jbzdak.sqlbuilder.RawString;

/**
 * Created by: Jacek Bzdak
 */
public class RawStringPeer extends AbstractPeer<RawString>{
   public void appendTo(StringBuilder stringBuilder) {
      stringBuilder.append(' ');
      stringBuilder.append(parent.getRawString());
      stringBuilder.append(' ');
   }
}
