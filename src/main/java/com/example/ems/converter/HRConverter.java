package com.example.ems.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.ems.dto.HRDTO;
import com.example.ems.entities.HR;

@Component
public class HRConverter {
	@Autowired 
	private ModelMapper modelmapper;
	
	 public HRDTO toDto(HR hr) {
	        HRDTO dto = this.modelmapper.map(hr, HRDTO.class);
//        dto.setId(admin.getId());
//        dto.setName(admin.getName());
//        dto.setUsername(admin.getUsername());
//        dto.setEmail(admin.getEmail());
//        dto.setRole(admin.getRole());
        return dto;
    }
	 public List<HRDTO> toDto(List<HR> hr)
		{
			return hr.stream().map(x-> toDto(x)).collect(Collectors.toList());
		}
	    
	  public HR toEntity(HRDTO dto) {
	        HR hr = this.modelmapper.map(dto,HR.class);
//        admin.setId(dto.getId());
//        admin.setName(dto.getName());
//        admin.setUsername(dto.getUsername());
//        admin.setEmail(dto.getEmail());
//        admin.setRole(dto.getRole());
        return hr;
    }
	  public List<HR> toEntity(List<HRDTO> dto)
		{
			return dto.stream().map(x->toEntity(x)).collect(Collectors.toList());
		}
}
