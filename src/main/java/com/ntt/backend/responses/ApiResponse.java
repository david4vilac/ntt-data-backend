package com.ntt.backend.responses;

public class ApiResponse<T> {
    private Integer status;
    private String mensaje;
    private T data;

    public ApiResponse(Integer status, String mensaje, T data) {
        this.status = status;
        this.mensaje = mensaje;
        this.data = data;
    }

    // Getters y Setters

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}