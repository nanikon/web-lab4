package ru.nanikon.backend.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author Natalia Nikonova
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateShotDTO {
   @Digits(integer = 1, fraction = 0)
   @Min(-4)
   @Max(4)
   @NotNull(message = "не должно быть пустым")
   private Double x;
   @Digits(integer = 1, fraction = 10)
   @Min(-5)
   @Max(5)
   @NotNull(message = "не должно быть пустым")
   private Double y;
   @Digits(integer = 1, fraction = 0)
   @Min(0)
   @Max(4)
   @NotNull(message = "не должно быть пустым")
   private Double r;
}
