package xyz.dddog.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.dddog.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
	public Account findByUserid(String userid);
}
