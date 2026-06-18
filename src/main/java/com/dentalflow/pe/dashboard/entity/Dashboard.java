package com.dentalflow.pe.dashboard.entity;

public class Dashboard {
	private Long citasDelDia;
    private Long tratamientosActivos;
    private Long pagosRealizados;
    private Long stockCritico;

    public Dashboard() {
    }

    public Long getCitasDelDia() {
        return citasDelDia;
    }

    public void setCitasDelDia(Long citasDelDia) {
        this.citasDelDia = citasDelDia;
    }

    public Long getTratamientosActivos() {
        return tratamientosActivos;
    }

    public void setTratamientosActivos(Long tratamientosActivos) {
        this.tratamientosActivos = tratamientosActivos;
    }

    public Long getPagosRealizados() {
        return pagosRealizados;
    }

    public void setPagosRealizados(Long pagosRealizados) {
        this.pagosRealizados = pagosRealizados;
    }

    public Long getStockCritico() {
        return stockCritico;
    }

    public void setStockCritico(Long stockCritico) {
        this.stockCritico = stockCritico;
    }
}
