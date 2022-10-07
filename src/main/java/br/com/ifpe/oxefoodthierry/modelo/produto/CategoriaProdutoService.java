package br.com.ifpe.oxefoodthierry.modelo.produto;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import antlr.StringUtils;
import br.com.ifpe.oxefoodthierry.util.entity.GenericService;
import br.com.ifpe.oxefoodthierry.util.exception.EntityAlreadyExistsException;

@Service
public class CategoriaProdutoService extends GenericService {
    
    @Autowired
    private CategoriaProdutoRepository repository;

    @Transactional
    public CategoriaProduto save(CategoriaProduto categoriaProduto) {

	super.validarRegistroVazio(categoriaProduto.getDescricao(), "descrição");
	this.validarCategoriaProdutoExistente(categoriaProduto, null);
	super.preencherCamposAuditoria(categoriaProduto);

	return repository.save(categoriaProduto);
    }

    private void validarCategoriaProdutoExistente(CategoriaProduto categoriaParam, Long id) {

	if (StringUtils.isNotBlank(categoriaParam.getDescricao())) {

	    CategoriaProduto categoria = repository.findByChaveAndDescricao(categoriaParam.getChaveEmpresa(), categoriaParam.getDescricao());

	    if (id == null) { // O id será null quando este método for chamado para validar a inclusão de novas categorias

		if (categoria != null) {
		    throw new EntityAlreadyExistsException(CategoriaProduto.LABEL, "Descrição");
		}

	    } else { // O id NÃO será null quando este método for chamado para validar a alteração de categorias

		if (categoria != null && categoria.getId() != id) {
		    throw new EntityAlreadyExistsException(CategoriaProduto.LABEL, "Descrição");
		}
	    }
	}
    }
}
