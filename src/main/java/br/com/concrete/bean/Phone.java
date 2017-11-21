package br.com.concrete.bean;

import javax.persistence.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Phone {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="cadastro_id")
    private User cadastro;

    private String ddd;
    private String number;


    public Phone() {
    }

    public Phone(String ddd, String number) {
        this.setDdd(ddd);
        this.setNumber(number);
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public User getCadastro() {
        return cadastro;
    }


    public void setCadastro(User cadastro) {
        this.cadastro = cadastro;
    }


    public String getDdd() {
        return ddd;
    }


    public void setDdd(String ddd) {
        this.ddd = ddd;
    }


    public String getNumber() {
        return number;
    }


    public void setNumber(String number) {
        this.number = number;
    }

    public String toString() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(this);
        }
        catch (JsonProcessingException e) {
        	return "{\"mensagem\":\"" + e.getMessage() + "\"}";
        }
    }
}
