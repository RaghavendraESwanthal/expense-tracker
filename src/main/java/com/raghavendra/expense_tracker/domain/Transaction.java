package com.raghavendra.expense_tracker.domain;

import com.raghavendra.expense_tracker.enums.TransactionType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private TransactionType type;

  private String category;

  private Double amount;

  private LocalDate date;

  public Transaction(TransactionType type, String category, double amount, LocalDate date) {
    this.type = type;
    this.category = category;
    this.amount = amount;
    this.date = date;
  }
}
