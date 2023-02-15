package com.adam.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adam.repository.CompanyRepository;
import com.adam.repository.MemberRepository;
import com.adam.dto.AdminFormDto;
import com.adam.dto.MemberFormDto;
import com.adam.entity.Company;
import com.adam.entity.Member;

import lombok.RequiredArgsConstructor;

@Service // service 클래스의 역할
@Transactional // 서비스 클래서에서 로직을 처리하다가 에러가 발생하면 로직을 수행하기 이전 상태로 되돌려 준다.
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
	private final MemberRepository memberRepository; //의존성 주입
	private final CompanyRepository companyRepository; //의존성 주입
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		Member member = memberRepository.findByUserId(userId);
		
		if(member == null) {
			throw new UsernameNotFoundException(userId);
		}
		
		//userDetails의 객체를 반환
		return User.builder()
				.username(member.getUserId())
				.password(member.getPassword())
				.roles(member.getRole().toString())
				.build();

	}
	
	public Member saveMember(Member member) {
		validateDuplicateMember(member);
		return memberRepository.save(member); //member 테이블에 insert
	}
	
	//아이디 중복체크 메소드
	private void validateDuplicateMember(Member member) {
		Member findMember = memberRepository.findByUserId(member.getUserId());
		if (findMember != null) {
			throw new IllegalStateException("이미 가입된 회원입니다.");
		}
	}
	
	public AdminFormDto getCompany() {
		Long comNum = memberRepository.findByCompanyByNative();
		AdminFormDto mf = new AdminFormDto();
		mf.setComNum(comNum);
		
		return mf;
	}
	
	public Company saveCompany(Company company) {
		return companyRepository.save(company); //member 테이블에 insert
	}
	

}
