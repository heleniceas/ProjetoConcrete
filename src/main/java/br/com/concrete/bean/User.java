package br.com.concrete.bean;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable=false)
    @NotNull
    private String name;

    @Column(unique = true, nullable=false)
    @NotNull(message = "O email é obrigatório")
    @Size(min = 1, max = 100)
    private String email;

    @Column(nullable=false)
    @NotNull
    private String password;

    @OneToMany(mappedBy = "cadastro", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Phone> phones = new ArrayList<Phone>();

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/YYY HH:mm:ss", timezone="America/Sao_Paulo", locale="br")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/YYY HH:mm:ss", timezone="America/Sao_Paulo", locale="br")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;
    
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/YYY HH:mm:ss", timezone="America/Sao_Paulo", locale="br")
    @Temporal(TemporalType.TIMESTAMP)
    private Date last_login;

    private String token;




    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
    	
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }





    public List<Phone> getPhones() {
        return phones;
    }


    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }


    public Date getCreated() {
        return created;
    }


    public void setCreated(Date created) {
        this.created = created;
    }


    public Date getModified() {
        return modified;
    }


    public void setModified(Date modified) {
        this.modified = modified;
    }


    public Date getLast_login() {
        return last_login;
    }


    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }


    public String getToken() {
        return token;
    }


    public void setToken(String token) {
        this.token = token;
    }

    public void addToCadastroPhone(Phone phone) {
        phone.setCadastro(this);
        this.phones.add(phone);
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
