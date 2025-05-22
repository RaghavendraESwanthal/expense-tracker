package com.raghavendra.expense_tracker.dto;

import com.raghavendra.expense_tracker.enums.TransactionType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class TransactionDto {

  private TransactionType type;
  private String category;
  private Double amount;
  private LocalDate date;
}
