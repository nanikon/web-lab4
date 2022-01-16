package ru.nanikon.backend.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Natalia Nikonova
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FullShotDTO extends CreateShotDTO {
   private boolean isHit;
}
