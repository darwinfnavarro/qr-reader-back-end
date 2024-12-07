package com.example.ProjectQr_reader.controller;

import org.springframework.web.bind.annotation.*;
import com.example.ProjectQr_reader.service.QrReaderService;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/v1")
public class QrReaderController {

    private final QrReaderService qrReaderService;

    public QrReaderController(QrReaderService qrReaderService) {
        this.qrReaderService = qrReaderService;
    }

    @GetMapping("/qr/{imei}")
    public String getQrData(@PathVariable String imei) {
        return qrReaderService.getImei(imei);
    }

    @GetMapping("/store")
    public String getStoreData() {
        return qrReaderService.getStore();
    }

    @GetMapping("/platform")
    public String getPlatformData() {
        return qrReaderService.getPlatform();
    }

    @GetMapping("/test")
    public String getQrData() {
        return "Api works with normality";
    }
}
