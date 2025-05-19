package com.sebastiao.reciclaville.repository;

import com.sebastiao.reciclaville.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material, Long> {
}
