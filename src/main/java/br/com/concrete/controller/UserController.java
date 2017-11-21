package br.com.concrete.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import br.com.concrete.bean.Phone;
import br.com.concrete.bean.User;
import br.com.concrete.repository.UserRepository;
import br.com.concrete.utils.CriptografarSenhas;
import br.com.concrete.utils.EnumMessage;
import br.com.concrete.utils.Token;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.Date;



@RestController
public class UserController {
    UserRepository repository;

    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/cadastro", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> cadastro(@RequestBody User user) {


		if (user == null || user.getEmail() == null || user.getPassword() == null) {
			return new ResponseEntity<Object>(EnumMessage.CAMPOS_NULOS.getMensagem(), HttpStatus.BAD_REQUEST);
		} 
		
		if (repository.findByEmail(user.getEmail()) == null) {
			Date data =new Date();
			user.setCreated(data);
			user.setModified(data);
			user.setLast_login(data);
			user.setToken(Token.gerarToken());
			user.setPassword(new CriptografarSenhas().criptografarSenhas(user.getPassword()));

			for (Phone ph : user.getPhones()) {
				ph.setCadastro(user);
			}
			return new ResponseEntity<Object>(repository.save(user).toString(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(EnumMessage.EMAIL_EXISTENTE.getMensagem(), HttpStatus.CONFLICT);
		}
	}


    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Object> login(@RequestBody User user) {

		if (user.getEmail() != null && user.getPassword() != null) {

			User cadBD = repository.findByEmail(user.getEmail());
			if (cadBD.getEmail().equals(user.getEmail())
					&& cadBD.getPassword().equals(new CriptografarSenhas().criptografarSenhas(user.getPassword()))) {

				cadBD.setToken(Token.gerarToken());
				cadBD.setLast_login(new Date());
				repository.save(cadBD);
				return new ResponseEntity<Object>(cadBD.toString(), HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>(EnumMessage.USUARIO_OU_SENHA_INVALIDOS.getMensagem(),
						HttpStatus.UNAUTHORIZED);
			}
		}
		return new ResponseEntity<Object>(EnumMessage.USUARIO_OU_SENHA_INVALIDOS.getMensagem(),
				HttpStatus.UNAUTHORIZED);

	}


    @RequestMapping(value = "/perfil/{id}", method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> perfil(@RequestHeader(value="Authorization", required=false) String auth, @PathVariable Long id) {
    	
        if (auth != null) {
            String auth_parts[] = auth.trim().split("\\s+");
            if (auth_parts.length > 0) {
                String token = auth_parts[auth_parts.length - 1];
                User cadBD = repository.findOne(id);
               
                if (cadBD != null) {
                    if (cadBD.getToken().equals(token)) {
                        if (Token.validarToken(token)) {
                            return new ResponseEntity<Object>(cadBD.toString(), HttpStatus.OK);
                        } else {
                            return new ResponseEntity<Object>(EnumMessage.USUARIO_OU_SENHA_INVALIDOS.getMensagem(), HttpStatus.UNAUTHORIZED);
                        }

                    } 
                } 
            } 
        }
       
            return new ResponseEntity<Object>(EnumMessage.NAO_AUTORIZADO.getMensagem(), HttpStatus.UNAUTHORIZED);
    }
}
