package ru.nanikon.backend.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Natalia Nikonova
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateShotDTO {
   private Integer x;
   private Double y;
   private Integer r;
}
