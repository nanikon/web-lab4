package ru.nanikon.backend.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.nanikon.backend.data.CreateShotDTO;
import ru.nanikon.backend.data.FullShotDTO;
import ru.nanikon.backend.exception.NotFoundDataException;
import ru.nanikon.backend.mapper.ShotMapper;
import ru.nanikon.backend.repository.ShotRepository;
import ru.nanikon.backend.repository.UserRepository;
import ru.nanikon.backend.security.userDetails.CustomUserDetails;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Natalia Nikonova
 */
@Service
public class ShotService {
   private final ShotRepository shotRepository;
   private final UserRepository userRepository;
   private final ShotMapper mapper;

   public ShotService(
           ShotRepository shotRepository,
           UserRepository userRepository,
           ShotMapper shotMapper
   ) {
      this.shotRepository = shotRepository;
      this.userRepository = userRepository;
      this.mapper = shotMapper;
   }

   private Long getCurrentUserId() {
      return ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
   }

   public List<FullShotDTO> getAll() {
      return shotRepository.getAllByUser(userRepository.findById(getCurrentUserId())
                      .orElseThrow(() -> new NotFoundDataException("Не найден пользователь")))
              .stream()
              .map(mapper::fromEntityToDTO)
              .collect(Collectors.toList());
   }

   public FullShotDTO create(CreateShotDTO shotDTO) {
      return mapper.fromEntityToDTO(shotRepository.save(mapper.fromCreateDTOToEntity(shotDTO, getCurrentUserId())));
   }
}
