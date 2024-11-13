package io.paleta.model;

import java.io.Serializable;

public class PaletaSet extends JsonObject implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public PaletaSet( int local, int visit) {
		puntosLocal = local;
		puntosVisitante = visit;
	}
	
	public int getPuntosLocal() {
		return puntosLocal;
	}
	public void setPuntosLocal(int puntosLocal) {
		this.puntosLocal = puntosLocal;
	}
	public int getPuntosVisitante() {
		return puntosVisitante;
	}
	public void setPuntosVisitante(int puntosVisitante) {
		this.puntosVisitante = puntosVisitante;
	}
	public int getSetNumero() {
		return setNumero;
	}
	public void setSetNumero(int setNumero) {
		this.setNumero = setNumero;
	}
	public boolean isCompletado() {
		return completado;
	}
	public void setCompletado(boolean completado) {
		this.completado = completado;
	}
	public int getMinutoLocal() {
		return minutoLocal;
	}
	public void setMinutoLocal(int minutoLocal) {
		this.minutoLocal = minutoLocal;
	}
	public int getMinutoVisitante() {
		return minutoVisitante;
	}
	public void setMinutoVisitante(int minutoVisitante) {
		this.minutoVisitante = minutoVisitante;
	}

	public int puntosLocal;
	public int puntosVisitante;
	
	public int setNumero;
	public boolean completado;
	
	public int minutoLocal;
	public int minutoVisitante;
	
	public Boolean isLocalWinner() {
		return Boolean.valueOf(completado && (puntosLocal>puntosVisitante));
	}

	public Boolean isVisitorWinner() {
		return Boolean.valueOf( completado && (puntosLocal<puntosVisitante));
	}
	
}
