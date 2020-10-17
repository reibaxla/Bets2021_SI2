package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Vector;

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
public class Apustua extends Mugimendu implements Serializable {
	
	
	@XmlID @Id
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private Integer apustuId;
	private boolean irabazi;
	private Date firstEventDate;
	@XmlIDREF
	private Vector<Kuota> kuota= new Vector<Kuota>();
	@XmlIDREF
	private Erabiltzaile user;
	@XmlIDREF
	private Mugimendu mu;
	
	public Apustua() {
		super();
	}
	
	public Apustua(double dirua, Vector<Kuota> kuota, Date firstEventDate, Date data, Erabiltzaile user) {
		super(data, dirua, user);
		this.kuota=kuota;
		this.firstEventDate=firstEventDate;
	}
	
	public Apustua(Integer apustuId, double dirua, Vector<Kuota> kuota, Date firstEventDate, Date data, Erabiltzaile user) {
		super(data, dirua, user);
		this.apustuId=apustuId;
		this.kuota=kuota;
		this.firstEventDate=firstEventDate;
	}
	
	public Apustua(Integer apustuId, double dirua, Vector<Kuota> kuota, Date firstEventDate, Date data, Erabiltzaile user, boolean Irabazi) {
		super(data, dirua, user);
		this.apustuId=apustuId;
		this.irabazi=Irabazi;
		this.firstEventDate=firstEventDate;
	}
	
	public Integer getApustuId() {
		return this.apustuId;
	}
	public void setApustuId(Integer apustuId) {
		this.apustuId=apustuId;
	}
	
	public Vector<Kuota> getKuota() {
		return this.kuota;
	}
	public void setKuota(Vector<Kuota> kuota) {
		this.kuota=kuota;
	}
	
	public Date getFirstEventDate() {
		return this.firstEventDate;
	}
	public void setFirstEventDate(Date firstEventDate) {
		this.firstEventDate=firstEventDate;
	}
	
	public Erabiltzaile getUser() {
		return this.user;
	}
	public void setUser(Erabiltzaile user) {
		this.user=user;
	}
	
	public boolean getIrabazi() {
		return this.irabazi;
	}
	public void setIrabazi(boolean irabazi) {
		this.irabazi=irabazi;
	}
	
}
