package com.ms.reloff.service;

import static com.ms.reloff.utils.ConstantUtil.LOG_END;
import static com.ms.reloff.utils.ConstantUtil.LOG_START;
import static com.ms.reloff.utils.ConstantUtil.MSG_MAIL_DUPL;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.reloff.dto.UserDto;
import com.ms.reloff.entity.Profile;
import com.ms.reloff.entity.Users;
import com.ms.reloff.repository.IProfileRepository;
import com.ms.reloff.repository.IUserRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * UsersServiceImpl clase que implementa la interfaz de servicio
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
@Service
@Slf4j
public class UsersServiceImpl implements UsersService {

	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private IProfileRepository profileRepository;

	@Override
	public Users getUserByUsername(String username) {
		log.info("[getUserByUsername]::start/end of method");
		Optional<Users> opt = userRepository.findByUsernameOrMail(username, username);
		if (opt.isPresent())
			return opt.get();

		return null;
	}

	@Override
	public Long countUsersByUsername(String username) {
		log.info("[countUserByUsername]::start/end of method");
		return userRepository.countByNationalIdOrUsername(username, username);
	}

	@Override
	public List<UserDto> findAllUsers() {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		List<UserDto> result = userRepository.findAllUsers();
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return result;
	}

	@Override
	public Boolean save(UserDto user) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		String username = user.getMail().substring( 0, user.getMail().indexOf("@"));
		Users model = userRepository.findByMailOrNationalIdOrUsername(user.getMail(), user.getNationalId(), username);
		
		if ((Objects.nonNull(model) && Objects.isNull(user.getId()))
				|| Objects.nonNull(model) && !user.getId().equals(model.getId()))
			throw new Exception(MSG_MAIL_DUPL);

		Users usuarioModel = new Users();
		Optional<Profile> profile = profileRepository.findById(Long.valueOf(user.getProfileId()));

		if (profile.isPresent()) {

			if(Objects.nonNull(user.getId()))
				usuarioModel.setId(user.getId());
				
			usuarioModel.setNames(user.getNames());
			usuarioModel.setLastName(user.getLastName());
			usuarioModel.setMiddleName(user.getMiddleName());
			usuarioModel.setMail(user.getMail());
			usuarioModel.setNationalId(username);
			usuarioModel.setProfile(profile.get());
			usuarioModel.setUsername(username);
			usuarioModel.setPass(user.getPass());
			usuarioModel.setBusinessPosition(user.getBusinessPosition());
			userRepository.save(usuarioModel);
		}
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return true;
	}

	@Override
	public boolean delete(UserDto dto) throws Exception {
		log.info(String.format(LOG_START, Thread.currentThread().getStackTrace()[1].getMethodName()));
		userRepository.deleteById(dto.getId());
		log.info(String.format(LOG_END, Thread.currentThread().getStackTrace()[1].getMethodName()));
		return true;
	}

}
