package ru.nanikon.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.nanikon.backend.data.CreateShotDTO;
import ru.nanikon.backend.data.FullShotDTO;
import ru.nanikon.backend.service.ShotService;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Natalia Nikonova
 */
@RestController
@RequestMapping("/api/shots")
@SecurityRequirement(name="Authorization")
public class ShotController {
    private final ShotService shotService;

    public ShotController(ShotService shotService) {
        this.shotService = shotService;
    }

    @Operation(summary = "Метод для получения всех выстрелов текущего авторизированного пользователя")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<FullShotDTO> getAllShots() {
        return shotService.getAll();
    }

    @Operation(summary = "Метод для проверки выстрела и сохранения его в базу данных")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public FullShotDTO createShot(@RequestBody @Valid CreateShotDTO shotDTO) {
        return shotService.create(shotDTO);
    }
}
