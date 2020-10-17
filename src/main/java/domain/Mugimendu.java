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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso ({Apustua.class, DiruMug.class})
@Entity
public class Mugimendu implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@XmlID @Id
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private Integer mugId;
	private Date data;
	private double dirua;
	@XmlIDREF
	private Erabiltzaile user;
	
	public Mugimendu() {
		super();
	}
	public Mugimendu(Date data, double dirua, Erabiltzaile user) {
		super();
		this.data=data;
		this.dirua=dirua;
	}
	
	public Mugimendu(Integer mugId, Date data, double dirua, Erabiltzaile user) {
		super();
		this.data=data;
		this.mugId=mugId;
		this.dirua=dirua;
	}
	
	public Integer getMugId(){
		return this.mugId;
	}
	public void setMugId(Integer mugId){
		this.mugId=mugId;
	}
		
	public Date getData(){
		return this.data;
	}
	public void setData(Date data){
		this.data=data;
	}
	
	public double getDirua() {
		return this.dirua;
	}
	public void setDirua(double dirua) {
		this.dirua=dirua;
	}
	
	public Erabiltzaile getUser(){
		return this.user;
	}
	public void setUser(Erabiltzaile user){
		this.user=user;
	}
}
