package com.raghavendra.expense_tracker.service;

import com.raghavendra.expense_tracker.domain.Transaction;
import com.raghavendra.expense_tracker.dto.TransactionDto;
import com.raghavendra.expense_tracker.enums.TransactionType;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface TransactionService {
  void loadTransactionsFromFile(MultipartFile file);

  List<Transaction> getAll();

  Map<TransactionType, Double> getMonthlySummary(Integer month, Integer year);

  void addTransaction(TransactionDto transaction);
}
