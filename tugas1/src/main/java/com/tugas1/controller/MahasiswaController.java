package com.tugas1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tugas1.model.FakultasModel;
import com.tugas1.model.MahasiswaModel;
import com.tugas1.model.ProgramStudiModel;
import com.tugas1.model.UniversitasModel;
import com.tugas1.service.MahasiswaService;
@Controller
public class MahasiswaController {
	
	@Autowired
	MahasiswaService mahasiswaDAO;
    
	@RequestMapping("/")
    public String index (Model model)
    {
		return "index";
    }
	
	@RequestMapping(value="/mahasiswa", method=RequestMethod.GET)
	public String view (Model model, @RequestParam(value="npm", required=false) String npm)
	{
		MahasiswaModel mahasiswa = mahasiswaDAO.selectMahasiswa(npm);
		
		if (mahasiswa != null){
			model.addAttribute ("mahasiswa", mahasiswa);
			return "view";	
		}else{
			model.addAttribute ("npm",npm);
			return "not-found";
		}
	}
	
	@RequestMapping (value="mahasiswa/cari", method=RequestMethod.GET)
	public String searchPath (Model model, 
			@RequestParam(value="univ", required=false) String id_univ, 
			@RequestParam(value="fakultas", required=false) String id_fakultas)
	{
		if (id_univ == null && id_fakultas == null){
			List<UniversitasModel> universitas = mahasiswaDAO.selectUniversitas();
			model.addAttribute("universitas", universitas);
			return "cari";
		}
		else if (id_univ != null && id_fakultas == null){
			List<FakultasModel> fakultas = mahasiswaDAO.selectFakultas(id_univ);
			
			if(fakultas != null){
				model.addAttribute("fakultas", fakultas);
				return "carifakultas";
			}else{
				model.addAttribute("id_univ",id_univ);
				return "not-found";
			}
		}
		else if (id_univ != null && id_fakultas != null){
			List<ProgramStudiModel> prodi2 = mahasiswaDAO.selectProgramStudi(id_univ,id_fakultas);
			if(prodi2 != null){
				model.addAttribute("prodi2", prodi2);
				return "cariprodi";
			}else{
				model.addAttribute("id_fakultas",id_fakultas);
				return "not-found";
			}
		}else return "not-found";
	}
	
    @RequestMapping(value="/mahasiswa/viewall", method=RequestMethod.GET)
    public String view (Model model,
    		@RequestParam(value="univ", required=true) String id_univ, 
			@RequestParam(value="fakultas", required=true) String id_fakultas,
			@RequestParam(value="prodi", required=true) String id_prodi)
    {
        List<MahasiswaModel> mahasiswa2 = mahasiswaDAO.selectAllMahasiswa2 (id_univ, id_fakultas, id_prodi);
        model.addAttribute ("mahasiswa2", mahasiswa2);
        return "viewall";
    }
    
    @RequestMapping("/mahasiswa/tambah")
    public String add (Model model)
    {
        return "form-add";
    }
    
    @RequestMapping(value = "/mahasiswa/tambah/submit", method = RequestMethod.POST)
    public String addSubmit (@ModelAttribute MahasiswaModel mahasiswa, Model model)
    {
        mahasiswaDAO.insertMahasiswa(mahasiswa);
        model.addAttribute ("mahasiswa", mahasiswa);
        return "success-add";
    }
    
    @RequestMapping ("/mahasiswa/ubah/{npm}")
    public String update (Model model, 
    		@PathVariable(value = "npm") String npm)
    {
        MahasiswaModel mahasiswa = mahasiswaDAO.selectMahasiswa (npm);
        
        if (mahasiswa != null){
        	model.addAttribute("mahasiswa",mahasiswa);
        	return "form-update";
        }else {
        	model.addAttribute ("npm", npm);
        	return "not-found";
        }
    }
    
	@RequestMapping(value = "/mahasiswa/ubah/submit", method = RequestMethod.POST)
    public String updateSubmit (@ModelAttribute MahasiswaModel mahasiswa, Model model)
    {
        mahasiswaDAO.updateMahasiswa(mahasiswa);
        model.addAttribute ("mahasiswa", mahasiswa);
        return "success-update";
    }
	
	@RequestMapping (value="mahasiswa/kelulusan", method=RequestMethod.GET)
	public String kelulusanPath (Model model, 
			@RequestParam(value="tahun_masuk", required=false) String tahun_masuk, 
			@RequestParam(value="id_prodi", required=false) String id_prodi)
	{
		if (tahun_masuk != null && id_prodi != null){
			MahasiswaModel mahasiswa= mahasiswaDAO.selectPresentase(tahun_masuk, id_prodi);
			model.addAttribute("mahasiswa", mahasiswa);
			return "result-kelulusan";
		}
		else return "kelulusan";
	}

}
