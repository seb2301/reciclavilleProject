package com.sebastiao.reciclaville.repository;

import com.sebastiao.reciclaville.dto.DashboardItem;
import com.sebastiao.reciclaville.model.ItemDeclaracao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemDeclaracaoRepository extends JpaRepository<ItemDeclaracao, Long> {


    @Query("SELECT new com.sebastiao.reciclaville.dto.DashboardItem(i.material.nome, SUM(i.toneladasCompensacao)) "
            + "FROM ItemDeclaracao i "
            + "GROUP BY i.material.nome")
    List<DashboardItem> aggregateCompensacaoPorMaterial();
}
