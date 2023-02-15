package com.adam.dto;
//회원가입으로부터 넘어오는 가입정보를 담을DTO


import javax.validation.constraints.*;

import org.hibernate.validator.constraints.Length;

import com.adam.constant.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminFormDto {
	
	@NotBlank(message = "아이디는 필수 입력 값입니다.")
	private String userId;
	
	@NotBlank(message = "이름은 필수 입력 값입니다.")
	private String name;	
	
	@NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
	@Length(min = 8, max = 16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요.")
	private String password;
		
	@NotEmpty(message = "회사명은 필수 입력 값입니다.")
	private String comName;

	private Long comNum;
	
	private Role role;
	
	
}