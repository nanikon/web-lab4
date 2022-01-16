package ru.nanikon.backend.mapper;

import org.springframework.stereotype.Component;
import ru.nanikon.backend.area.HorizontalRhomb;
import ru.nanikon.backend.area.LittleCircle;
import ru.nanikon.backend.area.Quarter;
import ru.nanikon.backend.area.VerticalRect;
import ru.nanikon.backend.data.CreateShotDTO;
import ru.nanikon.backend.data.FullShotDTO;
import ru.nanikon.backend.entity.ShotEntity;
import ru.nanikon.backend.exception.NotFoundDataException;
import ru.nanikon.backend.repository.UserRepository;
import ru.nanikon.backend.area.AreaService;

/**
 * @author Natalia Nikonova
 */
@Component
public class ShotMapper {
   private final AreaService areaService;
   private final UserRepository userRepository;

   public ShotMapper(AreaService areaService, UserRepository userRepository) {
      this.areaService = areaService;
      this.userRepository = userRepository;
      this.areaService.addShape(new LittleCircle(Quarter.FIRST));
      this.areaService.addShape(new VerticalRect(Quarter.SECOND));
      this.areaService.addShape(new HorizontalRhomb(Quarter.FOURTH));
   }

   public ShotEntity fromCreateDTOToEntity(CreateShotDTO source, Long userId) {
      ShotEntity target = new ShotEntity();
      target.setX((int) Math.round(source.getX()));
      target.setY(source.getY());
      target.setR((int) Math.round(source.getR()));
      areaService.checkArea(target);
      target.setUser(userRepository.findById(userId).orElseThrow(() -> new NotFoundDataException("Пользователь не найден")));
      return target;
   }

   public FullShotDTO fromEntityToDTO(ShotEntity source) {
      FullShotDTO target = new FullShotDTO();
      target.setX((double) source.getX());
      target.setY(source.getY());
      target.setR((double) source.getR());
      target.setHit(source.getIsHit());
      return target;
   }
}
