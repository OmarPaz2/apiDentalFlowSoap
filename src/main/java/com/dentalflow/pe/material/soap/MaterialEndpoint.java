package com.dentalflow.pe.material.soap;

import com.dentalflow.pe.material.entity.Material;
import com.dentalflow.pe.material.serviceImpl.MaterialServiceImpl;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@WebService(serviceName = "MaterialService")
@Component
@RequiredArgsConstructor
public class MaterialEndpoint {

    private final MaterialServiceImpl materialService;

    @WebMethod
    public List<Material> materialGetAll() {
        return materialService.materialGetAll();
    }

    @WebMethod
    public Material materialCreate(
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "stock") Integer stock,
            @WebParam(name = "stockMinimo") Integer stockMinimo,
            @WebParam(name = "costoUnitario") BigDecimal costoUnitario
    ) {
        Material material = new Material();
        material.setNombre(nombre);
        material.setStock(stock);
        material.setStockMinimo(stockMinimo);
        material.setCostoUnitario(costoUnitario);

        return materialService.materialCreate(material);
    }

    @WebMethod
    public Material materialGetById(
            @WebParam(name = "id") Integer id
    ) {
        return materialService.materialGetById(id);
    }

    @WebMethod
    public String materialUpdate(
            @WebParam(name = "id") Integer id,
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "stock") Integer stock,
            @WebParam(name = "stockMinimo") Integer stockMinimo,
            @WebParam(name = "costoUnitario") BigDecimal costoUnitario
    ) {
        Material material = new Material();
        material.setNombre(nombre);
        material.setStock(stock);
        material.setStockMinimo(stockMinimo);
        material.setCostoUnitario(costoUnitario);

        return materialService.materialUpdate(id, material);
    }

    @WebMethod
    public String materialDelete(
            @WebParam(name = "id") Integer id
    ) {
        return materialService.materialDelete(id);
    }

    @WebMethod
    public List<Material> stockCritico() {
        return materialService.stockCritico();
    }
}