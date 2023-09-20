package com.cos.security1.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.security1.model.User;
import com.cos.security1.repository.UserRepository;

//시큐리티 설정에서 loginProcessingUrl("/login");
// /login 요청이 오면 자동으로 UserDetailsService타입으로 IoC되어있는 loadUserByUsername 함수가 실행 => 규칙
@Service
public class PrincipalDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	//Security session = Authentication = UserDetails가 들어가야합니다.
	//이 메서드의 리턴값인 UserDetails는 Authentication의 내부에 들어가게됩니다.
	//결과로 Security session(내부 Authentication(내부 UserDetails))가 됩니다.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//유저가 있는지 확인을 해봐야해요.
		//근데 Username으로 User를 확인하려면 UserRepository에 하나 만들어야합니다.
		User userEntity = userRepository.findByUsername(username);
		
		if(userEntity != null) {
			return new PrincipalDetails(userEntity);
		}
		return null;
	}

}
