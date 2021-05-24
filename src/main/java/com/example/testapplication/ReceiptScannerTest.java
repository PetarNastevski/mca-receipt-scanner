package com.example.testapplication;

import com.example.network.controller.ReceiptController;


public class ReceiptScannerTest {
    public static void main(String[] args) {
        ReceiptController receiptController = new ReceiptController();
        receiptController.start();
    }
}