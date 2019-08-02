
package ecci_tictactoe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="obteneryResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "obteneryResult"
})
@XmlRootElement(name = "obteneryReturn")
public class ObteneryReturn {

    protected int obteneryResult;

    /**
     * Obtiene el valor de la propiedad obteneryResult.
     * 
     */
    public int getObteneryResult() {
        return obteneryResult;
    }

    /**
     * Define el valor de la propiedad obteneryResult.
     * 
     */
    public void setObteneryResult(int value) {
        this.obteneryResult = value;
    }

}
