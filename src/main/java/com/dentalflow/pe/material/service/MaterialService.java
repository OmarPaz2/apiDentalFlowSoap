package com.dentalflow.pe.material.service;

import java.util.List;

import com.dentalflow.pe.material.entity.Material;


import jakarta.jws.WebService;

@WebService
public interface MaterialService {

   
    List<Material> materialGetAll();

    
    Material materialCreate(Material material);

    
    Material materialGetById(Integer id);

    
    String materialUpdate(Integer id, Material material);

    
    String materialDelete(Integer id);

   
    List<Material> stockCritico();
}