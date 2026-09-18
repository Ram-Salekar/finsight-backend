package com.finsight.controller;

import com.finsight.apimodel.TransactionRequest;
import com.finsight.apimodel.TransactionResponse;
import com.finsight.apitomanagermapper.TransactionApiMapper;
import com.finsight.manager.ITransactionManager;
import com.finsight.managermodel.TransactionManagerModel;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final ITransactionManager transactionManager;
    private final TransactionApiMapper transactionApiMapper;

    public TransactionController(ITransactionManager transactionManager, TransactionApiMapper transactionApiMapper) {
        this.transactionManager = transactionManager;
        this.transactionApiMapper = transactionApiMapper;
    }

    @GetMapping
    public List<TransactionResponse> getAllTransactions() {
        return transactionManager.getAllTransactions().stream()
                .map(transactionApiMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TransactionResponse getTransactionById(@PathVariable Long id) {
        return transactionApiMapper.toResponse(transactionManager.getTransactionById(id));
    }

    @PostMapping
    public TransactionResponse createTransaction(@Valid @RequestBody TransactionRequest request) {
        TransactionManagerModel model = transactionApiMapper.toManagerModel(request);
        return transactionApiMapper.toResponse(transactionManager.createTransaction(model));
    }

    @PutMapping("/{id}")
    public TransactionResponse updateTransaction(@PathVariable Long id, @Valid @RequestBody TransactionRequest request) {
        TransactionManagerModel model = transactionApiMapper.toManagerModel(request);
        return transactionApiMapper.toResponse(transactionManager.updateTransaction(id, model));
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        transactionManager.deleteTransaction(id);
    }
}
