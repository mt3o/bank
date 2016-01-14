package pl.training.bank.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.training.bank.Bank;
import pl.training.bank.entity.Account;
import pl.training.bank.service.repository.AccountsRepository;
import pl.training.bank.transferobject.AccountTO;
import pl.training.bank.transferobject.PageTO;
import pl.training.bank.transferobject.TransferObjectMapper;

import java.net.URI;
import java.util.List;

@RequestMapping("api/accounts")
@RestController
public class AccountsResource {

    private Bank bank;
    private AccountsRepository accountsRepository;
    private TransferObjectMapper transferObjectMapper;

    @Autowired
    public AccountsResource(Bank bank, AccountsRepository accountsRepository, TransferObjectMapper transferObjectMapper) {
        this.bank = bank;
        this.accountsRepository = accountsRepository;
        this.transferObjectMapper = transferObjectMapper;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createAccount() {
        Account account = bank.createAccount();
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{/id}").buildAndExpand(account.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public PageTO<AccountTO> getAccounts(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) {
        Page<Account> page = accountsRepository.findAll(new PageRequest(pageNumber, pageSize));
        List<AccountTO> accountTOs = transferObjectMapper.map(page.getContent(), AccountTO.class);
        return new PageTO<AccountTO>(page.getTotalPages(), accountTOs);
    }

}
