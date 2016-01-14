package pl.training.bank.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.training.bank.Bank;
import pl.training.bank.service.repository.AccountNotFoundException;
import pl.training.bank.transferobject.OperationTO;

@RequestMapping("api/accounts/{account-number}/operations")
@RestController
public class OperationsResource {

    private Bank bank;

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity process(@RequestBody OperationTO operation, @PathVariable("account-number") String accountNumber) {
        switch (operation.getType()) {
            case DEPOSIT:
                bank.depositFundsIntoAccount(operation.getFunds(), accountNumber);
                break;
            case WITHDRAW:
                bank.withdrawFundsFromAccount(operation.getFunds(), accountNumber);
                break;
        }
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity onAccountNotFoundException(AccountNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }

}
