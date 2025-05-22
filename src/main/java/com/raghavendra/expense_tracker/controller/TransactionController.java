package com.raghavendra.expense_tracker.controller;

import com.raghavendra.expense_tracker.domain.Transaction;
import com.raghavendra.expense_tracker.dto.TransactionDto;
import com.raghavendra.expense_tracker.enums.TransactionType;
import com.raghavendra.expense_tracker.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/transaction")
public class TransactionController {

  private final TransactionService service;

  @PostMapping()
  public String addTransaction(@RequestBody TransactionDto transaction) {
    service.addTransaction(transaction);
    return "Transaction added successfully";
  }

  @PostMapping("/upload")
  public String uploadFile(@RequestParam("file") MultipartFile file) {
    if (Objects.isNull(file)) {
      throw new RuntimeException("File not found");
    }
    service.loadTransactionsFromFile(file);
    return "File processed successfully";
  }

  @GetMapping("/summary")
  public Map<TransactionType, Double> getMonthlySummary(@RequestParam Integer month, @RequestParam Integer year) {
    return service.getMonthlySummary(month, year);
  }

  @GetMapping()
  public List<Transaction> getAllTransactions() {
    return service.getAll();
  }
}
