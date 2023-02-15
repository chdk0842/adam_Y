package com.adam.entity;

import javax.persistence.*;
import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.adam.constant.Role;
import com.adam.dto.AdminFormDto;
import com.adam.dto.MemberFormDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="member") //테이블명
@Getter
@Setter
@ToString
public class Member {
	@Id
	@Column(unique = true)
	private String userId;
	
	private String name;
	
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "comNum")
	private Company company;

	
	public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder, Company getCom, Role role) {
		Member member = new Member();
		member.setName(memberFormDto.getName());
		member.setUserId(memberFormDto.getUserId());
		member.setCompany(getCom);
		
		 String password = passwordEncoder.encode(memberFormDto.getPassword()); //비밀번호 암호화
		 member.setPassword(password);
		 
		
		member.setRole(role);
		
		return member;
	}
	
	public static Member createAdminMember(AdminFormDto adminFormDto, PasswordEncoder passwordEncoder, Company getCom, Role role) {
		Member member = new Member();
		member.setName(adminFormDto.getName());
		member.setUserId(adminFormDto.getUserId());
		member.setCompany(getCom);
		
		 String password = passwordEncoder.encode(adminFormDto.getPassword()); //비밀번호 암호화
		 member.setPassword(password);
		 
		
		member.setRole(role);
		
		return member;
	}




}
