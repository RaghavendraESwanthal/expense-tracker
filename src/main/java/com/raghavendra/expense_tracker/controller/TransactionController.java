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

package com.raghavendra.expense_tracker.controller;

import com.raghavendra.expense_tracker.domain.Transaction;
import com.raghavendra.expense_tracker.dto.TransactionDto;
import com.raghavendra.expense_tracker.enums.TransactionType;
import com.raghavendra.expense_tracker.service.TransactionService;
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
@RequestMapping("/v1/transaction")
public class TransactionController {


  private final TransactionService service;

  public TransactionController(TransactionService service) {
    this.service = service;
  }

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
