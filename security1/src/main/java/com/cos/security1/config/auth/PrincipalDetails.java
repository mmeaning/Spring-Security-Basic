package com.cos.security1.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.security1.model.User;

//시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행시킨다.
//로그인을 진행이 완료가 되면 시큐리티가 가진 session을 만들어줍니다.(Security ContextHolder)
//(Security ContextHolder)이 키값에 세션정보를 저장할 때 여기 들어갈 수 있는 정보(오브젝트 타입)가 정해져있다. => Authentication 타입 객체여야한다.
//Authentication안에 User정보가 있어야한다.
//User오브젝트의 타입은 UserDetails타입 객체여야한다.

//즉, Security Session영역에 들어갈 수 있는 객체=> Authentication객체여야한다. =>그 객체 안의 User정보는 UserDetails(PrincipalDetails)타입이여야한다.

public class PrincipalDetails implements UserDetails {

	private User user;// 콤포지션

	// 생성자
	public PrincipalDetails(User user) {
		this.user = user;
	}

	// 해당 User의 권한을 리턴하는 곳!
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// user.getRole(); => 이게 해당 유저의 권한인데, 얘 리턴타입은 String입니다. => 반환타입을 맞춰줘볼게요
		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return user.getRole();
			}
		});

		return collect;
	}

	// 비밀번호 가져오기
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	// 유저 이름 가져오기
	@Override
	public String getUsername() {
		return user.getUsername();
	}

	// 계정 만료됐니?
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 계정 잠겼니?
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 비밀번호를 너무 오래사용한거 아니니?
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 계정이 활성화 되어있니?
	@Override
	public boolean isEnabled() {

		// 우리 사이트에서 1년동안 회원이 로그인을 안하면 휴면 계정으로 하기로함.
		// user.getLoginDate();//마지막 로그인 날짜를 들고와서
		// 현재 시간 - 마지막 로그인 시간 => 1년을 초과하면 return false;로 하는겁니다.
		return true;
	}

}
