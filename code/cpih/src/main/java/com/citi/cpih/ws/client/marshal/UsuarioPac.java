package com.citi.cpih.ws.client.marshal;


import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Mensaje")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsuarioPac implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	//ETIQUETAS XML
	@XmlElement(name = "NumEmp")
	private String numEmp;
	
	@XmlElement(name = "UsrUniversal")
	private String usrUniversal;
	
	@XmlElement(name = "Nombre")
	private String nombre;
	
	@XmlElement(name = "Appat")
	private String appat;
	
	@XmlElement(name = "Apmat")
	private String apmat;
	
	@XmlElement(name = "Region")
	private int region;
	
	@XmlElement(name = "IdPerfil")
	private String idPerfil;
	
	@XmlElement(name = "NombrePerfil")
	private String nombrePerfil;
	
	@XmlElement(name = "FvPrepagoPadre")
	private int fvPrepagoPadre;
	
	@XmlElement(name = "FvPospagoPadre")
	private String fvPospagoPadre;
	
	@XmlElement(name = "FvPrepagoPersonal")
	private int fvPrepagoPersonal;
	
	@XmlElement(name = "FvpospagoPersonal")
	private String fvpospagoPersonal;
	
	@XmlElement(name = "FvPrepagoReporte")
	private int fvPrepagoReporte;
	
	@XmlElement(name = "FvPospagoReporte")
	private String fvPospagoReporte;
	
	@XmlElement(name = "Escenario")
	private String escenario;
	
	@XmlElement(name = "Direccion")
	private String direccion;
	
	@XmlElement(name = "Subdireccion")
	private String subdireccion;
	
	@XmlElement(name = "Gerencia")
	private String gerencia;
	
	@XmlElement(name = "Departamento")
	private String departamento;
	
	@XmlElement(name = "DescDepartamento")
	private String descDepartamento;
	
	@XmlElement(name = "Puesto")
	private String puesto;
	
	@XmlElement(name = "Correo")
	private String correo;
	
	@XmlElement(name = "ProblemaId")
	private int problemaId;
	
	@XmlElement(name = "ProblemaDesc")
	private String problemaDesc;

	public String getNumEmp() {
		return numEmp;
	}

	public void setNumEmp(String numEmp) {
		this.numEmp = numEmp;
	}

	public String getUsrUniversal() {
		return usrUniversal;
	}

	public void setUsrUniversal(String usrUniversal) {
		this.usrUniversal = usrUniversal;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAppat() {
		return appat;
	}

	public void setAppat(String appat) {
		this.appat = appat;
	}

	public String getApmat() {
		return apmat;
	}

	public void setApmat(String apmat) {
		this.apmat = apmat;
	}

	public int getRegion() {
		return region;
	}

	public void setRegion(int region) {
		this.region = region;
	}

	public String getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(String idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getNombrePerfil() {
		return nombrePerfil;
	}

	public void setNombrePerfil(String nombrePerfil) {
		this.nombrePerfil = nombrePerfil;
	}

	public int getFvPrepagoPadre() {
		return fvPrepagoPadre;
	}

	public void setFvPrepagoPadre(int fvPrepagoPadre) {
		this.fvPrepagoPadre = fvPrepagoPadre;
	}

	public String getFvPospagoPadre() {
		return fvPospagoPadre;
	}

	public void setFvPospagoPadre(String fvPospagoPadre) {
		this.fvPospagoPadre = fvPospagoPadre;
	}

	public int getFvPrepagoPersonal() {
		return fvPrepagoPersonal;
	}

	public void setFvPrepagoPersonal(int fvPrepagoPersonal) {
		this.fvPrepagoPersonal = fvPrepagoPersonal;
	}

	public String getFvpospagoPersonal() {
		return fvpospagoPersonal;
	}

	public void setFvpospagoPersonal(String fvpospagoPersonal) {
		this.fvpospagoPersonal = fvpospagoPersonal;
	}

	public int getFvPrepagoReporte() {
		return fvPrepagoReporte;
	}

	public void setFvPrepagoReporte(int fvPrepagoReporte) {
		this.fvPrepagoReporte = fvPrepagoReporte;
	}

	public String getFvPospagoReporte() {
		return fvPospagoReporte;
	}

	public void setFvPospagoReporte(String fvPospagoReporte) {
		this.fvPospagoReporte = fvPospagoReporte;
	}

	public String getEscenario() {
		return escenario;
	}

	public void setEscenario(String escenario) {
		this.escenario = escenario;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getSubdireccion() {
		return subdireccion;
	}

	public void setSubdireccion(String subdireccion) {
		this.subdireccion = subdireccion;
	}

	public String getGerencia() {
		return gerencia;
	}

	public void setGerencia(String gerencia) {
		this.gerencia = gerencia;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getDescDepartamento() {
		return descDepartamento;
	}

	public void setDescDepartamento(String descDepartamento) {
		this.descDepartamento = descDepartamento;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public int getProblemaId() {
		return problemaId;
	}

	public void setProblemaId(int problemaId) {
		this.problemaId = problemaId;
	}

	public String getProblemaDesc() {
		return problemaDesc;
	}

	public void setProblemaDesc(String problemaDesc) {
		this.problemaDesc = problemaDesc;
	}

	@Override
	public String toString() {
		return "UsuarioPac [numEmp=" + numEmp + ", usrUniversal=" + usrUniversal + ", nombre=" + nombre + ", appat="
				+ appat + ", apmat=" + apmat + ", region=" + region + ", idPerfil=" + idPerfil + ", nombrePerfil="
				+ nombrePerfil + ", fvPrepagoPadre=" + fvPrepagoPadre + ", fvPospagoPadre=" + fvPospagoPadre
				+ ", fvPrepagoPersonal=" + fvPrepagoPersonal + ", fvpospagoPersonal=" + fvpospagoPersonal
				+ ", fvPrepagoReporte=" + fvPrepagoReporte + ", fvPospagoReporte=" + fvPospagoReporte + ", escenario="
				+ escenario + ", direccion=" + direccion + ", subdireccion=" + subdireccion + ", gerencia=" + gerencia
				+ ", departamento=" + departamento + ", descDepartamento=" + descDepartamento + ", puesto=" + puesto
				+ ", correo=" + correo + ", problemaId=" + problemaId + ", problemaDesc=" + problemaDesc + "]";
	}
	
	
}
