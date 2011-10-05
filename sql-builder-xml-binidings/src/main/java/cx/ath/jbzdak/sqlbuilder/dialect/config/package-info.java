
@XmlSchema(
        namespace = Namespace.NAMESPACE,
        xmlns = {@XmlNs(prefix = "sql", namespaceURI = Namespace.NAMESPACE)},
        elementFormDefault = XmlNsForm.QUALIFIED,
        attributeFormDefault = XmlNsForm.UNQUALIFIED
)
package cx.ath.jbzdak.sqlbuilder.dialect.config;

import cx.ath.jbzdak.sqlbuilder.Namespace;

import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;