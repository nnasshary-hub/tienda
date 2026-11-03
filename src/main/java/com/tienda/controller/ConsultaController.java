package com.tienda.controller;

import com.tienda.service.CategoriaService;
import com.tienda.service.ProductoService;
import java.math.BigDecimal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    public ConsultaController(
            ProductoService productoService,
            CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        var productos = productoService.getProductos(false);

        model.addAttribute("productos", productos);
        cargarCategorias(model);

        return "consulta/listado";
    }

    @PostMapping("/consultaDerivada")
    public String consultaDerivada(
            @RequestParam("precioInf") BigDecimal precioInf,
            @RequestParam("precioSup") BigDecimal precioSup,
            Model model) {

        var productos = productoService.consultaDerivada(
                precioInf, precioSup);

        model.addAttribute("productos", productos);
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);
        cargarCategorias(model);

        return "consulta/listado";
    }

    @PostMapping("/consultaJPQL")
    public String consultaJPQL(
            @RequestParam("precioInf") BigDecimal precioInf,
            @RequestParam("precioSup") BigDecimal precioSup,
            Model model) {

        var productos = productoService.consultaJPQL(
                precioInf, precioSup);

        model.addAttribute("productos", productos);
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);
        cargarCategorias(model);

        return "consulta/listado";
    }

    @PostMapping("/consultaSQL")
    public String consultaSQL(
            @RequestParam("precioInf") BigDecimal precioInf,
            @RequestParam("precioSup") BigDecimal precioSup,
            Model model) {

        var productos = productoService.consultaSQL(
                precioInf, precioSup);

        model.addAttribute("productos", productos);
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);
        cargarCategorias(model);

        return "consulta/listado";
    }

    @PostMapping("/consultaAmpliada")
    public String consultaAmpliada(
            @RequestParam("idCategoria") Integer idCategoria,
            @RequestParam("precioInf") BigDecimal precioInf,
            @RequestParam("precioSup") BigDecimal precioSup,
            Model model) {

        var productos = productoService.consultaAmpliada(
                idCategoria, precioInf, precioSup);

        model.addAttribute("productos", productos);
        model.addAttribute("idCategoria", idCategoria);
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);
        cargarCategorias(model);

        return "consulta/listado";
    }

    private void cargarCategorias(Model model) {
        model.addAttribute(
                "categorias",
                categoriaService.getCategorias(true));
    }
}
