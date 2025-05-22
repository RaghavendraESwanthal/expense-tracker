package com.raghavendra.expense_tracker.service;

import com.raghavendra.expense_tracker.domain.Transaction;
import com.raghavendra.expense_tracker.dto.TransactionDto;
import com.raghavendra.expense_tracker.enums.TransactionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class TransactionServiceTest {

  private TransactionService transactionService;

  @BeforeEach
  void setUp() {
    transactionService = new TransactionServiceImpl();
  }

  @Test
  void testAddTransaction() {
    TransactionDto tx = new TransactionDto(TransactionType.INCOME, "salary", 1200.0, LocalDate.of(2024, 5, 1));
    transactionService.addTransaction(tx);

    List<Transaction> transactions = transactionService.getAll();
    assertEquals(1, transactions.size());
    assertEquals("salary", transactions.get(0).getCategory());
    assertNotNull(transactions.get(0).getId());
  }

  @Test
  void testGetMonthlySummary() {
    transactionService.addTransaction(new TransactionDto(TransactionType.INCOME, "salary", 1000.0, LocalDate.of(2024, 5, 1)));
    transactionService.addTransaction(new TransactionDto(TransactionType.EXPENSE, "food", 300.0, LocalDate.of(2024, 5, 5)));

    Map<TransactionType, Double> summary = transactionService.getMonthlySummary(5, 2024);

    assertEquals(2, summary.size());
    assertEquals(1000.0, summary.get(TransactionType.INCOME));
    assertEquals(300.0, summary.get(TransactionType.EXPENSE));
  }

  @Test
  void testLoadTransactionsFromFile() throws IOException {
    String csvData = """
      type,category,amount,date
      income,salary,1000.00,2024-05-10
      expense,food,200.00,2024-05-02
      """;

    MockMultipartFile file = new MockMultipartFile(
      "file",
      "transactions.csv",
      "text/csv",
      new ByteArrayInputStream(csvData.getBytes(StandardCharsets.UTF_8))
    );

    transactionService.loadTransactionsFromFile(file);
    List<Transaction> result = transactionService.getAll();

    assertEquals(2, result.size());
    assertEquals(TransactionType.INCOME, result.get(0).getType());
    assertEquals("salary", result.get(0).getCategory());
    assertEquals(1000.0, result.get(0).getAmount());
  }

  @Test
  void testGetAllTransactions() {
    // Arrange
    TransactionDto t1 = new TransactionDto(TransactionType.INCOME, "salary", 1000.0, LocalDate.of(2024, 5, 1));
    TransactionDto t2 = new TransactionDto(TransactionType.EXPENSE, "rent", 500.0, LocalDate.of(2024, 5, 2));

    transactionService.addTransaction(t1);
    transactionService.addTransaction(t2);

    // Act
    List<Transaction> allTransactions = transactionService.getAll();

    // Assert
    assertEquals(2, allTransactions.size());

    Transaction fetched1 = allTransactions.get(0);
    Transaction fetched2 = allTransactions.get(1);

    assertEquals("salary", fetched1.getCategory());
    assertEquals(1000.0, fetched1.getAmount());
    assertEquals(TransactionType.INCOME, fetched1.getType());

    assertEquals("rent", fetched2.getCategory());
    assertEquals(500.0, fetched2.getAmount());
    assertEquals(TransactionType.EXPENSE, fetched2.getType());
  }
}