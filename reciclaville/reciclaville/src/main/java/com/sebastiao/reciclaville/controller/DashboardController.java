package com.sebastiao.reciclaville.controller;

import com.sebastiao.reciclaville.dto.DashboardItem;
import com.sebastiao.reciclaville.service.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    @GetMapping
    public List<DashboardItem> listarDashboard() {
        return service.getDashboardData();
    }
}
