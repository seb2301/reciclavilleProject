package com.sebastiao.reciclaville.service;

import com.sebastiao.reciclaville.dto.DashboardItem;
import com.sebastiao.reciclaville.repository.ItemDeclaracaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {

    private final ItemDeclaracaoRepository itemRepo;

    public DashboardService(ItemDeclaracaoRepository itemRepo) {
        this.itemRepo = itemRepo;
    }


    public List<DashboardItem> getDashboardData() {
        return itemRepo.aggregateCompensacaoPorMaterial();
    }
}
