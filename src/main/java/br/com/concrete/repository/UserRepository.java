package br.com.concrete.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.concrete.bean.User;

@Repository
public interface UserRepository extends CrudRepository <User, Long>{

    User findByEmail(String email);
}
