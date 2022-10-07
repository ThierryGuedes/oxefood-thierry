package br.com.ifpe.oxefoodthierry.modelo.produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import antlr.collections.List;

public interface CategoriaProdutoRepository extends JpaRepository<CategoriaProduto, Long>, JpaSpecificationExecutor<CategoriaProduto> {

    List<CategoriaProduto> findByChaveEmpresaOrderByDescricaoAsc(String chaveEmpresa);

    @Query(value = "SELECT c FROM CategoriaProduto c WHERE c.chaveEmpresa = :chaveEmpresa AND c.descricao = :descricao")
    CategoriaProduto findByChaveAndDescricao(String chaveEmpresa, String descricao);

    @Query(value = "SELECT c FROM CategoriaProduto c WHERE c.id = :id AND c.chaveEmpresa = :chaveEmpresa")
    CategoriaProduto findByIdAndChave(Long id, String chaveEmpresa);

}
