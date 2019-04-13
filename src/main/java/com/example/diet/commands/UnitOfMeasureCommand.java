package com.example.diet.commands;

import com.example.diet.domains.UnitOfMeasure;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UnitOfMeasureCommand extends UnitOfMeasure {
    private Long id;
    private String description;
}
