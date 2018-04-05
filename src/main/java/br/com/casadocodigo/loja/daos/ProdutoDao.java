package br.com.casadocodigo.loja.daos;

import br.com.casadocodigo.loja.models.Produto;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class ProdutoDao {

    @PersistenceContext
    private EntityManager manager;

    public void gravar(Produto produto) {
        manager.persist(produto);
    }

    public List<Produto> listar() {
        return manager.createQuery("Select p from Produto p", Produto.class).getResultList();
    }

    public Produto find(int id) {
        return manager.createQuery("select distinct(p) from Produto p join fetch p.precos precos "
                                                   + "where p.id = :id", Produto.class).setParameter("id", id).getSingleResult();
    }
}