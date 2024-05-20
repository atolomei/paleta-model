package io.paleta.model;

import java.io.Serializable;

public class TablaPosicion extends JsonObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public String equipo;
	public int puntos;
	public int tantosFavor;
	public int tantosContra;
	public int difTantos;
	public int setGanados;
	public int setPerdidos;
	public int difSets;
	public int partidosJugados;
	public int partidosGanados;
	public int partidosPerdidos;
	public int puntosDescontados;
	public int reprogramados;
	public String nota;
	
	
	public String getEquipo() {
		return equipo;
	}
	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	public int getTantosFavor() {
		return tantosFavor;
	}
	public void setTantosFavor(int tantosFavor) {
		this.tantosFavor = tantosFavor;
	}
	public int getTantosContra() {
		return tantosContra;
	}
	public void setTantosContra(int tantosContra) {
		this.tantosContra = tantosContra;
	}
	public int getDifTantos() {
		return difTantos;
	}
	public void setDifTantos(int difTantos) {
		this.difTantos = difTantos;
	}
	public int getSetGanados() {
		return setGanados;
	}
	public void setSetGanados(int setGanados) {
		this.setGanados = setGanados;
	}
	public int getSetPerdidos() {
		return setPerdidos;
	}
	public void setSetPerdidos(int setPerdidos) {
		this.setPerdidos = setPerdidos;
	}
	public int getDifSets() {
		return difSets;
	}
	public void setDifSets(int difSets) {
		this.difSets = difSets;
	}
	public int getPartidosJugados() {
		return partidosJugados;
	}
	public void setPartidosJugados(int partidosJugados) {
		this.partidosJugados = partidosJugados;
	}
	public int getPartidosPerdidos() {
		return partidosPerdidos;
	}
	public void setPartidosPerdidos(int partidosPerdidos) {
		this.partidosPerdidos = partidosPerdidos;
	}
	
	public int getPartidosGanados() {
		return partidosGanados;
	}
	public void setPartidosGanados(int partidosGanados) {
		this.partidosGanados = partidosGanados;
	}
	
	
	public int getPuntosDescontados() {
		return puntosDescontados;
	}
	public void setPuntosDescontados(int puntosDescontados) {
		this.puntosDescontados = puntosDescontados;
	}
	public int getReprogramados() {
		return reprogramados;
	}
	public void setReprogramados(int reprogramados) {
		this.reprogramados = reprogramados;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	

	
}
