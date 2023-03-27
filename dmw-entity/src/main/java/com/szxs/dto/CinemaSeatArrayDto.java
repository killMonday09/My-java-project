package com.szxs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CinemaSeatArrayDto implements Serializable {
private Integer cinemaId;
private Integer scheduleId;
private Integer id;
}
