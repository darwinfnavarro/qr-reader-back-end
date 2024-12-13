package com.example.ProjectQr_reader.controller;

import com.example.ProjectQr_reader.service.LoteService;
import com.example.ProjectQr_reader.model.Lote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/lotes")
@CrossOrigin(origins = "http://localhost:3000")
public class LoteController {

    @Autowired
    private LoteService loteService;


    @PutMapping("/autorizar/{loteId}")
    public Lote autorizarLote(@PathVariable Integer loteId) {
        return loteService.autorizarLote(loteId);
    }


    @PutMapping("/rechazar/{loteId}")
    public Lote rechazarLote(@PathVariable Integer loteId) {
        return loteService.rechazarLote(loteId);
    }
}
