package com.tugas1.service;

import java.util.List;

import com.tugas1.model.FakultasModel;
import com.tugas1.model.MahasiswaModel;
import com.tugas1.model.ProgramStudiModel;
import com.tugas1.model.UniversitasModel;

public interface MahasiswaService {
	MahasiswaModel selectMahasiswa (String npm);
    List<UniversitasModel> selectUniversitas ();
    List<FakultasModel> selectFakultas (String id_univ);
    List<ProgramStudiModel> selectProgramStudi (String id_univ,String id_fakultas);
    List<MahasiswaModel> selectAllMahasiswa2 (String id_univ, String id_fakultas, String id_prodi);
    void insertMahasiswa(MahasiswaModel mahasiswa);
    void updateMahasiswa(MahasiswaModel mahasiswa);
    MahasiswaModel selectPresentase(String tahun_masuk, String id_prodi);
}
