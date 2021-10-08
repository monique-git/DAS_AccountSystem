package za.ac.nwu.das.translator.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.das.domain.dto.AccountTypeDto;
import za.ac.nwu.das.domain.persistence.AccountType;
import za.ac.nwu.das.repo.persistence.AccountTypeRepo;
import za.ac.nwu.das.translator.AccountTypeTranslator;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountTypeTranslatorImp implements AccountTypeTranslator {

    private final AccountTypeRepo accountTypeRepo;

    @Autowired
    public AccountTypeTranslatorImp(AccountTypeRepo accountTypeRepo){

        this.accountTypeRepo = accountTypeRepo;
    }

    @Override
    public List<AccountTypeDto> getAllAccountTypes() {

        List<AccountTypeDto> accountTypeDtos = new ArrayList<>();
        try{
            for(AccountType accountType : accountTypeRepo.findAll()){
                accountTypeDtos.add(new AccountTypeDto(accountType));
            }
        }catch (Exception ex){
            //TODO: log
            throw new RuntimeException("Unable to read from the DB", ex);
        }
        return accountTypeDtos;
    }

    @Override
    public AccountTypeDto create(AccountTypeDto accountTypeDto) {

        try{
            AccountType accountType = accountTypeRepo.save(accountTypeDto.getAccountType());
            return new AccountTypeDto(accountType);
        }catch(Exception ex){
            //TODO: log
            throw new RuntimeException("Unable to save to the DB", ex);
        }

    }

    @Override
    public AccountTypeDto getAccountTypeByMnemonic(String mnemonic){
        try{
            AccountType accountType = accountTypeRepo.getAccountTypeByMnemonic(mnemonic);
            return new AccountTypeDto(accountType);
        }catch(Exception ex){
            throw new RuntimeException("Unable to read from the DB", ex);
        }
    }

//    @Override
//    public AccountTypeDto getAccountTypeDtoByMnemonic(String mnemonic){
//        try{
//            return accountTypeRepo.getAccountTypeDtoByMnemonic(mnemonic);
//        }catch(Exception ex){
//            throw new RuntimeException("Unable to read from the DB", ex);
//        }
//    }
}
