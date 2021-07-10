package com.eng.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 郭富城
 */
@Data
@Builder
@AllArgsConstructor
public class CompetitorWord implements Serializable {
    private String word;
    private String definition;

    private String wrongDefinition1;
    private String wrongDefinition2;
    private String wrongDefinition3;
}
