package com.training0802.demo.service.mysql;

import com.training0802.demo.dto.AccountInfoToUserDetails;
import com.training0802.demo.model.mysql.Account;
import com.training0802.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
@Profile("mysql")
public class AccountInfoToUserDetailsService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> account = accountRepository.findByUsername(username);
        return account.map(AccountInfoToUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("not found this username"));
    }
}
