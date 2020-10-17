package domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class DiruMug extends Mugimendu implements Serializable {
	
	@XmlID @Id
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private Integer diruMugId;
	@XmlIDREF
	private Erabiltzaile user;
	@XmlIDREF
	private Mugimendu mu;
	
	public DiruMug() {
		super();
	}
	public DiruMug(double dirua, Date data, Erabiltzaile user) {
		super(data, dirua, user);
	}
	
	public Integer getDiruMugId() {
		return this.diruMugId;
	}
	public void setDiruMugId(Integer diruMugId) {
		this.diruMugId=diruMugId;
	}
	
}
