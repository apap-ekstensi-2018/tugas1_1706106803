package com.tugas1.model;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProgramStudiModel {
	private int id;
	private String kode_prodi;
	private String nama_prodi;
	private int id_univ;
	private String nama_univ;
	private int id_fakultas;
	private String nama_fakultas;
}
