package com.example.demo.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;


import com.example.demo.models.ProductoModel;
import com.example.demo.models.UsuarioModel;
import com.example.demo.repositories.ProductoRepository;
import com.example.demo.repositories.UsuarioRepository;
import com.example.demo.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    ProductoService  productoService;
    @Autowired
    UsuarioRepository usuarioRepository;
private final ProductoRepository productoRepository;
    public ProductoController(ProductoService productoService, ProductoRepository productoRepository) {

        this.productoRepository = productoRepository;
    }

    @GetMapping()
    public ArrayList<ProductoModel> obtenerProducto(){
        return productoService.obtenerProducto();
    }

    @PostMapping("/query")
    public ProductoModel guardarProducto(@ModelAttribute ProductoModel producto, @RequestParam("image") MultipartFile file) throws IOException {
        String path= new File("C:/Users/GABO/Desktop/Catalogo_frontend/Catalogo_Frontend/src/assets").getAbsolutePath();
        //String path = "C:\\Users\\GABO\\Desktop\\taller\\Catalogo_backend\\apiRestSpringBootUsuarios-master\\apiRestSpringBootUsuarios-master\\apiRestSpringBootUsuarios-master\\images";
        String name= UUID.randomUUID().toString()+file.getOriginalFilename();
        try {
            //byte[] bytes = IOUtils.toByteArray(file.getInputStream());

            // Path path = Paths.get("./src/main/images"+"/"+ UUID.randomUUID().toString()+file.getOriginalFilename());
            file.transferTo( new File(path+ "\\" + name)); //no lo guarda con tipo pero se puede habrir desde el navegador
            //Files.write(path,bytes);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

String p="./assets/";
     //  producto.setImagen(name);
        System.out.println(p+name);

        System.out.println(producto);
producto.setImagen(p+name);
        return productoService.guardarProducto(producto);

    }



    @PostMapping("/guardar")
    public ProductoModel guardarProducto(@RequestBody ProductoModel producto) throws IOException {

        return productoService.guardarProducto(producto);

    }



    @PostMapping("/image")
    public void getImage(@RequestParam("image") MultipartFile file){
        String path= new File("apiRestSpringBootUsuarios-master/apiRestSpringBootUsuarios-master/src/main/images").getAbsolutePath();
        //String path = "C:\\Users\\GABO\\Desktop\\taller\\Catalogo_backend\\apiRestSpringBootUsuarios-master\\apiRestSpringBootUsuarios-master\\apiRestSpringBootUsuarios-master\\images";
        String name= UUID.randomUUID().toString()+file.getOriginalFilename();
        try {
            //byte[] bytes = IOUtils.toByteArray(file.getInputStream());

           // Path path = Paths.get("./src/main/images"+"/"+ UUID.randomUUID().toString()+file.getOriginalFilename());
            file.transferTo( new File(path+ "\\" + name)); //no lo guarda con tipo pero se puede habrir desde el navegador
            //Files.write(path,bytes);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }


    }



    @GetMapping(path="/image/{imagename}")
    public ResponseEntity<Resource> findImage(@PathVariable("imagename") String id) throws IOException {
        String path= new File("apiRestSpringBootUsuarios-master/apiRestSpringBootUsuarios-master/src/main/images").getAbsolutePath();
        //File archivo = new File(path+"\\"+id);
        //System.out.println(archivo.getPath());
        //System.out.println(archivo.getName());
        Path root = Paths.get("apiRestSpringBootUsuarios-master/apiRestSpringBootUsuarios-master/src/main/images");
        Path file = root.resolve(id);
        Resource resource = new UrlResource(file.toUri());

        return  ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
    }


    @GetMapping( path = "/{id}")
    public Optional<ProductoModel> obtenerProductoPorId(@PathVariable("id") Long id) {
        return productoService.obtenerPorId(id);
    }
    @GetMapping( path = "/marca")
    public ArrayList<ProductoModel> obtenerProductoPorMarca(@RequestParam("marca") String marca) {
        return productoService.obtenerPorMarca(marca);
    }

    @GetMapping( path = "/categoria")
    public ArrayList<ProductoModel> obtenerProductoPorCategoria(@RequestParam("categoria") String categoria) {
        return productoService.obtenerPorCategoria(categoria);
    }
    @GetMapping( path = "/nombre")
    public ArrayList<ProductoModel> obtenerProductoPorNombre(@RequestParam("nombre") String nombre) {
        return productoService.obtenerPorNombre(nombre);
    }

  //  @GetMapping("/query")
 //   public ArrayList<ProductoModel> obtenerProductoPorIdUsuario(@RequestParam("id_usuario") Integer id_usuario){
  //      return this.ProductoService.obtenerPorIdUsuario(id_usuario);
 //   }



    @DeleteMapping( path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id){
        boolean ok = this.productoService.eliminarProducto(id);
        if (ok){
            return "Se eliminó el producto con id " + id;
        }else{
            return "No pudo eliminar el producto con id" + id;
        }
    }

    @PutMapping(path = "/{id}")
    public ProductoModel editarPorId(@RequestBody ProductoModel productoModel, @PathVariable("id") Long id  ){
        return  productoService.editarProducto(productoModel, id );

    }

    @PutMapping(path = "vista/{id}")
    public ProductoModel guardarVista(ProductoModel productoModel, @PathVariable("id") Long id  ){

       productoModel = this.productoRepository.findByIdProducto(id);
        return  productoService.saveVista(productoModel, id );

    }


    @GetMapping("usuario/{idUsuario}")
    public ArrayList<ProductoModel> obtenerProductoPorUsuario(@PathVariable Long idUsuario) {
        UsuarioModel u = new UsuarioModel();
       u= this.usuarioRepository.findByIdUsuario(idUsuario);
    return productoService.obtenerPorIdUsuario(u);
    }

    @GetMapping("usuario/dash")
    public ArrayList<Long> obtenerProductosPorMarca(@RequestParam("marca") String marca) {

        return this.productoRepository.countAllByMarca(marca);
    }



}
