package com.tugas1.service;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tugas1.dao.KemahasiswaanMapper;
import com.tugas1.model.FakultasModel;
import com.tugas1.model.MahasiswaModel;
import com.tugas1.model.ProgramStudiModel;
import com.tugas1.model.UniversitasModel;

@Slf4j
@Service
public class MahasiswaServiceDatabase implements MahasiswaService{
	@Autowired
	private KemahasiswaanMapper mhsMapper;
	
	@Override
	public MahasiswaModel selectMahasiswa (String npm){
		log.info ("Lihat data mahasiswa dengan NPM = {}", npm);
			return mhsMapper.selectMahasiswa(npm);
	}
	
	@Override
    public List<MahasiswaModel> selectAllMahasiswa2 (String id_univ, String id_fakultas, String id_prodi)
    {
        log.info ("Tampilkan data mahasiswa");
        return mhsMapper.selectAllMahasiswa2(id_univ,id_fakultas,id_prodi);
    }
	
	@Override
	public List<UniversitasModel> selectUniversitas (){
		log.info("Tampilkan data universitas");
		return mhsMapper.selectUniversitas();
	}
	
	@Override
	public List<FakultasModel> selectFakultas (String id_univ){
		log.info ("Lihat data fakultas dengan id universitas = {}", id_univ);
		return mhsMapper.selectFakultas(id_univ);
	}
	
	@Override
	public List<ProgramStudiModel> selectProgramStudi (String id_univ, String id_fakultas){
		log.info ("Lihat data program studi dengan id univ = {} dan id fakultas = {}", id_univ,id_fakultas);
		return mhsMapper.selectProgramStudi(id_univ, id_fakultas);
	}
	
	@Override
    public void insertMahasiswa (MahasiswaModel mahasiswa)
    {
    	mhsMapper.insertMahasiswa(mahasiswa);
    }
	
	@Override
    public void updateMahasiswa (MahasiswaModel mahasiswa)
    {
    	mhsMapper.updateMahasiswa(mahasiswa);
    }
	
	@Override
	public MahasiswaModel selectPresentase (String tahun_masuk, String id_prodi){
		log.info ("Lihat presentase kelulusan pada tahun= {} di prodi = {} ", tahun_masuk,id_prodi);
		return mhsMapper.selectPresentase(tahun_masuk,id_prodi);
	}
	
}
