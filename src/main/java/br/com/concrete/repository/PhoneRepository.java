package br.com.concrete.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.concrete.bean.Phone;

@Repository
public interface PhoneRepository extends CrudRepository <Phone, Long>{

}
