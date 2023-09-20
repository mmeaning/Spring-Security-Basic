package com.cos.security1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.security1.model.User;

//Integer => primary key(ID)의 타입이 Integer니까
//JpaRepository가 기본적인 CRUD함수를 들고 있습니다.
//JpaRepository를 상속했기 때문에 @Repository라는 애노테이션이 없어도 IoC가 됩니다. => 자동으로 빈으로 등록
public interface UserRepository extends JpaRepository<User, Integer>{
	//findBy 규칙 => Username 문법
	//SELECT * FROM user WHERE username = ?(파라미터);
	public User findByUsername(String username);//JPA Query methods를 공부해보면 나옵니다.
	
	//SELECT * FROM user WHERE email = ?(파라미터);
	public User findByEmail();
}
