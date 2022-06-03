package br.com.serratec.monitoria.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.serratec.monitoria.model.Pessoa;
import br.com.serratec.monitoria.model.UserDetailsImpl;
import br.com.serratec.monitoria.repository.PessoaRepository;

@Service
public class AuthService implements UserDetailsService {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Pessoa> pessoa = pessoaRepository.findByUsername(username);

		if (pessoa.isEmpty()) {
			throw new UsernameNotFoundException("Ususario " + username + " não existe");
		}

		return new UserDetailsImpl(pessoa.get().getNome(), pessoa.get().getPassword(), pessoa.get().getId());
	}

}
