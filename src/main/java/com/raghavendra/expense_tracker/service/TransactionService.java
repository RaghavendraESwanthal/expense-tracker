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
