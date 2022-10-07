package br.com.ifpe.oxefoodthierry.servicos.produto;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.oxefoodthierry.modelo.produto.CategoriaProduto;
import br.com.ifpe.oxefoodthierry.modelo.produto.CategoriaProdutoService;
import br.com.ifpe.oxefoodthierry.util.entity.GenericController;

@RestController
@RequestMapping("/api/categoriaproduto")
public class CategoriaProdutoController extends GenericController {

    @Autowired
    private CategoriaProdutoService categoriaProdutoService;

    @PostMapping
    public ResponseEntity<CategoriaProduto> save(@RequestBody @Valid CategoriaProdutoRequest request) {

	validarPreenchimentoChave(request.getChaveEmpresa());
	CategoriaProduto categoriaProduto = categoriaProdutoService.save(request.buildCategoriaProduto());
	return new ResponseEntity<CategoriaProduto>(categoriaProduto, HttpStatus.CREATED);
    }

}

