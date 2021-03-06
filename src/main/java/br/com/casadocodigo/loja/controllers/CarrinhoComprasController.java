package br.com.casadocodigo.loja.controllers;

import br.com.casadocodigo.loja.daos.ProdutoDao;
import br.com.casadocodigo.loja.models.CarrinhoCompras;
import br.com.casadocodigo.loja.models.CarrinhoItem;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/carrinho")
@Scope(value= WebApplicationContext.SCOPE_REQUEST)
public class CarrinhoComprasController {

    @Autowired
    private CarrinhoCompras carrinho;

    @Autowired
    private ProdutoDao produtoDao;

    private CarrinhoItem criaItem(Integer produtoId, TipoPreco tipo){
        Produto produto = produtoDao.find(produtoId);
        CarrinhoItem carrinhoItem = new CarrinhoItem(produto, tipo);
        return carrinhoItem;
    }

    @RequestMapping("/add")
    public ModelAndView add(Integer produtoId, TipoPreco tipo){
        ModelAndView modelAndView = new ModelAndView("redirect:/produtos");
        CarrinhoItem carrinhoItem = criaItem(produtoId, tipo);
        carrinho.add(carrinhoItem);
        return modelAndView;
    }



}
