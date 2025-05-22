package com.raghavendra.expense_tracker.service;

import com.raghavendra.expense_tracker.domain.Transaction;
import com.raghavendra.expense_tracker.dto.TransactionDto;
import com.raghavendra.expense_tracker.enums.TransactionType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

  private final List<Transaction> transactions = new ArrayList<>();
  private Long currentId = 1L;

  @Override
  public void addTransaction(TransactionDto transactionDto) {
    Transaction transaction = new Transaction(transactionDto.getType(), transactionDto.getCategory(), transactionDto.getAmount(), transactionDto.getDate());
    transaction.setId(currentId++);
    transactions.add(transaction);
  }

  /**
   * Sample input file format (CSV):
   * income,salary,1000.00,2024-05-01
   * expense,food,200.00,2024-05-02
   * income,business,500.00,2024-05-10
   * expense,travel,300.00,2024-05-12
   */
  @Override
  public void loadTransactionsFromFile(MultipartFile file) {
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
      String line;
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

      // skip headers
      reader.readLine();

      while ((line = reader.readLine()) != null) {
        String[] parts = line.split(",");
        if (parts.length == 4) {
          String type = parts[0];
          String category = parts[1];
          double amount = Double.parseDouble(parts[2]);
          LocalDate date;
          try {
            date = LocalDate.parse(parts[3], formatter);
          } catch (DateTimeParseException e) {
            throw new RuntimeException("Invalid date format: " + parts[3]);
          }
          Transaction t = new Transaction(TransactionType.valueOf(type.toUpperCase()), category, amount, date);
          t.setId(currentId++);
          transactions.add(t);
        }
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    } finally {
      try {
        if (reader != null)
          reader.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public List<Transaction> getAll() {
    return transactions;
  }

  @Override
  public Map<TransactionType, Double> getMonthlySummary(Integer month, Integer year) {
    return transactions.stream()
      .filter(t -> t.getDate().getMonthValue() == month && t.getDate().getYear() == year)
      .collect(Collectors.groupingBy(
        Transaction::getType,
        Collectors.summingDouble(Transaction::getAmount)
      ));
  }
}
