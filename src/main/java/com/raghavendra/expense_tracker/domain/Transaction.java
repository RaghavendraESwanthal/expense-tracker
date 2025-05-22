/*
 * Trigear System Limited CONFIDENTIAL
 * Unpublished Copyright (c) 2023-2024 Trigear, All Rights Reserved.
 *
 * NOTICE: All information contained herein is, and remains the property of
 * Trigear. The intellectual and technical concepts contained herein are
 * proprietary to Trigear and may be covered by U.K. and Foreign Patents,
 * patents in process, and are protected by trade secret and copyright law.
 * Dissemination of this information or reproduction of this material is
 * strictly forbidden unless prior written permission is obtained from Trigear.
 * Access to the source code contained herein is hereby forbidden to anyone
 * except current Trigear employees, managers or contractors who have executed
 * Confidentiality and Non-disclosure agreements explicitly covering such access.
 *
 * The copyright notice above does not evidence any actual or intended
 * publication or disclosure of this source code, which includes information
 * that is confidential and/or proprietary, and is a trade secret, of Trigear.
 * ANY REPRODUCTION, MODIFICATION, DISTRIBUTION, PUBLIC PERFORMANCE, OR PUBLIC
 * DISPLAY OF OR THROUGH USE OF THIS SOURCE CODE WITHOUT THE EXPRESS WRITTEN
 * CONSENT OF Trigear IS STRICTLY PROHIBITED, AND IN VIOLATION OF APPLICABLE
 * LAWS AND INTERNATIONAL TREATIES. THE RECEIPT OR POSSESSION OF THIS SOURCE
 * CODE AND/OR RELATED INFORMATION DOES NOT CONVEY OR IMPLY ANY RIGHTS TO
 * REPRODUCE, DISCLOSE OR DISTRIBUTE ITS CONTENTS, OR TO MANUFACTURE, USE, OR
 * SELL ANYTHING THAT IT MAY DESCRIBE, IN WHOLE OR IN PART.
 */

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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public TransactionType getType() {
    return type;
  }

  public void setType(TransactionType type) {
    this.type = type;
  }

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
