package com.example.javawebservice_session07_baitap01.service;

import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    public boolean processPayment(String accountNumber, double amount) {
        // Chỉ giữ lại logic nghiệp vụ chính
        System.out.println("SERVICE: Đang xử lý thanh toán cho tài khoản " + accountNumber);

        try {
            Thread.sleep(150); // Giả lập thời gian xử lý hệ thống
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return true;
    }
}