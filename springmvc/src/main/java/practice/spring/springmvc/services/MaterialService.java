package practice.spring.springmvc.services;

import practice.spring.springmvc.model.Material;

public interface MaterialService {

    public Material findByProductIdAndMaterialId(Long productId, Long materialId);

    public Material saveMaterial(Material material);
}
