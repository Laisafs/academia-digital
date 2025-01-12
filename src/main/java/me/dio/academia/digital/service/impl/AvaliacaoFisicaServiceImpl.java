package me.dio.academia.digital.service.impl;

import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaForm;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaUpdateForm;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.repository.AvaliacaoFisicaRepository;
import me.dio.academia.digital.service.IAvaliacaoFisicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliacaoFisicaServiceImpl implements IAvaliacaoFisicaService {

  @Autowired
  private AvaliacaoFisicaRepository avaliacaoFisicaRepository;

  @Autowired
  private AlunoRepository alunoRepository;

  @Override
  public AvaliacaoFisica create(AvaliacaoFisicaForm form) throws Exception {
    AvaliacaoFisica avaliacaoFisica = new AvaliacaoFisica();
    Aluno aluno = alunoRepository.findById(form.getAlunoId()).get();
    
    if (aluno == null) {
    	throw new Exception("Aluno não localizado!");
    }

    avaliacaoFisica.setAluno(aluno);
    avaliacaoFisica.setPeso(form.getPeso());
    avaliacaoFisica.setAltura(form.getAltura());

    return avaliacaoFisicaRepository.save(avaliacaoFisica);
  }

  @Override
  public AvaliacaoFisica get(Long id) {
	  return avaliacaoFisicaRepository.getById(id);
  }

  @Override
  public List<AvaliacaoFisica> getAll() {
    return avaliacaoFisicaRepository.findAll();
  }

  @Override
  public AvaliacaoFisica update(Long id, AvaliacaoFisicaUpdateForm formUpdate) {
	  AvaliacaoFisica avaliacaoFisicaDB = avaliacaoFisicaRepository.findById(id).get();
	  avaliacaoFisicaDB.setPeso(formUpdate.getPeso());
	  avaliacaoFisicaDB.setAltura(formUpdate.getAltura());
	  avaliacaoFisicaRepository.save(avaliacaoFisicaDB);
	  
	  return avaliacaoFisicaDB;
  }

  @Override
  public void delete(Long id) {
	  AvaliacaoFisica avaliacaoFisica = avaliacaoFisicaRepository.findById(id).get();
	  
	  if (avaliacaoFisica != null) {
		  avaliacaoFisicaRepository.delete(avaliacaoFisica);
	  }
  }
}
