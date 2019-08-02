
package ecci_tictactoe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="numFila" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="numColumna" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="simbolo" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "numFila",
    "numColumna",
    "simbolo"
})
@XmlRootElement(name = "setJugada")
public class SetJugada {

    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer numFila;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer numColumna;
    @XmlElement(required = true, nillable = true)
    protected String simbolo;

    /**
     * Obtiene el valor de la propiedad numFila.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumFila() {
        return numFila;
    }

    /**
     * Define el valor de la propiedad numFila.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumFila(Integer value) {
        this.numFila = value;
    }

    /**
     * Obtiene el valor de la propiedad numColumna.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumColumna() {
        return numColumna;
    }

    /**
     * Define el valor de la propiedad numColumna.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumColumna(Integer value) {
        this.numColumna = value;
    }

    /**
     * Obtiene el valor de la propiedad simbolo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSimbolo() {
        return simbolo;
    }

    /**
     * Define el valor de la propiedad simbolo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSimbolo(String value) {
        this.simbolo = value;
    }

}
