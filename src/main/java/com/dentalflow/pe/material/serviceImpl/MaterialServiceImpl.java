package com.dentalflow.pe.material.serviceImpl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.dentalflow.pe.material.entity.Material;
import com.dentalflow.pe.material.repository.IMaterialRepository;
import com.dentalflow.pe.material.service.MaterialService;

@Service
public class MaterialServiceImpl implements MaterialService {

    private final IMaterialRepository repository;

    public MaterialServiceImpl(IMaterialRepository repository) {
        this.repository = repository;
    }

	@Override
	public List<Material> materialGetAll() {
		return repository.findAll();
	}

    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPCIONISTA')")
	@Override
	public Material materialCreate(Material material) {
		if(material.getStock() < 0){
            throw new RuntimeException("El stock no puede ser negativo");
        }

        if(material.getStockMinimo() < 0){
            throw new RuntimeException("El stock mínimo no puede ser negativo");
        }

        if(material.getCostoUnitario().compareTo(BigDecimal.ZERO) < 0){
            throw new RuntimeException("El costo unitario no puede ser negativo");
        }

        return repository.save(material);
	}

    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPCIONISTA')")
	@Override
	public Material materialGetById(Integer id) {
		return repository.findById(id).orElse(null);
	}

    @PreAuthorize("hasRole('ADMIN' or hasRole('RECEPCIONISTA')")
	@Override
	public String materialUpdate(Integer id, Material material) {
		Material existente = repository.findById(id).orElse(null);

        if(existente == null){
            return "Material no encontrado";
        }

        existente.setNombre(material.getNombre());
        existente.setStock(material.getStock());
        existente.setStockMinimo(material.getStockMinimo());
        existente.setCostoUnitario(material.getCostoUnitario());

        repository.save(existente);

        return "Material actualizado correctamente";
	}

    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPCIONISTA')")
	@Override
	public String materialDelete(Integer id) {
		if(!repository.existsById(id)){
            return "Material no encontrado";
        }

        repository.deleteById(id);

        return "Material eliminado correctamente";
	}

    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPCIONISTA')")
	@Override
	public List<Material> stockCritico() {
		return repository.findAll()
                .stream()
                .filter(m -> m.getStock() <= m.getStockMinimo())
                .toList();
	}
    
}
