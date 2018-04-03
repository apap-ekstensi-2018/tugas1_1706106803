package com.tugas1.model;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UniversitasModel {
	private int id;
	private String kode_univ;
	private String nama_univ;
}